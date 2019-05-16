package com.rafa.brewer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.rafa.brewer.dto.FotoDTO;
import com.rafa.brewer.storage.FotoStorageRunnable;

@RestController
@RequestMapping("/fotos")
public class FotosController
{
	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files)
	{
		DeferredResult<FotoDTO> resultado = new DeferredResult<FotoDTO>();
		
		Thread thread = new Thread(new FotoStorageRunnable(files, resultado) );
		thread.start();
		
		return resultado;
	}
}
