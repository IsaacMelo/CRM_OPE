package com.impacta.crm.dto;

public enum TipoRelatorioProduto {
	
	ATIVO("Ativo"), 
	INATIVO("Inativo"),
	NEGATIVO("Negativo");
	
	private String descricao;

	TipoRelatorioProduto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
