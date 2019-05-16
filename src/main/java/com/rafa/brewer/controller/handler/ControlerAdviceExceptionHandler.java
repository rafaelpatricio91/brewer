package com.rafa.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rafa.brewer.service.exception.NomeEstiloJaCadastradoException;

@ControllerAdvice
public class ControlerAdviceExceptionHandler
{
	@ExceptionHandler(NomeEstiloJaCadastradoException.class)
	public ResponseEntity<String> handleNomeEstiloJaCadastradoException(NomeEstiloJaCadastradoException e)
	{
		return ResponseEntity.badRequest().body(e.getMessage() );
	}
}
