package br.org.generation.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Annotation que indica que e uma classe controller
public class HelloController {
	
	@GetMapping ("/hello")
	public String sayHello(){
		
		return "hello world!"
				+ "\nPersistencia!"
				+ "\nAtenção ao detalhe!";
	}

}
