package br.com.db1.pedidos.pedidosapi.domain.entity;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Verificador;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="produto")
@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="codigo",length = 50,nullable = false, unique = true)
	private String codigo;
	
	@Column(name="nome",length = 100,nullable = false)
	private String nome;
	
	@Column(name="valor",precision = 16, scale = 2,nullable = false)
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status",length = 30, nullable = false)
	private ProdutoStatus status;
	
	protected Produto() {}
	
	public Produto(String codigo, String nome, Double valor) {
		Verificador.naoNulo(codigo, "código do produto");
		Verificador.naoNulo(nome, "nome do produto");
		Verificador.naoNulo(valor, "valor do produto");

		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.status = ProdutoStatus.ATIVO;
	}
	
	public void inativar() {
		if (!ProdutoStatus.ATIVO.equals(this.status)) {
			throw new RuntimeException("Produto está " + this.status);
		}
		this.status = ProdutoStatus.INATIVO;
	}
	
	public boolean isAtivo() {
		return  ProdutoStatus.ATIVO.equals(this.status);
	}

	public Long getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

	public ProdutoStatus getStatus() {
		return status;
	}
		
	@Override
	public String toString() {
		return "Codigo: "+this.codigo+" Nome: "+this.nome+" Valor: "+ String.format("%.2f",this.getValor());
	}
	
}
