package se.atrosys.service.image.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.birds.common.model.Image;
import se.atrosys.service.common.response.ImageResponse;
import se.atrosys.service.image.factory.ImageResponseFactory;
import se.atrosys.service.image.service.FlickrService;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
public class ImageResource {
	@Autowired
	ImageResponseFactory imageResponseFactory;

	@RequestMapping("/image/{id}")
	public Callable<ImageResponse> image(@PathVariable("id") String id) {
		return () -> imageResponseFactory.createResponse(id).toBlocking().single();
	}
}
