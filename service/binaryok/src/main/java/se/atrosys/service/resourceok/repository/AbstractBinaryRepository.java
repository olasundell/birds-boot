package se.atrosys.service.resourceok.repository;

import org.springframework.data.repository.CrudRepository;
import se.atrosys.birds.common.model.AbstractBinary;

public interface AbstractBinaryRepository <T extends AbstractBinary> extends CrudRepository<T, String> { }
