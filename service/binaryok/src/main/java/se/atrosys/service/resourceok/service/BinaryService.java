package se.atrosys.service.resourceok.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.common.model.AbstractBinary;
import se.atrosys.birds.common.model.Image;
import se.atrosys.birds.common.model.Sound;
import se.atrosys.service.resourceok.repository.AbstractBinaryRepository;
import se.atrosys.service.resourceok.repository.ImageRepository;
import se.atrosys.service.resourceok.repository.SoundRepository;

@Component
public class BinaryService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	SoundRepository soundRepository;

	public boolean registerOrCheckIfOK(Image image) {
		return registerOrCheckIfOK(image, imageRepository);
	}

	public boolean registerOrCheckIfOK(Sound sound) {
		return registerOrCheckIfOK(sound, soundRepository);
	}

	private <T extends AbstractBinary> boolean registerOrCheckIfOK(T binary, AbstractBinaryRepository<T> repository) {
		logger.info("Checking if {} is OK", binary.getId());
		if (repository.exists(binary.getId())) {
			final T one = repository.findOne(binary.getId());

			logger.info("It's already registered, is it OK? {}", one.isOk());

			return one.isOk();
		}

		logger.info("It's the first time we've seen it, saving");
		repository.save(binary);

		return binary.isOk();
	}
}
