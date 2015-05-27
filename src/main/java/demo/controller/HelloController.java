package demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/unprotected")
	public String unprotected() {
		return "Should be unprotected";
	}

	@RequestMapping("/protected")
	public String yesProtected() {
		return "should be protected by OAuth2";
	}

}
