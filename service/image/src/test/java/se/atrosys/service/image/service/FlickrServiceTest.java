package se.atrosys.service.image.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import rx.Observable;
import se.atrosys.birds.common.model.Image;

import java.util.List;

public class FlickrServiceTest {

	public static final String ID = "Troglodytes troglodytes";

	@Test
	@Ignore
	public void shouldGetDTO() {
		final Observable<List<Image>> observable = new FlickrService().getImages(ID);
		observable.doOnNext(images -> {

			Image image = images.get(0);

			Assert.assertNotNull(image);
			Assert.assertEquals(ID, image.getBirdId());
			Assert.assertNotEquals("", image.getBirdId());
			Assert.assertNotEquals("", image.getUrl());
		});
	}
}