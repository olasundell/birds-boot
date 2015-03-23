package se.atrosys.service.mainpage.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.service.mainpage.factory.MainPageResponseFactory;
import se.atrosys.service.mainpage.response.MainPageResponse;

@RestController
public class MainPageResource {
	@Autowired
	MainPageResponseFactory mainPageResponseFactory;

	@RequestMapping("/mainpage/")
	public MainPageResponse mainPage(@RequestParam(value = "randseed", required = false, defaultValue = "0") int randseed) {
		return mainPageResponseFactory.createResponse(randseed);
	}
}
