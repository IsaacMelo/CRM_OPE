package com.impacta.crm.dto;

import java.math.BigDecimal;

public class ValorItensEstoque {

	private BigDecimal valorCompra;
	private Long totalItens;
	
	public ValorItensEstoque() {
		
	}

	public ValorItensEstoque(BigDecimal valorCompra, Long totalItens) {
		this.valorCompra = valorCompra;
		this.totalItens = totalItens;
	}

	public BigDecimal getValorCompra() {
		return valorCompra != null ? valorCompra : BigDecimal.ZERO;
	}

	public void setValorCompra(BigDecimal valor) {
		this.valorCompra = valor;
	}

	public Long getTotalItens() {
		return totalItens != null ? totalItens : 0L;
	}

	public void setTotalItens(Long totalItens) {
		this.totalItens = totalItens;
	}
	
}
