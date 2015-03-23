package se.atrosys.service.common.response;

public class Link {
	private final String href;
	private final String type;

	protected Link(Builder builder) {
		this.href = builder.href;
		this.type = builder.type;
	}

	public String getHref() {
		return href;
	}

	public String getType() {
		return type;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String href;
		private String type;

		public Link build() {
			return new Link(this);
		}

		public Builder withHref(String href) {
			this.href = href;

			return this;
		}

		public Builder withType(String type) {
			this.type = type;

			return this;
		}
	}
}
