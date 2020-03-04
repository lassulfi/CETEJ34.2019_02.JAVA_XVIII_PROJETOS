package br.com.utfpr;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.utfpr.entity.Contato;
import br.com.utfpr.repository.ContatoRepository;

@SpringBootApplication
public class Atividade9Application implements CommandLineRunner {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Atividade9Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
						
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
		
		Contato contato1 = new Contato("Ana da Silva", 30, formatter.parse("2013-10-05"));
				
		contatoRepository.save(contato1);		
	}
}
