package com.rafa.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafa.brewer.model.Estilo;
import com.rafa.brewer.repository.Estilos;
import com.rafa.brewer.service.exception.NomeEstiloJaCadastradoException;

@Service
public class CadastroEstiloService
{	
	@Autowired
	private Estilos estilos;
	
	@Transactional
	public Estilo salvar(Estilo estilo)
	{
		Optional<Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome() );
		if (estiloOptional.isPresent())
		{
			throw new NomeEstiloJaCadastradoException("Nome do estilo já cadastrado");
		}
		
		return estilos.saveAndFlush(estilo);
	}
}
