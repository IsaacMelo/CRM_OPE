package com.impacta.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impacta.crm.model.FormaPagamento;

@Repository
public interface FormaPagamentos extends JpaRepository<FormaPagamento, Long> {

	public Optional<FormaPagamento> findByNomeIgnoreCase(String nome);
	
}
