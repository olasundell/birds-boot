package se.atrosys.birds.common.model;

import java.util.*;

public class Bird implements Model {
	public final static Locale LATIN = new Locale("lat");
	private final Map<Locale, String> names = new HashMap<>();
	private final Family family;
	private final Order order;

	private Bird(Builder builder) {
		names.putAll(builder.names);
		this.family = builder.family;
		this.order = builder.order;
	}

	public static Builder builder() {
		return new Builder();
	}

	public String getName() {
		return names.get(LATIN);
	}

	public Map<Locale, String> getNames() {
		return Collections.unmodifiableMap(names);
	}

	public String getOrder() {
		return order.getName();
	}

	public String getFamily() {
		return family.getName();
	}

	public static final class Builder {
		private final Map<Locale, String> names = new HashMap<>();
		private Order order;
		private Family family;

		private Builder() {
		}

		public Builder withName(Locale locale, String name) {
			this.names.put(locale, name);
			return this;
		}

		public Bird build() {
			return new Bird(this);
		}

		public Builder withOrder(Order order) {
			this.order = order;
			return this;
		}

		public Builder withFamily(Family family) {
			this.family = family;
			return this;
		}
	}
}
