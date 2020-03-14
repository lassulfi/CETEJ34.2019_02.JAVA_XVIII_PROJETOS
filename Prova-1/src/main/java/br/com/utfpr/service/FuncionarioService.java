package br.com.utfpr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.utfpr.entity.Funcionario;
import br.com.utfpr.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public void salvarFuncionarios(List<Funcionario> funcionarios) throws Exception {
		if(funcionarios.size() < 3)
			throw new Exception("Quantidade de funcionarios inferior a 3");
		
		funcionarioRepository.saveAll(funcionarios);
	}
	
	public Funcionario atualizarFuncionario(Funcionario obj) throws Exception {
		Funcionario entity = funcionarioRepository.findById(obj.getId())
				.orElseThrow(() -> new Exception("Id não encontrado"));
	
		if(entity.getCargo().getNome().equals(obj.getCargo().getNome())) 
			throw new Exception("Cargo deve ser diferente do atual");
		
		entity.setNome(obj.getNome());
		entity.setSexo(obj.getSexo());
		entity.setTelefone(obj.getTelefone());
		entity.setCargo(obj.getCargo());
		
		return funcionarioRepository.save(entity);
	}
	
	public void excluirFuncionarioPorId(Long id) {
		funcionarioRepository.deleteById(id);
	}
	
	public void listarFuncionarios() {
		funcionarioRepository.findAll().forEach(System.out::println);
	}
	
	public void listarSomenteNomes() {
		funcionarioRepository.findOnlyNames().forEach(el -> {System.out.println(el.getNome());});
	}
	
	public Long getTotalFuncionarios() {
		return funcionarioRepository.findTotal().getTotal();
	}
}
