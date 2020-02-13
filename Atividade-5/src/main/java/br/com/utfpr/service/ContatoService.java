package br.com.utfpr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.utfpr.entity.Contato;
import br.com.utfpr.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repository;
	
	public void salvar(Contato contato) {
		repository.save(contato);
	}
	
	public Optional<Contato> buscarPorId(Long id) {
		return repository.findById(id);
	}
	
	public List<Contato> buscarTodos() {
		return repository.findAll();
	}
	
	public List<Contato> buscarPorIdade(Integer idade) {
		
		Contato contato = new Contato();
		contato.setIdade(idade);
		Example<Contato> example = Example.of(contato);
		
		return repository.findAll(example);
	}
	
	public List<Contato> buscarPorNome(String nome) {
		Contato contato = new Contato();
		contato.setNome(nome);
		
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.ENDING);
		
		Example<Contato> example = Example.of(contato, matcher);
		
		return repository.findAll(example);
	}
	
	public List<Contato> buscarTodosPorIdadeAsc() {
		
		Sort sort = new Sort(Direction.ASC, "idade");
		
		return repository.findAll(sort);
	}
	
	public Page<Contato> paginaResultados() {
		
		Sort sort = new Sort(Direction.ASC, "nome");
		Pageable pageable = PageRequest.of(0, 3, sort);
		
		return repository.findAll(pageable);
	}
}
