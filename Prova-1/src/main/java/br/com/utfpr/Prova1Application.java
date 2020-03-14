package br.com.utfpr;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.utfpr.entity.Cargo;
import br.com.utfpr.entity.Funcionario;
import br.com.utfpr.service.CargoService;
import br.com.utfpr.service.FuncionarioService;

@SpringBootApplication
public class Prova1Application implements CommandLineRunner {
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private FuncionarioService funcionarioService;

	public static void main(String[] args) {
		SpringApplication.run(Prova1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Cargo> cargos = Arrays.asList(new Cargo("Dev Jr"), 
				new Cargo("Dev Pleno"), 
				new Cargo("Dev Senior"));
		
		
		List<Funcionario> funcionarios = Arrays.asList(
				new Funcionario("Luis Daniel Assulfi", "Masculino", "(19)99928-5624", cargos.get(0)),
				new Funcionario("Marisa dos Santos Amaral", "Feminino", "(19)99928-5624", cargos.get(1)),
				new Funcionario("Joao Teixeira", "Masculino", "(19)99928-5624",  cargos.get(2)));
		
		for(int i = 0; i < 3; i++) {
			cargos.get(i).getFuncionarios().add(funcionarios.get(i));
		}
		
		cargoService.salvarCargos(cargos);
		funcionarioService.salvarFuncionarios(funcionarios);
		
		System.out.println("Listagem de cargos");
		cargoService.listarCargos();
		
		System.out.println("Listagem de funcionarios");
		funcionarioService.listarFuncionarios();
				
		System.out.println("Listagem dos nomes dos funcionarios");
		funcionarioService.listarSomenteNomes();
		
		System.out.printf("Total de funcionarios %d", funcionarioService.getTotalFuncionarios());
	}

}
