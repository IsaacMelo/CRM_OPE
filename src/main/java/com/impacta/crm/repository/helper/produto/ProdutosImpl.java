package com.impacta.crm.repository.helper.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.impacta.crm.dto.ProdutoDTO;
import com.impacta.crm.dto.ValorItensEstoque;
import com.impacta.crm.model.Produto;
import com.impacta.crm.repository.filter.ProdutoFilter;
import com.impacta.crm.repository.paginacao.PaginacaoUtil;
import com.impacta.crm.storage.FotoStorage;

public class ProdutosImpl implements ProdutosQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public List<ProdutoDTO> porSkuOuNome(String skuOuNome) {
		String jpql = "select new com.impacta.crm.dto.ProdutoDTO(codigo, sku, nome, valor, quantidadeEstoque, foto) "
				+ "from Produto where lower(sku) like lower(:skuOuNome) or lower(nome) like lower(:skuOuNome) and ativo = 1";
		List<ProdutoDTO> cervejasFiltradas = manager.createQuery(jpql, ProdutoDTO.class)
					.setParameter("skuOuNome", skuOuNome + "%")
					.getResultList();
		cervejasFiltradas.forEach(c -> c.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + c.getFoto())));
		return cervejasFiltradas;
	}
	
	@Override
	public ValorItensEstoque valorItensEstoque() {
		String query = "select new com.impacta.crm.dto.ValorItensEstoque(sum(valorCompra * quantidadeEstoque), sum(quantidadeEstoque)) from Produto";
		return manager.createQuery(query, ValorItensEstoque.class).getSingleResult();
	}
	
	private Long total(ProdutoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(ProdutoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getSku())) {
				criteria.add(Restrictions.eq("sku", filtro.getSku()));
			}
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (isCategoriaPresent(filtro)) {
				criteria.add(Restrictions.eq("categoria", filtro.getCategoria()));
			}

			if (filtro.getValorDe() != null) {
				criteria.add(Restrictions.ge("valor", filtro.getValorDe()));
			}

			if (filtro.getValorAte() != null) {
				criteria.add(Restrictions.le("valor", filtro.getValorAte()));
			}
		}
	}
	
	private boolean isCategoriaPresent(ProdutoFilter filtro) {
		return filtro.getCategoria() != null && filtro.getCategoria().getCodigo() != null;
	}

}
