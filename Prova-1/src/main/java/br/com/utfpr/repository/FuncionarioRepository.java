package br.com.utfpr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.utfpr.entity.Funcionario;
import br.com.utfpr.entity.projection.FuncionarioTotal;
import br.com.utfpr.entity.projection.NomeFuncionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

	@Query("SELECT f FROM Funcionario f")
	List<NomeFuncionario> findOnlyNames();
	
	@Query("SELECT COUNT(f) AS total FROM Funcionario f")
	FuncionarioTotal findTotal();
}
