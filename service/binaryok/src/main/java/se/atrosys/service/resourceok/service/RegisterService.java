package se.atrosys.service.resourceok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.common.model.AbstractBinary;
import se.atrosys.service.resourceok.repository.ImageRepository;
import se.atrosys.service.resourceok.response.OkPostResponse;

@Component
public class RegisterService {
	@Autowired
	private ImageRepository repository;

	public OkPostResponse register(AbstractBinary resource) {
//		repository.save(resource);

		return new OkPostResponse();
	}

	public void registerNotOk(String id) {
		final AbstractBinary one = repository.findOne(id);
		one.setOk(false);
//		repository.save(one);
	}

	public void registerOk(String id) {
		final AbstractBinary one = repository.findOne(id);
		one.setOk(true);
//		repository.save(one);
	}

	public boolean isOk(String id) {
		final AbstractBinary one = repository.findOne(id);
		return (one != null && one.isOk());
	}
}
