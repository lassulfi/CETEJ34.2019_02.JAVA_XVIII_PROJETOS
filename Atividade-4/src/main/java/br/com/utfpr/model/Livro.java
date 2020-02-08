package br.com.utfpr.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "livro")
public class Livro extends AbstractPersistable<Long> {

	@Column(name = "titulo", length = 255, nullable = false)
	private String titulo;
	
	@Column(name = "data_lancamento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataLancamento;
	
	@Column(name = "isbn", length = 13, nullable = false)
	private String isbn;
	
	@ManyToMany
	@JoinTable(name = "autor_livro", 
	joinColumns = @JoinColumn(name = "livro_id"),
	inverseJoinColumns = @JoinColumn(name = "autor_id"))
	private List<Autor> autores = new ArrayList<>();

	public Livro() {
	}

	public Livro(String titulo, Date dataLancamento, String isbn) {
		super();
		this.titulo = titulo;
		this.dataLancamento = dataLancamento;
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}
}
