package br.com.utfpr.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "CONTATOS")
@NamedQuery(
	name = "Contato.byIdade",
	query = "FROM Contato c WHERE c.idade = ?1")
@NamedNativeQuery(
	name = "Contato.byNome",
	query = "SELECT * FROM CONTATOS WHERE nome LIKE ?1",
	resultClass = Contato.class)
public class Contato extends AbstractPersistable<Long> {

	@Column(name = "nome", length = 64, nullable = false)
	private String nome;
	
	@Column(name = "idade")
	private Integer idade;
	
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	private Date dtCadastro;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;
	
	public Contato() {}
	
	public Contato(String nome, Integer idade, Date dtCadastro, Endereco endereco) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.dtCadastro = dtCadastro;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}	
	
	@Override
	public String toString() {
		return "Contato: nome = " + nome 
				+ ", idade =  " + idade 
				+ ", data de cadastro = " + dtCadastro
				+ "\n\t Endereço: " + endereco;
	}
}
