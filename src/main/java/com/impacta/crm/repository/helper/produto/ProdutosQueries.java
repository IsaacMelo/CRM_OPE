package com.impacta.crm.repository.helper.produto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.impacta.crm.dto.ProdutoDTO;
import com.impacta.crm.dto.ValorItensEstoque;
import com.impacta.crm.model.Produto;
import com.impacta.crm.repository.filter.ProdutoFilter;

public interface ProdutosQueries {

	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable);
	
	public List<ProdutoDTO> porSkuOuNome(String skuOuNome);
	
	public ValorItensEstoque valorItensEstoque();
	
}
