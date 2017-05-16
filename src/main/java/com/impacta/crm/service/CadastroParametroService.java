package com.impacta.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.crm.model.Parametro;
import com.impacta.crm.repository.Parametros;

@Service
public class CadastroParametroService {

	@Autowired
	private Parametros parametros;
	
	@Transactional
	public Parametro salvar(Parametro parametro) {
		return parametros.saveAndFlush(parametro);
	}
	
}
