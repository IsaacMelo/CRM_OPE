package com.impacta.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impacta.crm.model.Venda;
import com.impacta.crm.repository.helper.venda.VendasQueries;

public interface Vendas extends JpaRepository<Venda, Long>, VendasQueries {

}
