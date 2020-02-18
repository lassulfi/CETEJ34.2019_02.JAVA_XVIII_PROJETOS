package br.com.utfpr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "ENDERECOS")
public class Endereco extends AbstractPersistable<Long> {

	public enum TipoEndereco{
		RESIDENCIAL, COMERCIAL
	}
	
	@Column(name = "tipo", length = 32, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipoEndereco;
	
	@Column(name = "logradouro", length = 64, nullable = false)
	private String logradouro;
	
	@Column(name = "cidade", length = 64, nullable = false)
	private String cidade;
	
	@Column(name = "estado", length = 2, nullable = false)
	private String estado;

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}
}
