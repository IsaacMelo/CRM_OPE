package com.impacta.crm.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.impacta.crm.model.Categoria;

public class EstiloConverter implements Converter<String, Categoria> {

	@Override
	public Categoria convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Categoria categoria = new Categoria();
			categoria.setCodigo(Long.valueOf(codigo));
			return categoria;
		}
		
		return null;
	}

}
