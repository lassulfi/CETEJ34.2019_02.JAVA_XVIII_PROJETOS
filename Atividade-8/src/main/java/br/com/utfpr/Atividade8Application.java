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
import br.com.utfpr.service.EnderecoService;

@SpringBootApplication
public class Atividade8Application implements CommandLineRunner {
	
	@Autowired
	private EnderecoService enderecoService;
	
	public static void main(String[] args) {
		SpringApplication.run(Atividade8Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Endereco endereco1 = new Endereco(TipoEndereco.RESIDENCIAL, "Rua Um", "Cidade Um", "SP");
		Endereco endereco2 = new Endereco(TipoEndereco.RESIDENCIAL, "Rua Dois", "Cidade Dois", "SP");
		Endereco endereco3 = new Endereco(TipoEndereco.RESIDENCIAL, "Rua Três", "Cidade Três", "SP");
		Endereco endereco4 = new Endereco(TipoEndereco.RESIDENCIAL, "Rua Quatro", "Cidade Quatro", "SP");
				
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
		
		Contato contato1 = new Contato("Ana da Silva", 30, formatter.parse("2013-10-05"));
		Contato contato2 = new Contato("Eduardo Coelho", 25, formatter.parse("2013-11-14"));
		Contato contato3 = new Contato("Juliana Boemo", 39, formatter.parse("2014-04-21"));
		Contato contato4 = new Contato("Aniele Vicentini da Silva", 23, formatter.parse("2014-08-18"));
				
		// Consultas da Atividade 8
		// Salva um endereco usando a classe EnderecoService
		this.enderecoService.salvar(endereco1, contato1);
		
	}
}
