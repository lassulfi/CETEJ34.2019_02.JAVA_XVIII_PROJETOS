package br.com.utfpr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "funcionario")
public class Funcionario extends AbstractPersistable<Long> {

	@Column(name = "nome", length = 80, nullable = false)
	private String nome;
	
	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;
	
	@Column(name = "rg", length = 9, nullable = false)
	private String rg;
	
	@Column(name="data_nascimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(name = "salario", nullable = false)
	private Float salario;
	
	@Column(name = "quantidade_dependentes", nullable = false)
	private Integer quantidadeDependentes;
	
	public Funcionario() {
	}

	public Funcionario(String nome, String cpf, String rg, Date dataNascimento, Float salario,
			Integer quantidadeDependentes) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
		this.quantidadeDependentes = quantidadeDependentes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Float getSalario() {
		return salario;
	}

	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public Integer getQuantidadeDependentes() {
		return quantidadeDependentes;
	}

	public void setQuantidadeDependentes(Integer quantidadeDependentes) {
		this.quantidadeDependentes = quantidadeDependentes;
	}
	
	@Override
	protected void setId(Long id) {
		super.setId(id);
	}	
}
