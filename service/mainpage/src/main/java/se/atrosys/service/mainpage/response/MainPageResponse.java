package se.atrosys.service.mainpage.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import se.atrosys.birds.common.model.AbstractBinary;
import se.atrosys.birds.common.model.Bird;
import se.atrosys.service.common.response.AbstractResponse;
import se.atrosys.service.mainpage.model.MainPage;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonDeserialize(builder = MainPageResponse.Builder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MainPageResponse extends AbstractResponse<MainPage> {
	private MainPageResponse(Builder builder) {
		this.models.addAll(builder.mainPages);
	}

	public List<MainPage> getMainPage() {
		return Collections.unmodifiableList(models);
	}

	@Override
	protected String getLinkPrefix() {
		return "mainpage";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private final List<MainPage> mainPages = new ArrayList<>();

		private Builder() {}

		public Builder withMainPages(List<MainPage> mainPages) {
			this.mainPages.clear();
			this.mainPages.addAll(mainPages);

			return this;
		}

		public MainPageResponse build() {
			return new MainPageResponse(this);
		}
	}
}
