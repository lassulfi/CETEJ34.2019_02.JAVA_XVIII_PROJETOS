package br.com.utfpr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.utfpr.entity.Endereco;
import br.com.utfpr.entity.projection.CidadeTotal;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query("SELECT e.cidade AS cidade, "
			+ "count(e.cidade) AS total "
			+ "FROM Endereco e "
			+ "GROUP BY e.cidade")
	List<CidadeTotal> findByCidadeTotal();
	
	@Procedure("proc_endereco")
	String procedureEndereco(Long id);
	
	@Modifying
	@Query("UPDATE Endereco e SET e.cidade = ?1 WHERE e.id = ?2")
	Integer updateCidadeById(String cidade, Long id);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("DELETE FROM Endereco e WHERE e.id = ?1")
	Integer deleteEndereco(Long id);
}
