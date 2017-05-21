package com.impacta.crm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.crm.model.Fornecedor;
import com.impacta.crm.repository.Fornecedores;
import com.impacta.crm.service.exception.CpfCnpjClienteJaCadastradoException;

@Service
public class CadastroFornecedorService {

	@Autowired
	private Fornecedores fornecedores;
	
	@Transactional
	public void salvar(Fornecedor fornecedor) {
		
		if(fornecedor.isNovo()){
			Optional<Fornecedor> fornecedorExistente = fornecedores.findByCpfOuCnpj(fornecedor.getCpfOuCnpjSemFormatacao());
			if (fornecedorExistente.isPresent()) {
				throw new CpfCnpjClienteJaCadastradoException("CPF/CNPJ já cadastrado");
			}
		}
		
		fornecedores.save(fornecedor);
	}
	
	@Transactional
	public void excluir(Fornecedor fornecedor){
		fornecedor.setAtivo(false);
		fornecedores.save(fornecedor);
	}
}
