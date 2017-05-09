package com.impacta.crm.service.event.venda;

import com.impacta.crm.model.Venda;

public class VendaEvent {

	private Venda venda;

	public VendaEvent(Venda venda) {
		this.venda = venda;
	}

	public Venda getVenda() {
		return venda;
	}

}
