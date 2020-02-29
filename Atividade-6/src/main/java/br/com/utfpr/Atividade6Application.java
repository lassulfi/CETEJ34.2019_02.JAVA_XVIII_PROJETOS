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
import br.com.utfpr.entity.projection.CidadeTotal;
import br.com.utfpr.entity.projection.NomeCidade;
import br.com.utfpr.entity.projection.SemEndereco;
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
		enderecoRepository.deleteAll();
		contatoRepository.deleteAll();
		
		List<Endereco> enderecos = Arrays.asList(
			new Endereco(TipoEndereco.RESIDENCIAL, "Rua Um", "Cidade Um", "SP"),
			new Endereco(TipoEndereco.RESIDENCIAL, "Rua Dois", "Cidade Dois", "SP"),
			new Endereco(TipoEndereco.RESIDENCIAL, "Rua Três", "Cidade Três", "SP"),
			new Endereco(TipoEndereco.RESIDENCIAL, "Rua Quatro", "Cidade Quatro", "SP")
		);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
		
		List<Contato> contatos = Arrays.asList(
			new Contato("Ana da Silva", 30, formatter.parse("2013-10-05"), enderecos.get(0)),
			new Contato("Eduardo Coelho", 25, formatter.parse("2013-11-14"), enderecos.get(1)),
			new Contato("Juliana Boemo", 39, formatter.parse("2014-04-21"), enderecos.get(2)),
			new Contato("Aniele Vicentini da Silva", 23, formatter.parse("2014-08-18"), enderecos.get(3))
		);
		
		for(int i = 0; i < 4; i++) {
			enderecos.get(i).setContato(contatos.get(i));			
		}
		
		contatoRepository.saveAll(contatos);
		enderecoRepository.saveAll(enderecos);
		
		// Recupera o primeiro registro
		Contato primeiroRegistroContato = contatoRepository.findFirstBy();
		System.out.println("Primeiro registro: " + primeiroRegistroContato);
		
		// Recupera os 3 primeiros contatos ordenados por idade
		List<Contato> primeiros3RegistrosOrdenadosPorIdadeContatos = contatoRepository.findFirst3ByOrderByIdadeAsc();
		System.out.println("Recupera o primeiro contato ordenado por idade");
		primeiros3RegistrosOrdenadosPorIdadeContatos.forEach(System.out::println);
		
		// Recupera os contato de maior idade
		List<Contato> contatosMaiorIdade = contatoRepository.findByContatoMaiorIdade();
		System.out.println("Recupera os contato de maior idade");
		contatosMaiorIdade.forEach(System.out::println);
		
		// Recupera uma lista de contatos com uma data superior a uma data informada
		List<Contato> contatosCadastratosAposData = contatoRepository.findByDtCadastroAfter(formatter.parse("2014-01-01"));
		System.out.println("Recupera uma lista de contatos com uma data superior a uma data informada");
		contatosCadastratosAposData.forEach(System.out::println);
		
		// Recupera contatos por uma data de cadastro
		List<Contato> contatosByDataCadastro = contatoRepository.findByDataCadastro(formatter.parse("2013-11-13"));
		System.out.println("Recupera contatos por uma data de cadastro");
		contatosByDataCadastro.forEach(System.out::println);
		
		// Recupera uma lista de contatos por idade
		List<Contato> contatosPorIdade = contatoRepository.findByIdade(23);
		System.out.println("Recupera uma lista de contatos por idade");
		contatosPorIdade.forEach(System.out::println);
		
		// Recupera um contato por nome
		Contato contatoPorNome = contatoRepository.findByNome("Juliana Boemo");
		System.out.println("Recupera um contato por nome");
		System.out.println(contatoPorNome);
		
		// Recupera um contato sem trazer o endereco
		SemEndereco contatoByNomeSemEndereco = contatoRepository.findContatoByNome("Juliana Boemo");
		System.out.println("Recupera um contato por nome");
		System.out.println("Nome: " + contatoByNomeSemEndereco.getNome() 
			+ ", idade: " + contatoByNomeSemEndereco.getIdade() 
			+ ", data de cadastro: " + contatoByNomeSemEndereco.getDtCadastro());
		
		// Recupera a cidade do contato consultando pelo nome do contato
		NomeCidade contatoCidadeByNome = contatoRepository.findContatoCidadeByNome("Juliana Boemo");
		System.out.println("Recupera a cidade do contato consultando pelo nome do contato");
		System.out.println("Nome do contato: " + contatoCidadeByNome.getNome() 
			+ "Nome cidade: " + contatoCidadeByNome.getCidade());
		
		// Recupera o numero de cidades e o número de vezes que ela foi cadastrada
		List<CidadeTotal> cidadesTotais = enderecoRepository.findByCidadeTotal();
		System.out.println("Recupera o numero de cidades e o número de vezes que ela foi cadastrada");
		cidadesTotais.forEach(cidade -> System.out.println("Nome da cidade: " + cidade.getCidade() 
			+ ", total: " + cidade.getTotal()));
	}
}
