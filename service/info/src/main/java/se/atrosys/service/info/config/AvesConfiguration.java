package se.atrosys.service.info.config;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.atrosys.birds.common.model.Aves;
import se.atrosys.birds.excel.ReadExcel;

import java.io.IOException;

@Configuration
public class AvesConfiguration {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	public Aves aves() {
		try {
			return new ReadExcel().read();
		} catch (IOException | OpenXML4JException | XmlException e) {
			logger.error("Something horrible happened while trying to read birds.", e);
		}

		return null;
	}
}
