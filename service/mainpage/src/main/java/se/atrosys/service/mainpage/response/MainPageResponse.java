package se.atrosys.service.mainpage.response;

import com.google.common.base.Preconditions;
import se.atrosys.birds.common.model.AbstractBinary;
import se.atrosys.birds.common.model.Bird;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MainPageResponse {
	@NotNull
	private final Bird bird;

	@NotNull
	private final List<Bird> alternatives;

	@NotNull
	private final AbstractBinary binary;

	private MainPageResponse(Builder builder) {
		this.bird = Preconditions.checkNotNull(builder.bird);
		this.alternatives = Preconditions.checkNotNull(builder.alternatives);
		this.binary = Preconditions.checkNotNull(builder.binary);
	}

	public Bird getBird() {
		return bird;
	}

	public List<Bird> getAlternatives() {
		return alternatives;
	}

	public AbstractBinary getBinary() {
		return binary;
	}

	public static class Builder {
		private Bird bird;
		private List<Bird> alternatives;
		private AbstractBinary binary;

		public Builder bird(Bird bird) {
			this.bird = bird;
			return this;
		}

		public Builder alternatives(List<Bird> alternatives) {
			this.alternatives = alternatives;
			return this;
		}

		public Builder binary(AbstractBinary binary) {
			this.binary = binary;
			return this;
		}

		public Builder fromPrototype(MainPageResponse prototype) {
			bird = prototype.bird;
			alternatives = prototype.alternatives;
			binary = prototype.binary;
			return this;
		}

		public MainPageResponse build() {
			return new MainPageResponse(this);
		}
	}
}
