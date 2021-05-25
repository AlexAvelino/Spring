package br.org.generation.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping ("/hello1")
	public String sayobjectives() {
		
		return "hello world"
				+ "\nEssa semana eu espero aprender e dominar as ferramentas "
				+ "do Spring e dar um novo passo em minha carreira como "
				+ "desenvolvedor!";
	}

}
