package com.rafa.brewer.repository.helper.estilo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.rafa.brewer.model.Estilo;
import com.rafa.brewer.repository.filter.EstiloFilter;

public class EstilosImpl implements EstiloQueries
{
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override @Transactional(readOnly=true)
	public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable)
	{
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPagina; 
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPagina);
		
		Sort sort = pageable.getSort();
		if (sort != null)
		{
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property) );
		}
		
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<Estilo>(criteria.list(), pageable, total(filtro));
	}

	private Long total(EstiloFilter filtro)
	{
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(EstiloFilter filtro, Criteria criteria)
	{
		if (filtro != null)
		{
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
		}
	}

}
