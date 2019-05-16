package com.rafa.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafa.brewer.model.Estilo;

@Repository
public interface Estilos extends JpaRepository<Estilo, Long>
{
	public Optional<Estilo> findByNomeIgnoreCase(String nome);
}
