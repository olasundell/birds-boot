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
import se.atrosys.service.mainpage.model.MainPage;
import se.atrosys.service.mainpage.response.MainPageResponse;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class MainPageResponseFactory {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AsyncRestTemplate asyncRibbonRestTemplate;

	public MainPageResponse createResponse(int randseed) {
		final MainPage.Builder mainPageBuilder = MainPage.builder();
		logger.info("Getting random bird");

		getExchange("http://info/randombird/", BirdResponse.class, "").flatMap(
				responseEntity -> {
					// TODO null and range checking goes here.
					Bird bird = responseEntity.getBody().getBirds().get(0);
					mainPageBuilder.withBird(bird);

					logger.info("Got {}", bird.getName());
					Observable<ResponseEntity<ImageResponse>> imageObs = getImageObservable(mainPageBuilder, bird);
					Observable<ResponseEntity<BirdResponse>> alternativesObs = getAlternativesObservable(mainPageBuilder, bird);

					return Observable.merge(imageObs, alternativesObs);
				}
		).toBlocking().forEach((o) -> logger.info(o.toString()));

		return MainPageResponse.builder()
				.withMainPages(Collections.singletonList(mainPageBuilder.build()))
				.build();
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
								logger.info("Getting alternative birds");
								mainPageBuilder.withAlternatives(alternativesResponseEntity.getBody().getBirds());
							});
				}
		);
	}

	private Observable<ResponseEntity<ImageResponse>> getImageObservable(MainPage.Builder mainPageBuilder, Bird bird) {
		return getExchange("http://image/image/{id}",
								ImageResponse.class,
								bird.getName()).doOnNext(
								imageEntity -> {
									logger.info("Getting image");
									if (imageEntity.getBody().getImages() != null && imageEntity.getBody().getImages().size() > 0) {
										mainPageBuilder.withBinary(imageEntity.getBody().getImages().get(0));
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
