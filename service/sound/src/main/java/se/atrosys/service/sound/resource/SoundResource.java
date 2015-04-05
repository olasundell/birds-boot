package se.atrosys.service.sound.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.birds.common.formatter.BirdNameFormatter;
import se.atrosys.service.sound.factory.SoundResponseFactory;
import se.atrosys.service.common.response.SoundResponse;

import java.util.concurrent.Callable;

@RestController
public class SoundResource {
	@Autowired
	SoundResponseFactory soundResponseFactory;
	@Autowired
	BirdNameFormatter birdNameFormatter;

	@RequestMapping("/sound/{ids}")
	public Callable<SoundResponse> sound(@PathVariable("ids") String ids,
	                                     @RequestParam(value = "limit", required = false, defaultValue = "3") Integer limit) {
		return () -> soundResponseFactory.createResponse(birdNameFormatter.formatNames(ids), limit).toBlocking().single();
	}
}
