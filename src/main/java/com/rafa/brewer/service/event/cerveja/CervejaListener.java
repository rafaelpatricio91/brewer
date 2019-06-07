package com.rafa.brewer.service.event.cerveja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rafa.brewer.storage.FotoStorage;

@Component
public class CervejaListener
{
	@Autowired
	private FotoStorage fotoStorage;
	
	
	@EventListener(condition = "#evento.temFoto()")
	public void cervejaSalva(CervejaSalvaEvent evento)
	{
		fotoStorage.salvar(evento.getCerveja().getFoto());
		System.out.println(">>>>>>>>>>> Tem foto sim: " + evento.getCerveja().getFoto());
	}
}
