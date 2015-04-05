package se.atrosys.service.resourceok.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("se.atrosys.service.resourceok.repository")
public class MongoConfig extends AbstractMongoConfiguration {
	@Override
	protected String getDatabaseName() {
		return "birds";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient();
	}

	@Override
	protected String getMappingBasePackage() {
		return "se.atrosys.service.resourceok.repository";
	}
}
