package com.impacta.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impacta.crm.model.Parametro;

@Repository
public interface Parametros extends JpaRepository<Parametro, Long> {
	
}
