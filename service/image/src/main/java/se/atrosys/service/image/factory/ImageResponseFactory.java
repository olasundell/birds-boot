package se.atrosys.service.image.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;
import se.atrosys.service.common.response.ImageResponse;
import se.atrosys.service.image.service.FlickrService;
import se.atrosys.service.resourceok.service.BinaryService;

import java.util.stream.Collectors;

@Component
public class ImageResponseFactory {
	@Autowired
	FlickrService flickrService;

	@Autowired
	BinaryService binaryService;

	public Observable<ImageResponse> createResponse(String id) {
		final ImageResponse.Builder builder = ImageResponse.builder();

		return flickrService.getImages(id)
				.flatMap(images -> Observable.just(builder.withImages(images.parallelStream()
						.filter(binaryService::registerOrCheckIfOK)
						.collect(Collectors.toList())).build()));
	}
}
