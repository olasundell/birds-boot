package se.atrosys.service.image.service;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import se.atrosys.birds.common.model.Image;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FlickrService {
	private final String APIKEY = "2ae735c1073a6524c6af82dd4a0da37c";
	private final String SECRET = "43bbaa4345802f59";
	private final String FLICKR_URL = String.format("http://api.flickr.com/services/rest/?api_key=%s&method={flickrmethod}&tags={tags}", APIKEY);
//	private final OAuthRestTemplate oAuthRestTemplate;
	private final RestTemplate restTemplate;

	public FlickrService() {
		restTemplate = new RestTemplate();

//		final HttpComponentsAsyncClientHttpRequestFactory requestFactory = new HttpComponentsAsyncClientHttpRequestFactory();
//		final BaseProtectedResourceDetails protectedResourceDetails = new BaseProtectedResourceDetails();
//		final CoreOAuthConsumerSupport coreOAuthConsumerSupport = new CoreOAuthConsumerSupport();
//
//		final OAuthClientHttpRequestFactory oAuthClientHttpRequestFactory = new OAuthClientHttpRequestFactory(requestFactory,
//				protectedResourceDetails,
//				coreOAuthConsumerSupport);
//
//		oAuthRestTemplate = new OAuthRestTemplate(oAuthClientHttpRequestFactory, protectedResourceDetails);
	}

	public Image getImage(String id) {
//		oAuthRestTemplate.getForEntity()

		final String url = String.format("https://api.flickr.com/services/rest/");
		final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);


		//?method=flickr.photos.search&text=%s&api_key=%s&format=json&nojsoncallback=1",
//				"Troglodytes troglodytes",
//				APIKEY);

		final MultiValueMap<String, String> urlVariables = new LinkedMultiValueMap<>();

		urlVariables.put("method", Collections.singletonList("flickr.photos.search"));
		urlVariables.put("text", Collections.singletonList(id));
		urlVariables.put("api_key", Collections.singletonList(APIKEY));
		urlVariables.put("nojsoncallback", Collections.singletonList("1"));
		urlVariables.put("format", Collections.singletonList("json"));
		urlVariables.put("per_page", Collections.singletonList("3"));

		uriComponentsBuilder.queryParams(urlVariables);

		DTO dto = restTemplate.getForObject(uriComponentsBuilder.build(false).toUriString(), FlickrService.DTO.class);

		return new Image(dto.photos.photo.get(0).id, constructUrl(dto.photos.photo.get(0)), id);
	}

	protected String constructUrl(DTO.Photos.Photo photo) {
		final String uri = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri);

		Map<String, Object> map = new HashMap<>();

		map.put("farm-id", photo.farm);
		map.put("server-id", photo.server);
		map.put("id", photo.id);
		map.put("secret", photo.secret);

		return builder.buildAndExpand(map).toString();
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
//					"photo" : [
//			{
//				"owner" : "63223331@N05",
//					"server" : "7595",
//					"id" : "16772157399",
//					"title" : "Wren (Troglodytes troglodytes)",
//					"secret" : "bd6567a7c5",
//					"ispublic" : 1,
//					"isfriend" : 0,
//					"farm" : 8,
//					"isfamily" : 0
//			}
//			],
//					"perpage" : 1
		}
	}
}
