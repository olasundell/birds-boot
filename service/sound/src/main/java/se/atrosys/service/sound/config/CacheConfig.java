package se.atrosys.service.sound.config;

import org.springframework.context.annotation.Configuration;
import se.atrosys.service.common.Cache;
import se.atrosys.service.common.config.AbstractCacheConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
public class CacheConfig extends AbstractCacheConfiguration {
	@Override
	public List<Cache> enabledCaches() {
		return Collections.singletonList(Cache.SOUND_CACHE);
	}
}
