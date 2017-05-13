package com.impacta.crm.model;

public enum StatusVenda {

	 
	EMITIDA("Emitida"), 
	ORCAMENTO("Orçamento"),
	CANCELADA("Cancelada");

	private String descricao;

	StatusVenda(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
