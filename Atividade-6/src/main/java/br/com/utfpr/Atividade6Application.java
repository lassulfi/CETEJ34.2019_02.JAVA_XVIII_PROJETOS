package br.com.utfpr;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.utfpr.entity.Contato;
import br.com.utfpr.entity.Endereco;
import br.com.utfpr.entity.Endereco.TipoEndereco;
import br.com.utfpr.repository.ContatoRepository;
import br.com.utfpr.repository.EnderecoRepository;

@SpringBootApplication
public class Atividade6Application implements CommandLineRunner {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Atividade6Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Endereco> enderecos = Arrays.asList(
			new Endereco(TipoEndereco.RESIDENCIAL, "Rua Um", "Cidade Um", "Estado Um"),
			new Endereco(TipoEndereco.RESIDENCIAL, "Rua Dois", "Cidade Dois", "Estado Dois"),
			new Endereco(TipoEndereco.RESIDENCIAL, "Rua Três", "Cidade Três", "Estado Três"),
			new Endereco(TipoEndereco.RESIDENCIAL, "Rua Quatro", "Cidade Quatro", "Estado Quatro")
		);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		enderecoRepository.saveAll(enderecos);
		
		List<Contato> contatos = Arrays.asList(
			new Contato("Ana da Silva", 30, formatter.parse("2013-10-05"), enderecos.get(0)),
			new Contato("Eduardo Coelho", 25, formatter.parse("2013-11-14"), enderecos.get(1)),
			new Contato("Juliana Boemo", 39, formatter.parse("2014-04-21"), enderecos.get(2)),
			new Contato("Aniele Vicentini da Silva", 23, formatter.parse("2014-08-18"), enderecos.get(3))
		);
		
		contatoRepository.saveAll(contatos);
	}
}
