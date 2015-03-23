package se.atrosys.service.common.config;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.atrosys.service.common.Cache;

import java.util.List;

@EnableCaching
@Configuration
public abstract class AbstractCacheConfiguration implements CachingConfigurer {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public abstract List<Cache> enabledCaches();

	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager() {
		// TODO add configuration for the specific caches that we use.
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();

		for (Cache c: enabledCaches()) {
			logger.info("Creating cache {}", c.name());
			config.addCache(new CacheConfiguration()
					.name(c.getCacheName())
					.timeToLiveSeconds(c.getTimeToLiveSeconds())
					.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)
					.maxEntriesLocalHeap(c.getMaxEntriesLocalHeap()));
		}

		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}

	@Override
	public CacheResolver cacheResolver() {
		return new SimpleCacheResolver(cacheManager());
	}

	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return new SimpleCacheErrorHandler();
	}
}
