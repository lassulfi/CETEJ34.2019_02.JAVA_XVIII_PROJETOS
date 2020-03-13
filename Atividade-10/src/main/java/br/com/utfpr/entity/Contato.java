package br.com.utfpr.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "CONTATOS")
@NamedQuery(
	name = "Contato.byIdade",
	query = "FROM Contato c WHERE c.idade = ?1")
@NamedNativeQuery(
	name = "Contato.byNome",
	query = "SELECT * FROM CONTATOS WHERE nome LIKE ?1",
	resultClass = Contato.class)
@EntityListeners(AuditingEntityListener.class)
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
	
	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;
	
	@Column(name = "created_date")
	@CreatedDate
	private String createdDate;
	
	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;
	
	@Column(name = "modified_date")
	@LastModifiedDate
	private String modifiedDate;
	
	public Contato() {}
	
	public Contato(String nome, Integer idade, Date dtCadastro) {
		this.nome = nome;
		this.idade = idade;
		this.dtCadastro = dtCadastro;
	}
	
	public Contato(String nome, Integer idade, Date dtCadastro, Endereco endereco) {
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
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
