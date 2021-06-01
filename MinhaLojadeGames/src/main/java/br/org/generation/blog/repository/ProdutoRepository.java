package br.org.generation.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.blog.model.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	public List<Produto> findAllByNomeJogoContainingIgnoreCase( String nomeJogo);

}
