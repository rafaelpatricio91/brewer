package com.rafa.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafa.brewer.model.Cerveja;
import com.rafa.brewer.repository.helper.cerveja.CervejasQueries;

@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long>, CervejasQueries
{
	public Optional<Cerveja> findBySkuIgnoreCase(String sku);
}
