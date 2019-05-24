package com.rafa.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rafa.brewer.service.CadastroCervejaService;
import com.rafa.brewer.storage.FotoStorage;
import com.rafa.brewer.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroCervejaService.class)
public class ServiceConfig
{
	@Bean
	public FotoStorage fotoStorage()
	{
		return new FotoStorageLocal();
	}
}
