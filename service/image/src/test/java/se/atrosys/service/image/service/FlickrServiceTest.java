package se.atrosys.service.image.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import se.atrosys.birds.common.model.Image;

import java.util.List;

public class FlickrServiceTest {

	public static final String ID = "Troglodytes troglodytes";

	@Test
	@Ignore
	public void shouldGetDTO() {
		List<Image> images = new FlickrService().getImages(ID);

		Image image = images.get(0);

		Assert.assertNotNull(image);
		Assert.assertEquals(ID, image.getBirdId());
		Assert.assertNotEquals("", image.getBirdId());
		Assert.assertNotEquals("", image.getUrl());
	}

}