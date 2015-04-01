package se.atrosys.service.image.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.birds.common.model.Image;
import se.atrosys.service.common.response.ImageResponse;
import se.atrosys.service.image.service.FlickrService;

import java.util.List;

@RestController
public class ImageResource {
	@Autowired
	FlickrService flickrService;

	@RequestMapping("/image/{id}")
	public ImageResponse image(@PathVariable("id") String id) {
		return ImageResponse.builder().withImages(flickrService.getImages(id)).build();
	}
}
