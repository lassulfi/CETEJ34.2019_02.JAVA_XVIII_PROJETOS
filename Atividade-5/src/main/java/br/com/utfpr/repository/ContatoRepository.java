package br.com.utfpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.utfpr.entity.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
