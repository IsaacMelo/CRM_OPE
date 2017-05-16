package com.impacta.crm.repository.filter;

import java.math.BigDecimal;

import com.impacta.crm.model.Categoria;

public class ProdutoFilter {

	private String sku;
	private String nome;
	private Categoria categoria;
	private BigDecimal valorDe;
	private BigDecimal valorAte;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValorDe() {
		return valorDe;
	}

	public void setValorDe(BigDecimal valorDe) {
		this.valorDe = valorDe;
	}

	public BigDecimal getValorAte() {
		return valorAte;
	}

	public void setValorAte(BigDecimal valorAte) {
		this.valorAte = valorAte;
	}

}
