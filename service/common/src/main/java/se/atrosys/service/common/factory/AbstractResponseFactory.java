package se.atrosys.service.common.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.common.model.Aves;

@Component
public abstract class AbstractResponseFactory {
	@Autowired
	protected Aves aves;

	public void setAves(Aves aves) {
		this.aves = aves;
	}
}
