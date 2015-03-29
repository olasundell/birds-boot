package se.atrosys.service.resourceok.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.atrosys.birds.common.model.AbstractBinary;
import se.atrosys.service.resourceok.response.OkPostResponse;
import se.atrosys.service.resourceok.service.RegisterService;

@RestController
public class OkResource {
	@Autowired
	RegisterService registerService;

	@RequestMapping(value = "/ok/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String registerOk(@PathVariable String id) {
		registerService.registerOk(id);
		return id;
	}

	@RequestMapping(value = "/notok/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String registerNotOtk(@PathVariable String id) {
		registerService.registerNotOk(id);
		return id;
	}

	@RequestMapping(value = "/isok/{id}", method = RequestMethod.GET)
	public boolean isOk(@PathVariable String id) {
		return registerService.isOk(id);
	}

	@RequestMapping(value = "/register/", method = RequestMethod.POST)
	@ResponseBody
	public OkPostResponse isOk(@RequestBody AbstractBinary resource) {
		return registerService.register(resource);
	}
}
