package br.com.utfpr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "Func")
public class Funcionario extends AbstractPersistable<Long> {

	@Column(name = "Nome", length = 80, nullable = false)
	private String nome;
	
	@Column(name = "Sexo", length = 10, nullable = false)
	private String sexo;
	
	@Column(name = "Telefone", length = 18, nullable = false)
	private String telefone;
	
	@ManyToOne
	private Cargo cargo;
		
	public Funcionario() {
		
	}
	
	public Funcionario(String nome, String sexo, String telefone, Cargo cargo) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.telefone = telefone;
		this.cargo = cargo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	@Override
	public String toString() {
		return "Funcionario: nome = " + nome + ", sexo = " + sexo + 
				", telefone = " + telefone + ", cargo = " + cargo;
	}
		
}
