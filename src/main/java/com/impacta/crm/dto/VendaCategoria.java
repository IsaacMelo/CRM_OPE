package com.impacta.crm.dto;

public class VendaCategoria {

	private Integer quantidade;
	private String 	nome;
		
	public VendaCategoria() {
		
	}

	public VendaCategoria(Integer quantidade, String nome) {
		this.quantidade = quantidade;
		this.nome 	= nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	
}
