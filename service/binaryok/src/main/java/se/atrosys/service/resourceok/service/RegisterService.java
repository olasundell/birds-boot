package se.atrosys.service.resourceok.service;

import org.springframework.stereotype.Component;
import se.atrosys.service.resourceok.resource.OkResource;
import se.atrosys.service.resourceok.response.OkPostResponse;

@Component
public class RegisterService {
	public OkPostResponse register(OkResource resource) {
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
