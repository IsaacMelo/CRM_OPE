package com.impacta.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.crm.model.Produto;
import com.impacta.crm.repository.Produtos;

@Service
public class CadastroProdutoService {

	@Autowired
	private Produtos produtos;
	
	@Transactional
	public void salvar(Produto produto) {
		produtos.save(produto);
	}
	
	@Transactional
	public void excluir(Produto produto) {
		produto.setAtivo(false);
		produtos.save(produto);
	}
	
}
