package com.impacta.crm.model;

public enum Registro {

	ENTRADA("Entrada"),
	SAIDA("Saída");
	
	private String descricao;
	
	Registro(String registro){
		this.descricao = registro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String registro) {
		this.descricao = registro;
	}
	
	
}
