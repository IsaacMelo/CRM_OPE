package com.impacta.crm.model;

public enum StatusVenda {

	 
	EMITIDA("Emitida"),
	FATURADA("Faturada"),
	TRANSPORTE("Em Transporte"),
	FINALIZADA("Finalizada"),
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
