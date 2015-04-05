package se.atrosys.birds.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public abstract class AbstractBinary implements Model {
	private boolean ok = true;

	@JsonIgnore
	public abstract String getBirdId();
	@JsonIgnore
	@Id
	public abstract String getId();

	public Boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
