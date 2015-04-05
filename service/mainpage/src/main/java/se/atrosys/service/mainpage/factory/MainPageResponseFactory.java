package se.atrosys.service.mainpage.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;
import rx.Observable;
import se.atrosys.birds.common.model.Bird;
import se.atrosys.service.common.response.BirdResponse;
import se.atrosys.service.common.response.FamilyResponse;
import se.atrosys.service.common.response.ImageResponse;
import se.atrosys.service.common.response.SoundResponse;
import se.atrosys.service.mainpage.model.MainPage;
import se.atrosys.service.mainpage.response.MainPageResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class MainPageResponseFactory {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AsyncRestTemplate asyncRibbonRestTemplate;

	public MainPageResponse createRandomResponse(int randseed) {
		final MainPage.Builder mainPageBuilder = MainPage.builder();
		logger.info("Getting random bird");

		createMainPage(mainPageBuilder, getExchange("http://info/randombird/", BirdResponse.class, "").cache());

		return MainPageResponse.builder()
				.withMainPages(Collections.singletonList(mainPageBuilder.build()))
				.build();
	}

	public MainPageResponse createResponseForSpecificId(String birdId) {
		final MainPage.Builder mainPageBuilder = MainPage.builder();
		logger.info("Getting specific bird {}", birdId);

		createMainPage(mainPageBuilder, getExchange("http://info/bird/{birdId}", BirdResponse.class, birdId).cache());

		return MainPageResponse.builder()
				.withMainPages(Collections.singletonList(mainPageBuilder.build()))
				.build();
	}

	private void createMainPage(MainPage.Builder mainPageBuilder, Observable<ResponseEntity<BirdResponse>> responseEntityObservable) {
		responseEntityObservable.flatMap(
				responseEntity -> {
					// TODO null and range checking goes here.
					Bird bird = responseEntity.getBody().getBirds().get(0);
					mainPageBuilder.withBird(bird);

					logger.info("Got {}", bird.getName());
					Observable<ResponseEntity<ImageResponse>> imageObs = getImageObservable(mainPageBuilder, bird);
					logger.info("First");
					Observable<ResponseEntity<BirdResponse>> alternativesObs = getAlternativesObservable(mainPageBuilder, bird);
					logger.info("Second");
					Observable<ResponseEntity<SoundResponse>> soundObs = getSoundObservable(mainPageBuilder, bird);
					logger.info("Third");

					return Observable.merge(Arrays.asList(alternativesObs, imageObs, soundObs), 3);
				}
		).doOnError(
				(t) -> logger.error("Something went awry", t)
		).toBlocking().last();
//		).forEach((o) -> logger.info("Got {}", o));
	}

	private Observable<ResponseEntity<SoundResponse>> getSoundObservable(MainPage.Builder mainPageBuilder, Bird bird) {
		return getExchange("http://sound/sound/{id}",
				SoundResponse.class,
				bird.getName()).doOnNext(
				soundEntity -> {
					if (soundEntity.getBody().getSounds() != null && soundEntity.getBody().getSounds().size() > 0) {
						mainPageBuilder.withSound(soundEntity.getBody().getSounds().get(0));
						logger.info("Got sound");
					} else {
						logger.warn("Sound empty");
					}
				});
	}

	private Observable<ResponseEntity<BirdResponse>> getAlternativesObservable(MainPage.Builder mainPageBuilder, Bird bird) {
		return getExchange("http://info/family/{id}",
				FamilyResponse.class,
				bird.getFamily()).flatMap(
				familyResponseEntity -> {
					return getExchange("http://info/bird/{names}", BirdResponse.class, familyResponseEntity.getBody()
							.getFamilies()
							.stream()
							.flatMap(f -> f.getBirdNames().stream())
							.limit(5)
							.collect(Collectors.joining(","))).doOnNext(
							alternativesResponseEntity -> {
								mainPageBuilder.withAlternatives(alternativesResponseEntity.getBody().getBirds());
								logger.info("Got alternative birds");
							});
				}
		);
	}

	private Observable<ResponseEntity<ImageResponse>> getImageObservable(MainPage.Builder mainPageBuilder, Bird bird) {
		return getExchange("http://image/image/{id}",
								ImageResponse.class,
								bird.getName()).doOnNext(
				imageEntity -> {
					if (imageEntity.getBody().getImages() != null && imageEntity.getBody().getImages().size() > 0) {
						mainPageBuilder.withImage(imageEntity.getBody().getImages().get(0));
						logger.info("Got image");
					} else {
						logger.warn("Image empty");
					}
				});
	}

	private <T> Observable<ResponseEntity<T>> getExchange(String url, Class<T> responseClass, String param) {
		return Observable.from(asyncRibbonRestTemplate.exchange(url,
				HttpMethod.GET,
				HttpEntity.EMPTY,
				responseClass,
				param));
	}
}
