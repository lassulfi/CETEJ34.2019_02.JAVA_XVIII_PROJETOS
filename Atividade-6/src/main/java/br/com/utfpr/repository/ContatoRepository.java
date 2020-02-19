package br.com.utfpr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.utfpr.entity.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
 
	Contato findByNomeAndIdade(String nome, Integer idade);
	
	List<Contato> findByEnderecoCidade(String cidade);
	
	Contato findFirstBy();
	
	Contato findTopByOrderByIdadeAsc();
	
	List<Contato> findFirst3ByOrderByIdadeAsc();
	
	@Query("SELECT c FROM Contato c WHERE idade >= 18 ORDER BY nome ASC")
	List<Contato> findByContatoMaiorIdade();
}
