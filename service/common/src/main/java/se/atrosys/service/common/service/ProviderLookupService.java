package se.atrosys.service.common.service;

import org.springframework.stereotype.Component;

@Component
public class ProviderLookupService {
	public String getHref(ServiceProvider p) {
		switch (p) {
		case SOUND:
			return "sound";
		case INFO:
			return "info";
		default:
			return "none";
		}
//		return "http://localhost";
	}
}
