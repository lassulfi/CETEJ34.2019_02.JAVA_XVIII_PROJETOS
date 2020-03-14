package br.com.utfpr.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "Cargo")
public class Cargo extends AbstractPersistable<Long> {

	@Column(name = "Cargo", length = 30, nullable = false)
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cargo_id")
	private List<Funcionario> funcionarios = new ArrayList<>();
	
	public Cargo() {
	}

	public Cargo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	@Override
	public String toString() {
		return "Cargo: nome = " + nome;
	}
	
	
}
