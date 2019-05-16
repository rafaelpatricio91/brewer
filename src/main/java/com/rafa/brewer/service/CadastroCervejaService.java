package com.rafa.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafa.brewer.model.Cerveja;
import com.rafa.brewer.repository.Cervejas;

@Service
public class CadastroCervejaService
{
	@Autowired
	private Cervejas cervejas;
	
	@Transactional
	public void salvar(Cerveja cerveja)
	{
		cervejas.save(cerveja);
	}
	
}
