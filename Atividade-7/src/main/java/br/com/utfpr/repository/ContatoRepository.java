package br.com.utfpr.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.utfpr.entity.Contato;
import br.com.utfpr.entity.projection.NomeCidade;
import br.com.utfpr.entity.projection.SemEndereco;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
 
	Contato findByNomeAndIdade(String nome, Integer idade);
	
	List<Contato> findByEnderecoCidade(String cidade);
	
	Contato findFirstBy();
	
	Contato findTopByOrderByIdadeAsc();
	
	List<Contato> findFirst3ByOrderByIdadeAsc();
	
	@Query("SELECT c FROM Contato c WHERE idade >= 18 ORDER BY nome ASC")
	List<Contato> findByContatoMaiorIdade();
	
	@Query("SELECT c FROM Contato c WHERE c.dtCadastro > ?1")
	List<Contato> findByDtCadastroAfter(Date dataCadastro);
	
	@Query(value = "SELECT * FROM CONTATOS WHERE data_cadastro = ?1", nativeQuery = true)
	List<Contato> findByDataCadastro(Date dataCadastro);
	
	@Query(name = "Contato.byIdade")
	List<Contato> findByIdade(Integer idade);
	
	@Query(name = "Contato.byNome")
	Contato findByNome(String nome);
	
	SemEndereco findContatoByNome(String nome);
	
	NomeCidade findContatoCidadeByNome(String nome);
	
	@Query("SELECT c FROM Contato c WHERE idade >= :idade OR nome LIKE :nome")
	List<Contato> findByIdadeOuNome(@Param("idade") Integer idade, @Param("nome") String nome);
}
