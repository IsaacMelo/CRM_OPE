package com.impacta.crm.repository.helper.resgistro;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.impacta.crm.model.RegistroEstoque;
import com.impacta.crm.repository.filter.RegistroFilter;
import com.impacta.crm.repository.paginacao.PaginacaoUtil;

public class RegistrosImpl implements RegistrosQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<RegistroEstoque> filtrar(RegistroFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(RegistroEstoque.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		adicionarOrdem(pageable,criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private void adicionarOrdem(Pageable pageable, Criteria criteria) {
		if(pageable.getSort() == null){
			criteria.addOrder(Order.desc("codigo"));
		}
	}

	private Long total(RegistroFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(RegistroEstoque.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(RegistroFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}
			
			if (filtro.getDesde() != null) {
				LocalDateTime desde = LocalDateTime.of(filtro.getDesde(), LocalTime.of(0, 0));
				criteria.add(Restrictions.ge("dataCriacao", desde));
			}
			
			if (filtro.getAte() != null) {
				LocalDateTime ate = LocalDateTime.of(filtro.getAte(), LocalTime.of(23, 59));
				criteria.add(Restrictions.le("dataCriacao", ate));
			}
			
			if (filtro.getRegistro() != null) {
				criteria.add(Restrictions.eq("registro", filtro.getRegistro()));
			}
		}
	}
	
}
