package br.com.utfpr.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
	
	private List<Pedido> pedidos = new ArrayList<>();
	
	@Override
	protected void setId(Long id) {
		super.setId(id);
	}	
}
