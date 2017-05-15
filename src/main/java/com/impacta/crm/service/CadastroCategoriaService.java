package com.impacta.crm.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.crm.model.Categoria;
import com.impacta.crm.repository.Categorias;
import com.impacta.crm.service.exception.ImpossivelExcluirEntidadeException;
import com.impacta.crm.service.exception.NomeCategoriaJaCadastradoException;

@Service
public class CadastroCategoriaService {

	@Autowired
	private Categorias categorias;
	
	@Transactional
	public Categoria salvar(Categoria categoria) {
		Optional<Categoria> categoriaOptional = categorias.findByNomeIgnoreCase(categoria.getNome());
		if (categoriaOptional.isPresent()) {
			throw new NomeCategoriaJaCadastradoException("Nome do estilo já cadastrado");
		}
		
		return categorias.saveAndFlush(categoria);
	}
	
	@Transactional
	public void excluir(Categoria categoria) {
		try {
			categorias.delete(categoria);
			categorias.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar a categoria. Já foi usada em algum produto.");
		}
	}
	
}
