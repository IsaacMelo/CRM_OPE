package com.impacta.crm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.crm.model.Cliente;
import com.impacta.crm.repository.Clientes;
import com.impacta.crm.service.exception.CpfCnpjClienteJaCadastradoException;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void salvar(Cliente cliente) {
		
		if(cliente.isNovo()){
			Optional<Cliente> clienteExistente = clientes.findByCpfOuCnpj(cliente.getCpfOuCnpjSemFormatacao());
			if (clienteExistente.isPresent()) {
				throw new CpfCnpjClienteJaCadastradoException("CPF/CNPJ já cadastrado");
			}
		}
		
		clientes.save(cliente);
	}
	
	@Transactional
	public void excluir(Cliente cliente){
		cliente = clientes.findOne(cliente.getCodigo());
		cliente.setAtivo(false);
		clientes.save(cliente);
	}
	
}
