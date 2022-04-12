package employeeManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		return ("<h1>Logged In!</h1>"
				+ "<br>"
				+ "<h2>Use Rest API Client/Browser to access endpoints.</h2>");
	}
}
