package com.impacta.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impacta.crm.model.Banco;

@Repository
public interface Bancos extends JpaRepository<Banco, Long> {

}
