package com.impacta.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impacta.crm.model.Cliente;
import com.impacta.crm.repository.helper.cliente.ClientesQueries;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries {

	public Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

	public List<Cliente> findByNomeStartingWithIgnoreCase(String nome);
	
    // Pesquisando por duas propriedades: nome e ativo.
	List<Cliente> findByNomeStartingWithIgnoreCaseAndAtivoEquals(String nome, boolean ativo);

}
