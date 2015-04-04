package se.atrosys.service.image.service;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import rx.Observable;
import se.atrosys.birds.common.model.Image;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FlickrService {
	private final String APIKEY = "2ae735c1073a6524c6af82dd4a0da37c";
	private final String SECRET = "43bbaa4345802f59";
	private final String FLICKR_URL = String.format("http://api.flickr.com/services/rest/?api_key=%s&method={flickrmethod}&tags={tags}", APIKEY);
//	private final OAuthRestTemplate oAuthRestTemplate;
	private final RestTemplate restTemplate;

	public FlickrService() {
		restTemplate = new RestTemplate();
	}

	public Observable<List<Image>> getImages(String id) {
		final String url = String.format("https://api.flickr.com/services/rest/");
		final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);

		final MultiValueMap<String, String> urlVariables = new LinkedMultiValueMap<>();

		urlVariables.put("method", Collections.singletonList("flickr.photos.search"));
		urlVariables.put("text", Collections.singletonList(id));
		urlVariables.put("api_key", Collections.singletonList(APIKEY));
		urlVariables.put("nojsoncallback", Collections.singletonList("1"));
		urlVariables.put("format", Collections.singletonList("json"));
		urlVariables.put("per_page", Collections.singletonList("3"));

		uriComponentsBuilder.queryParams(urlVariables);

		DTO dto = restTemplate.getForObject(uriComponentsBuilder.build(false).toUriString(), FlickrService.DTO.class);

		if (dto != null && dto.photos != null && dto.photos.photo != null) {
			return Observable.just(dto.photos.photo.stream()
					.map(photo -> createImage(id, photo))
					.collect(Collectors.toList()));
		}
		return Observable.empty();
	}

	private Image createImage(String birdId, DTO.Photos.Photo photo) {
		return Image.builder()
				.withId(photo.id)
				.withUrl(constructUrl(photo))
				.withBirdId(birdId)
				.build();
	}

	protected String constructUrl(DTO.Photos.Photo photo) {
		final String uri = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri);

		Map<String, Object> map = new HashMap<>();

		map.put("farm-id", photo.farm);
		map.put("server-id", photo.server);
		map.put("id", photo.id);
		map.put("secret", photo.secret);

		final String s = builder.buildAndExpand(map).toString();
		return s;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class DTO {
		public String stat;
		public Photos photos;

		public static class Photos {
			public int total;
			public int page;
			public int pages;
			public int perpage;
			public List<Photo> photo;

			public static class Photo {
				public String owner;
				public String server;
				public String id;
				public String title;
				public String secret;
				public boolean ispublic;
				public boolean isfriend;
				public boolean isfamily;
				public int farm;
			}
		}
	}
}
