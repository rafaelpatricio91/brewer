package com.rafa.brewer.repository.helper.estilo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rafa.brewer.model.Estilo;
import com.rafa.brewer.repository.filter.EstiloFilter;

public interface EstiloQueries
{
	public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable);
}
