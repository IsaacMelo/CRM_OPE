package com.impacta.crm.repository.helper.resgistro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.impacta.crm.model.RegistroEstoque;
import com.impacta.crm.repository.filter.RegistroFilter;

public interface RegistrosQueries {

	public Page<RegistroEstoque> filtrar(RegistroFilter filtro, Pageable pageable);
	
}
