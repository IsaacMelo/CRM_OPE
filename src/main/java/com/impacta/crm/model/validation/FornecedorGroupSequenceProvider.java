package com.impacta.crm.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.impacta.crm.model.Fornecedor;

public class FornecedorGroupSequenceProvider implements DefaultGroupSequenceProvider<Fornecedor> {

	@Override
	public List<Class<?>> getValidationGroups(Fornecedor fornecedor) {
		List<Class<?>> grupos = new ArrayList<>();
		
		grupos.add(Fornecedor.class);
				
		if (isPessoaSelecionada(fornecedor)) {
			grupos.add(fornecedor.getTipoPessoa().getGrupo());
		}
		
		return grupos;
	}

	private boolean isPessoaSelecionada(Fornecedor cliente) {
		return cliente != null && cliente.getTipoPessoa() != null;
	}

}
