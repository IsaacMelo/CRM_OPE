package com.impacta.crm.repository.helper.fornecedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.impacta.crm.model.Fornecedor;
import com.impacta.crm.repository.filter.FornecedorFilter;

public interface FornecedoresQueries {

	public Page<Fornecedor> filtrar(FornecedorFilter filtro, Pageable pageable);
}
