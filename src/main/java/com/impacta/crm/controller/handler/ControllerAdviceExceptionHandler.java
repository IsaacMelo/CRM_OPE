package com.impacta.crm.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.impacta.crm.service.exception.NomeCategoriaJaCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(NomeCategoriaJaCadastradoException.class)
	public ResponseEntity<String> handleNomeEstiloJaCadastradoException(NomeCategoriaJaCadastradoException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
