package com.impacta.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impacta.crm.model.ContaBancaria;

@Repository
public interface ContasBancarias extends JpaRepository<ContaBancaria, Long>{

}
