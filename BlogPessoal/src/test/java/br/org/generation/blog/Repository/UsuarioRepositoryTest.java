package br.org.generation.blog.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import br.org.generation.blog.model.Usuario;
import br.org.generation.blog.repository.UsuarioRepository;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	public void start() {
		Usuario usuario = new Usuario("Chefe", "0y", "9xxxxxxx9");
		if (UsuarioRepository.findFirstByNome(usuario.getNome()) == null)
			UsuarioRepository.save(usuario);

		usuario = new Usuario("Novo Chefe", "0y", "8xxxxxxx8");
		if (UsuarioRepository.findFirstByNome(usuario.getNome()) == null)
			UsuarioRepository.save(usuario);

		usuario = new Usuario("chefe Mais Antigo", "0y", "7xxxxxxx7");
		if (usuarioRepository.findFirstByNome(usuario.getNome()) == null)
			usuarioRepository.save(usuario);

		usuario = new Usuario(null, "Amigo", "0z", "5xxxxxxx5");
		if (UsuarioRepository.findFirstByNome(usuario.getNome()) == null)
			UsuarioRepository.save(usuario);
	}

	@Test
	public void findByNomeRetornausuario() throws Exception {

		Usuario usuario = UsuarioRepository.findFirstByNome("Chefe");

		assertTrue(usuario.getNome().equals("Chefe"));
	}

	@Test
	public void findAllByNomeIgnoreCaseRetornaTresusuario() {

		List<Usuario> usuarios = UsuarioRepository.findByUsuario("chefe");

		assertEquals(3, usuarios.size());
	}

	@AfterAll
	public void end() {
		UsuarioRepository.deleteAll();
	}
	
}