package se.atrosys.service.common.response;

import org.junit.Assert;
import org.junit.Test;
import se.atrosys.birds.common.model.Bird;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AbstractResponseTest {
	@Test
	public void shouldGenerateLinks() {
		TestResponse response = new TestResponse();

		response.addBirds(Arrays.asList(Bird.builder().withName(Bird.LATIN, "a").build(),
				Bird.builder().withName(Bird.LATIN, "b").build()));

		Map<String, Link> map = response.getLinks();
		Assert.assertNotNull(map);
		Assert.assertFalse(map.isEmpty());
//		map.containsKey();
	}

	public static class TestResponse extends AbstractResponse {

		public void addBirds(List<Bird> birds){
			models.addAll(birds);
		}

		@Override
		protected String getLinkPrefix() {
			return "test";
		}
	}
}