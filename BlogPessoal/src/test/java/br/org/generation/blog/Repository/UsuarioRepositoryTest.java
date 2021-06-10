package br.org.generation.blog.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import br.org.generation.blog.model.Usuario;
import br.org.generation.blog.repository.UsuarioRepository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	public void start() {
		usuarioRepository.deleteAll();
		
		Usuario usuario = new Usuario("Ana Carol", "hanna","12345678");
		
		if(usuarioRepository.findByUsuario("hanna").isEmpty()) {
			usuarioRepository.save(usuario);
		}
		
		usuario = new Usuario("Ana Thais", "hanna","987654321");
		if(usuarioRepository.findByUsuario("hanna").isEmpty()) {
			usuarioRepository.save(usuario);
		}
		
		usuario = new Usuario("Ana Erica", "hanna","45327891");
		if(usuarioRepository.findByUsuario("hanna").isEmpty()) {
			usuarioRepository.save(usuario);
		}
		
		usuario = new Usuario("Anderson souza", "Andersoncool","jorginho");
		if(usuarioRepository.findByUsuario("Andersoncool").isEmpty()) {
			usuarioRepository.save(usuario);
		}
		
		usuarioRepository.save(usuario);
	}
	
	@Test
	public void testFindByUsuario() throws Exception {
		Optional<Usuario> usuario;
		
		usuario = usuarioRepository.findByUsuario("hanna");
		
		assertTrue(usuario.get().getUsuario().equals("hanna"));
	}
	
	@Test
	public void testFindAllIgnoringCase() throws Exception {
		List<Usuario> usuario = usuarioRepository.findAllByNomeContainingIgnoreCase("anna");
		
		assertEquals(3, usuario.size());
		
	}
	
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
	
	
}