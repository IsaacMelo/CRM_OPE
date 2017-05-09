package com.impacta.crm.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.crm.model.Produto;
import com.impacta.crm.repository.Produtos;
import com.impacta.crm.service.exception.ImpossivelExcluirEntidadeException;
import com.impacta.crm.storage.FotoStorage;

@Service
public class CadastroProdutoService {

	@Autowired
	private Produtos produtos;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Transactional
	public void salvar(Produto produto) {
		produtos.save(produto);
	}
	
	@Transactional
	public void excluir(Produto produto) {
		try {
			String foto = produto.getFoto();
			produtos.delete(produto);
			produtos.flush();
			fotoStorage.excluir(foto);
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o produto. Já foi usada em alguma venda.");
		}
	}
	
}
