package com.impacta.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impacta.crm.model.Fornecedor;
import com.impacta.crm.repository.helper.fornecedor.FornecedoresQueries;

@Repository
public interface Fornecedores extends JpaRepository<Fornecedor, Long>, FornecedoresQueries {

	public Optional<Fornecedor> findByCpfOuCnpj(String cpfOuCnpj);

	public List<Fornecedor> findByNomeStartingWithIgnoreCase(String nome);
	
    // Pesquisando por duas propriedades: nome e ativo.
	List<Fornecedor> findByNomeStartingWithIgnoreCaseAndAtivoEquals(String nome, boolean ativo);

}
