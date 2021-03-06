package br.com.utfpr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.utfpr.entity.Endereco;
import br.com.utfpr.entity.projection.CidadeTotal;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query("SELECT e.cidade AS cidade, "
			+ "count(e.cidade) AS total "
			+ "FROM Endereco e "
			+ "GROUP BY e.cidade")
	List<CidadeTotal> findByCidadeTotal();
}
