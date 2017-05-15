package com.impacta.crm.repository.helper.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.impacta.crm.model.Categoria;
import com.impacta.crm.repository.filter.CategoriaFilter;

public interface CategoriasQueries {
	
	public Page<Categoria> filtrar(CategoriaFilter filtro, Pageable pageable);
	
}
