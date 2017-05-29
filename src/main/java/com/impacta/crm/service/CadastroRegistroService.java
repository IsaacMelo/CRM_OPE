package com.impacta.crm.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.crm.model.Produto;
import com.impacta.crm.model.RegistroEstoque;
import com.impacta.crm.repository.Produtos;
import com.impacta.crm.repository.Registros;
import com.impacta.crm.service.exception.ItensEstqueObrigatorioException;

@Service
public class CadastroRegistroService {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private Registros registros;
	
	@Autowired
	private Produtos produtos;
	
	@Transactional
	public RegistroEstoque salvarComoEntrada(RegistroEstoque registro) {
		if(registro.getItens().isEmpty()){
			throw new ItensEstqueObrigatorioException("Adicione ao menos um produto para dar entrada");
		}
		registro.setDataCriacao(LocalDateTime.now());
		darEntrada(registro);
		return registros.saveAndFlush(registro);
	}
	
	@Transactional
	public RegistroEstoque salvarComoSaida(RegistroEstoque registro) {
		if(registro.getItens().isEmpty()){
			throw new ItensEstqueObrigatorioException("Adicione ao menos um produto para dar saída");
		}
		registro.setDataCriacao(LocalDateTime.now());
		darSaida(registro);
		return registros.saveAndFlush(registro);
	}
	
	@Transactional
	private void darEntrada(RegistroEstoque registro){
		registro.getItens().forEach(i->{
			Produto produto = produtos.findOne(i.getProduto().getCodigo());
			produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + i.getQuantidade());
			produtos.saveAndFlush(produto);
		});
	}
	
	@Transactional
	private void darSaida(RegistroEstoque registro){
		registro.getItens().forEach(i->{
			Produto produto = produtos.findOne(i.getProduto().getCodigo());
			produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - i.getQuantidade());
			produtos.saveAndFlush(produto);
		});
	}
}
