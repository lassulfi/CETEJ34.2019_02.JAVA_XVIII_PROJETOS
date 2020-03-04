package br.com.utfpr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.utfpr.entity.Contato;
import br.com.utfpr.entity.Endereco;
import br.com.utfpr.repository.ContatoRepository;
import br.com.utfpr.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	public void salvar(Endereco endereco, Contato contato) {
		contato.setEndereco(endereco);
		contatoRepository.save(contato);
		endereco.setContato(contato);
		enderecoRepository.save(endereco);
	}
}
