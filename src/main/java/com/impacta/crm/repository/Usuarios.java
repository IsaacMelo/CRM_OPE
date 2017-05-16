package com.impacta.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impacta.crm.model.Cliente;
import com.impacta.crm.model.Usuario;
import com.impacta.crm.repository.helper.usuario.UsuariosQueries;

public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

	public Optional<Usuario> findByEmail(String email);

	public List<Usuario> findByCodigoIn(Long[] codigos);
	
	public List<Usuario> findByNomeStartingWithIgnoreCase(String nome);
}
