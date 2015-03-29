package se.atrosys.service.image.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import se.atrosys.birds.common.model.Image;

public class FlickrServiceTest {

	public static final String ID = "Troglodytes troglodytes";

	@Test
	@Ignore
	public void shouldGetDTO() {
		Image image = new FlickrService().getImage(ID);

		Assert.assertNotNull(image);
		Assert.assertEquals(ID, image.getBirdId());
		Assert.assertNotEquals("", image.getBirdId());
		Assert.assertNotEquals("", image.getUrl());
	}

}