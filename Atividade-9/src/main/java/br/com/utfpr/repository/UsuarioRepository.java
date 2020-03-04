package br.com.utfpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import br.com.utfpr.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Procedure(procedureName = "procedure_soma")
	Integer procedureSome(Integer arg);
}
