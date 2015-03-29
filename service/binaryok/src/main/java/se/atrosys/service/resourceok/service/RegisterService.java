package se.atrosys.service.resourceok.service;

import org.springframework.stereotype.Component;
import se.atrosys.birds.common.model.AbstractBinary;
import se.atrosys.service.resourceok.response.OkPostResponse;

@Component
public class RegisterService {
	public OkPostResponse register(AbstractBinary resource) {
		return null;
	}

	public void registerNotOk(String id) {
	}

	public void registerOk(String id) {
	}

	public boolean isOk(String id) {
		return true;
	}
}
