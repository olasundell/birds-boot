package se.atrosys.birds.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractBinary implements Model {
	@JsonIgnore
	public abstract String getId();
}
