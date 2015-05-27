package demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/unprotected")
	public String unprotected() {
		return "Should be unprotected, no sign-in needed";
	}

	@RequestMapping("/protected")
	public String yesProtected() {
		return "In order to see this, you should have had to authenticate via an OAuth2 Authorization Server";
	}

}
