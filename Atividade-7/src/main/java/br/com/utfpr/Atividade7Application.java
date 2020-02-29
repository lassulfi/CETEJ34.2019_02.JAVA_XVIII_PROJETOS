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
public class Atividade7Application implements CommandLineRunner {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Atividade7Application.class, args);
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
		
		// Consultas da Atividade 7
		// Retorna uma lista de contatos por idade ou nome
		System.out.println("Retona a lista de contatos por idade ou nome");
		List<Contato> contatosByIdadeOuNome = contatoRepository.findByIdadeOuNome(18, "Eduardo");
		contatosByIdadeOuNome.forEach(System.out::println);
		
		// Atualiza uma cidade por cidade
		System.out.println("Atualiza uma cidade por id");
		Integer updateCidadeById = enderecoRepository.updateCidadeById("Cidade Nova Três", (long) 3);
		if(updateCidadeById == 1) {
			System.out.println("Cidade atualizada");
		} else {
			System.out.println("Erro ao atualizaar cidade");
		}
		
		// Exclui um endereco
		System.out.println("Exclui um endereco por id");
		Integer deleteEndereco = enderecoRepository.deleteEndereco((long) 1);
		if(deleteEndereco == 1) {
			System.out.println("Endereço removido");
		} else {
			System.out.println("Erro ao excluir endereço");
		}
		
	}
}
