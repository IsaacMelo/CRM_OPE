package com.impacta.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impacta.crm.model.RegistroEstoque;

@Repository
public interface Registros extends JpaRepository<RegistroEstoque,Long > {


}
