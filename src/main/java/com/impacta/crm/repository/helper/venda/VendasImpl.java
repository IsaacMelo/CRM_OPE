package com.impacta.crm.repository.helper.venda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.impacta.crm.dto.VendaCategoria;
import com.impacta.crm.dto.VendaMes;
import com.impacta.crm.model.StatusVenda;
import com.impacta.crm.model.TipoPessoa;
import com.impacta.crm.model.Venda;
import com.impacta.crm.repository.filter.VendaFilter;
import com.impacta.crm.repository.paginacao.PaginacaoUtil;

public class VendasImpl implements VendasQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Venda> filtrar(VendaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		adicionarOrdem(pageable,criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Venda> filtrarFaturada(VendaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltroFaturada(filtro, criteria);
		adicionarOrdem(pageable,criteria);
		
		return new PageImpl<>(criteria.list(), pageable, totalFaturada(filtro));
	}
	
	@Transactional(readOnly = true)
	@Override
	public Venda buscarComItens(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);
		criteria.createAlias("itens", "i", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Venda) criteria.uniqueResult();
	}
	
	@Override
	public BigDecimal valorTotalNoAno() {
		Optional<BigDecimal> optional = Optional.ofNullable(
				manager.createQuery("select sum(valorTotal) from Venda where year(dataCriacao) = :ano and status = :status", BigDecimal.class)
					.setParameter("ano", Year.now().getValue())
					.setParameter("status", StatusVenda.FINALIZADA)
					.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}
	
	@Override
	public BigDecimal valorTotalNoAnoUsuario(long codigoUsuario) {
		Optional<BigDecimal> optional = Optional.ofNullable(
				manager.createQuery("select sum(valorTotal) from Venda where year(dataCriacao) = :ano and status = :status and codigo_usuario = :codigoUsuario", BigDecimal.class)
					.setParameter("ano", Year.now().getValue())
					.setParameter("status", StatusVenda.FINALIZADA)
					.setParameter("codigoUsuario", codigoUsuario)
					.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}
	
	@Override
	public BigDecimal valorTotalNoMes() {
		Optional<BigDecimal> optional = Optional.ofNullable(
				manager.createQuery("select sum(valorTotal) from Venda where month(dataCriacao) = :mes and status = :status", BigDecimal.class)
					.setParameter("mes", MonthDay.now().getMonthValue())
					.setParameter("status", StatusVenda.FINALIZADA)
					.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}
	
	@Override
	public BigDecimal valorTotalNoMesUsuario(long codigoUsuario) {
		Optional<BigDecimal> optional = Optional.ofNullable(
				manager.createQuery("select sum(valorTotal) from Venda where month(dataCriacao) = :mes and status = :status and codigo_usuario = :codigoUsuario", BigDecimal.class)
					.setParameter("mes", MonthDay.now().getMonthValue())
					.setParameter("status", StatusVenda.FINALIZADA)
					.setParameter("codigoUsuario", codigoUsuario)
					.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}
	
	@Override
	public BigDecimal valorTicketMedioNoAno() {
		Optional<BigDecimal> optional = Optional.ofNullable(
				manager.createQuery("select sum(valorTotal)/count(*) from Venda where year(dataCriacao) = :ano and status = :status", BigDecimal.class)
					.setParameter("ano", Year.now().getValue())
					.setParameter("status", StatusVenda.FINALIZADA)
					.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}
	
	@Override
	public BigDecimal valorTicketMedioNoAnoUsuario(long codigoUsuario) {
		Optional<BigDecimal> optional = Optional.ofNullable(
				manager.createQuery("select sum(valorTotal)/count(*) from Venda where year(dataCriacao) = :ano and status = :status and codigo_usuario = :codigoUsuario", BigDecimal.class)
					.setParameter("ano", Year.now().getValue())
					.setParameter("status", StatusVenda.FINALIZADA)
					.setParameter("codigoUsuario", codigoUsuario)
					.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VendaMes> totalPorMes() {
		List<VendaMes> vendasMes = manager.createNamedQuery("Vendas.totalPorMes").getResultList();
		
		LocalDate hoje = LocalDate.now();
		for (int i = 1; i <= 6; i++) {
			String mesIdeal = String.format("%d/%02d", hoje.getYear(), hoje.getMonthValue());
			
			boolean possuiMes = vendasMes.stream().filter(v -> v.getMes().equals(mesIdeal)).findAny().isPresent();
			if (!possuiMes) {
				vendasMes.add(i - 1, new VendaMes(mesIdeal, 0));
			}
			
			hoje = hoje.minusMonths(1);
		}
		
		return vendasMes;
	}
	
	@Override
	public List<VendaCategoria> totalPorCategoria() {
		List<VendaCategoria> vendasCategoria = manager.createNamedQuery("Vendas.porCategoria", VendaCategoria.class).getResultList();
		
		return vendasCategoria;
	}
	
	private Long total(VendaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Long totalFaturada(VendaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);
		adicionarFiltroFaturada(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(VendaFilter filtro, Criteria criteria) {
		criteria.createAlias("cliente", "c");
		criteria.createAlias("usuario", "u");
		
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}
			
			if (filtro.getStatus() != null) {
				criteria.add(Restrictions.eq("status", filtro.getStatus()));
			}
			
			if (!StringUtils.isEmpty(filtro.getUsuario())) {
				criteria.add(Restrictions.eq("u.nome", filtro.getUsuario()));
			}
			
			if (filtro.getDesde() != null) {
				LocalDateTime desde = LocalDateTime.of(filtro.getDesde(), LocalTime.of(0, 0));
				criteria.add(Restrictions.ge("dataCriacao", desde));
			}
			
			if (filtro.getAte() != null) {
				LocalDateTime ate = LocalDateTime.of(filtro.getAte(), LocalTime.of(23, 59));
				criteria.add(Restrictions.le("dataCriacao", ate));
			}
			
			if (filtro.getValorMinimo() != null) {
				criteria.add(Restrictions.ge("valorTotal", filtro.getValorMinimo()));
			}
			
			if (filtro.getValorMaximo() != null) {
				criteria.add(Restrictions.le("valorTotal", filtro.getValorMaximo()));
			}
			
			if (!StringUtils.isEmpty(filtro.getNomeCliente())) {
				criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getCpfOuCnpjCliente())) {
				criteria.add(Restrictions.eq("c.cpfOuCnpj", TipoPessoa.removerFormatacao(filtro.getCpfOuCnpjCliente())));
			}
			
			if(!filtro.getRoles().stream().anyMatch(i -> i.getAuthority().equals("ROLE_TODAS_VENDAS"))){
				criteria.add(Restrictions.eq("u.codigo", filtro.getCodigoUsuario()));
			}
			
		}
	}
	
	private void adicionarFiltroFaturada(VendaFilter filtro, Criteria criteria) {
		criteria.createAlias("cliente", "c");
		criteria.createAlias("usuario", "u");
		
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}
			
			if (filtro.getStatus() != null) {
				criteria.add(Restrictions.eq("status", filtro.getStatus()));
			}
			
			if (!StringUtils.isEmpty(filtro.getUsuario())) {
				criteria.add(Restrictions.eq("u.nome", filtro.getUsuario()));
			}
			
			if (filtro.getDesde() != null) {
				LocalDateTime desde = LocalDateTime.of(filtro.getDesde(), LocalTime.of(0, 0));
				criteria.add(Restrictions.ge("dataCriacao", desde));
			}
			
			if (filtro.getAte() != null) {
				LocalDateTime ate = LocalDateTime.of(filtro.getAte(), LocalTime.of(23, 59));
				criteria.add(Restrictions.le("dataCriacao", ate));
			}
			
			if (filtro.getValorMinimo() != null) {
				criteria.add(Restrictions.ge("valorTotal", filtro.getValorMinimo()));
			}
			
			if (filtro.getValorMaximo() != null) {
				criteria.add(Restrictions.le("valorTotal", filtro.getValorMaximo()));
			}
			
			if (!StringUtils.isEmpty(filtro.getNomeCliente())) {
				criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getCpfOuCnpjCliente())) {
				criteria.add(Restrictions.eq("c.cpfOuCnpj", TipoPessoa.removerFormatacao(filtro.getCpfOuCnpjCliente())));
			}
			
			criteria.add(Restrictions.in("status", StatusVenda.FATURADA,StatusVenda.TRANSPORTE));
			
		}
		
	}
	
	private void adicionarOrdem(Pageable pageable, Criteria criteria) {
		if(pageable.getSort() == null){
			criteria.addOrder(Order.desc("codigo"));
		}
	}

}
