package se.atrosys.service.image.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;
import se.atrosys.service.common.response.ImageResponse;
import se.atrosys.service.image.service.FlickrService;

@Component
public class ImageResponseFactory {
	@Autowired
	FlickrService flickrService;
	public Observable<ImageResponse> createResponse(String id) {
		final ImageResponse.Builder builder = ImageResponse.builder();

		return flickrService.getImages(id).flatMap(images -> Observable.just(builder.withImages(images).build()));
	}
}
