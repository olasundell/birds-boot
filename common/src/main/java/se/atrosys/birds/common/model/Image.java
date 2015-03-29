package se.atrosys.birds.common.model;

public class Image extends AbstractBinary {
	private String id;
	private String url;
	private String birdId;

	public Image(String id, String url, String birdId) {
		this.id = id;
		this.url = url;
		this.birdId = birdId;
	}

	@Override
	public String getBirdId() {
		return birdId;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}
}
