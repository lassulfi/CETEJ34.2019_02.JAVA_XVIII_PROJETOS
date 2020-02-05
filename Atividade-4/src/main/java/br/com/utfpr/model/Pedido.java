package br.com.utfpr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "pedido")
public class Pedido extends AbstractPersistable<Long> {

	@Column(name = "data_pedido", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataPedido;
	
	@Column(name = "endereco", length = 300, nullable = false)
	private String endereco;
	
	@Column(name = "valor_total", nullable = false)
	private Float valorTotal;
	
	@Column(name = "descricao", length = 300, nullable = false)
	private String descricao;
	
	@Column(name = "relacao_produtos", length = 500, nullable = false)
	private String relacaoProdutos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cliente")
	private Cliente cliente;
	
	public Pedido() {
	}

	public Pedido(Date dataPedido, String endereco, Float valorTotal, 
			String descricao, String relacaoProdutos, Cliente cliente) {
		this.dataPedido = dataPedido;
		this.endereco = endereco;
		this.valorTotal = valorTotal;
		this.descricao = descricao;
		this.relacaoProdutos = relacaoProdutos;
		this.cliente = cliente;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRelacaoProdutos() {
		return relacaoProdutos;
	}

	public void setRelacaoProdutos(String relacaoProdutos) {
		this.relacaoProdutos = relacaoProdutos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}	
}
