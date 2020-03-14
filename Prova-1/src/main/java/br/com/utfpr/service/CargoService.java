package br.com.utfpr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.utfpr.entity.Cargo;
import br.com.utfpr.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	
	public void salvarCargos(List<Cargo> cargos) throws Exception {
		if(cargos.size() < 3) throw new Exception("Quantidade de funcionarios inferior a 3");
		
		cargoRepository.saveAll(cargos);
	}
	
	public Cargo atualizarCargo(Cargo cargo) throws Exception {
		Cargo entity = cargoRepository.findById(cargo.getId()).orElseThrow(() -> new Exception("Cargo não cadastrado"));
	
		entity.setNome(cargo.getNome());
		
		return cargoRepository.save(entity);
	}
	
	public void excluirCargoPorId(Long id) {
		cargoRepository.deleteById(id);
	}
	
	public void listarCargos() {				
		cargoRepository.findAll().forEach(System.out::println);
	}
}
