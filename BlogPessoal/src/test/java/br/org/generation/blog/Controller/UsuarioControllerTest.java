package br.org.generation.blog.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.org.generation.blog.model.UserLogin;
import br.org.generation.blog.model.Usuario;
import br.org.generation.blog.repository.UsuarioRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UsuarioControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario, usuarioupd;
	
	@BeforeAll
	public void start() {
		usuarioRepository.deleteAll();
		usuario = new Usuario("Gustavo","gustavo.novaes@hotmail.com","gustavo20");
		usuarioupd = new Usuario("Gustavo Novaes","gustavo.novaes@hotmail.com","gustavo20");
	}

	@Test
	@Order(1)
	public void testCadastrar() {
		System.out.println("/////////////////////////////////// 1 ///////////////////////////////////");
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
		
		ResponseEntity<Usuario> resposta = 
				testRestTemplate
				.exchange("/usuario/cadastrar", HttpMethod.POST,request,Usuario.class);
		
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void testLogar() { // Método iniciando primeiro que os demais??? ordem: 2,1,3
		System.out.println("/////////////////////////////////// 2 ///////////////////////////////////");
		UserLogin usuariologin = new UserLogin(usuario);
		HttpEntity<UserLogin> request = new HttpEntity<UserLogin>(usuariologin);
		
		ResponseEntity<UserLogin> resposta = 
				testRestTemplate
				.exchange("/usuario/logar", HttpMethod.POST,request,UserLogin.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	@Order(3)
	public void testGetAll() {
		System.out.println("/////////////////////////////////// 3 ///////////////////////////////////");
		ResponseEntity<String> resposta = 
				testRestTemplate.exchange("/usuario", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.UNAUTHORIZED, resposta.getStatusCode());
		// Para retornar OK é preciso passar o token na header;
	}
	
}