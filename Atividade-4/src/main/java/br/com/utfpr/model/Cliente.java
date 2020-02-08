package br.com.utfpr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "cliente")
public class Cliente extends AbstractPersistable<Long> {

	@Column(name = "nome", length = 150, nullable = false)
	private String nome;
	
	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;
	
	@Column(name = "rg", length = 9, nullable = false)
	private String rg;
	
	@Column(name = "telefone_fixo", length = 10, nullable = false)
	private String telefoneFixo;
	
	@Column(name = "telefone_celular", length = 11, nullable = false)
	private String telefoneCelular;
	
	@Column(name = "data_nascimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
		
	public Cliente() {
	}

	public Cliente(String nome, String cpf, String rg, String telefoneFixo, String telefoneCelular,
			Date dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.telefoneFixo = telefoneFixo;
		this.telefoneCelular = telefoneCelular;
		this.dataNascimento = dataNascimento;
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

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}	
}
