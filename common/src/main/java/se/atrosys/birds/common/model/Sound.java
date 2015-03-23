package se.atrosys.birds.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Sound extends AbstractBinary {
	private String fileUrl;
	private String location;
	private String type;
	private String url;
	private String country;
	private String family;
	private String license;
	private String birdName;
	private String englishName;
	private String xenoCantoId;

	private Sound(Builder builder) {
		fileUrl = builder.fileUrl;
		location = builder.location;
		type = builder.type;
		url = builder.url;
		country = builder.country;
		family = builder.family;
		license = builder.license;
		birdName = builder.birdName;
		englishName = builder.englishName;
		xenoCantoId = builder.xenoCantoId;
	}

	public static Builder builder() {
		return new Builder();
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public String getLocation() {
		return location;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public String getCountry() {
		return country;
	}

	public String getFamily() {
		return family;
	}

	public String getLicense() {
		return license;
	}

	public String getBirdName() {
		return birdName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public String getXenoCantoId() {
		return xenoCantoId;
	}

	@Override
	public String getId() {
		return xenoCantoId;
	}

	public static final class Builder {
		private String fileUrl;
		private String location;
		private String type;
		private String url;
		private String country;
		private String family;
		private String license;
		private String birdName;
		private String englishName;
		private String xenoCantoId;

		private Builder() {
		}

		public Builder withFileUrl(String fileUrl) {
			this.fileUrl = fileUrl;
			return this;
		}

		public Builder withLocation(String location) {
			this.location = location;
			return this;
		}

		public Builder withType(String type) {
			this.type = type;
			return this;
		}

		public Builder withUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder withCountry(String country) {
			this.country = country;
			return this;
		}

		public Builder withFamily(String family) {
			this.family = family;
			return this;
		}

		public Builder withLicense(String license) {
			this.license = license;
			return this;
		}

		public Builder withBirdName(String birdName) {
			this.birdName = birdName;
			return this;
		}

		public Builder withEnglishName(String englishName) {
			this.englishName = englishName;
			return this;
		}

		public Builder withXenoCantoId(String xenoCantoId) {
			this.xenoCantoId = xenoCantoId;
			return this;
		}

		public Sound build() {
			return new Sound(this);
		}
	}
}
