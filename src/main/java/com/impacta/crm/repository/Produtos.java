package com.impacta.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impacta.crm.model.Produto;
import com.impacta.crm.repository.helper.produto.ProdutosQueries;

@Repository
public interface Produtos extends JpaRepository<Produto, Long>, ProdutosQueries {

}
