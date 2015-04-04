package se.atrosys.service.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.AsyncRestTemplate;

@Configuration
public class RestTemplateConfiguation {
	@Autowired
	LoadBalancerClient loadBalancerClient;

	@Bean
	public AsyncRestTemplate asyncRibbonRestTemplate() {
		return new AsyncRestTemplate(new RibbonAsyncClientHttpRequestFactory(loadBalancerClient));
	}
}
