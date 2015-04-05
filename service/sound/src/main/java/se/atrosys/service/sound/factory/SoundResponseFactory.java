package se.atrosys.service.sound.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import se.atrosys.birds.common.model.Model;
import se.atrosys.birds.common.model.Sound;
import se.atrosys.service.common.Cache;
import se.atrosys.service.sound.dto.XenoCantoDTO;
import se.atrosys.service.common.response.SoundResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoundResponseFactory {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String UNLESS_SPEL = "#result == null";

	AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();

	@Cacheable(value = Cache.Constant.SOUND_CACHE_NAME)
	public Observable<SoundResponse> createResponse(List<String> ids, int limit) {
		logger.info("Creating response for {}", ids);

		SoundResponse.Builder builder = SoundResponse.builder();

		List<Observable<ResponseEntity<XenoCantoDTO>>> list = new ArrayList<>();

		ids.parallelStream().forEach(id -> {
			ListenableFuture<ResponseEntity<XenoCantoDTO>> dtoFuture = asyncRestTemplate.exchange("http://www.xeno-canto.org/api/2/recordings?query={birdName}",
					HttpMethod.GET,
					HttpEntity.EMPTY,
					XenoCantoDTO.class,
					id);

			list.add(Observable.from(dtoFuture).doOnNext(
					xenoCantoDTOResponseEntity ->
							builder.withSounds(createModels(xenoCantoDTOResponseEntity.getBody(), limit))
			));
		});

		Observable.merge(list).toBlocking().last();

		return Observable.just(builder.build());
	}

	private Collection<Sound> createModels(XenoCantoDTO dto, int limit) {
		if (dto == null || dto.recordings == null) {
			return new ArrayList<>();
		}

		return dto.recordings.parallelStream()
				.limit(rangeCheckedLimit(limit))
				.map(this::createModel)
				.collect(Collectors.toList());
	}

	private int rangeCheckedLimit(int limit) {
		if (limit < 1) {
			return 1;
		} else if (limit > 1000) {
			return 1000;
		}

		return limit;
	}

	private Sound createModel(XenoCantoDTO.Recording recording) {
		return Sound.builder()
				.withBirdName(recording.sp)
				.withCountry(recording.cnt)
				.withEnglishName(recording.en)
				.withFamily(recording.gen)
				.withFileUrl(recording.file)
				.withLicense(recording.lic)
				.withLocation(recording.loc)
				.withType(recording.type)
				.withUrl(recording.url)
				.withXenoCantoId(recording.id)
				.build();
	}
}
