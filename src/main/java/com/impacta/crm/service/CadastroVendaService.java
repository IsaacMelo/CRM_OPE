package com.impacta.crm.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.crm.model.StatusVenda;
import com.impacta.crm.model.Venda;
import com.impacta.crm.repository.Vendas;
import com.impacta.crm.service.event.venda.VendaEvent;

@Service
public class CadastroVendaService {

	@Autowired
	private Vendas vendas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public Venda salvar(Venda venda) {
		if (venda.isSalvarProibido()) {
			throw new RuntimeException("Usuário tentando salvar uma venda proibida");
		}
		
		if (venda.isNova()) {
			venda.setDataCriacao(LocalDateTime.now());
		} else {
			Venda vendaExistente = vendas.findOne(venda.getCodigo());
			venda.setDataCriacao(vendaExistente.getDataCriacao());
		}
		
		if (venda.getDataEntrega() != null) {
			venda.setDataHoraEntrega(LocalDateTime.of(venda.getDataEntrega()
					, venda.getHorarioEntrega() != null ? venda.getHorarioEntrega() : LocalTime.NOON));
		}
		
		return vendas.saveAndFlush(venda);
	}

	@Transactional
	public void emitir(Venda venda) {
		long codigo;
		if(venda.isNova()) codigo = 0; else codigo = venda.getCodigo();
		
		Venda vendaTmp = vendas.findOne(codigo);
		
		if(vendaTmp != null){
			if(vendaTmp.getStatus() == StatusVenda.EMITIDA)
			venda.setStatus(StatusVenda.EMITIDA);
		}
		
		publisher.publishEvent(new VendaEvent(venda));
		venda.setStatus(StatusVenda.EMITIDA);
		salvar(venda);
	}

	@PreAuthorize("#venda.usuario == principal.usuario or hasRole('CANCELAR_VENDA')")
	@Transactional
	public void cancelar(Venda venda) {
		Venda vendaExistente = vendas.findOne(venda.getCodigo());
		
		vendaExistente.setStatus(StatusVenda.CANCELADA);
		vendas.save(vendaExistente);
		
		publisher.publishEvent(new VendaEvent(vendaExistente));
	}

}
