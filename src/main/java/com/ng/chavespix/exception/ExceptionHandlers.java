package com.ng.chavespix.exception;

import java.util.Arrays;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ng.chavespix.dto.response.ExceptionResponse;
import com.ng.chavespix.enums.TipoChave;
import com.ng.chavespix.enums.TipoConta;

@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handle(ValidationException ex) {
		return ResponseEntity.unprocessableEntity().body(new ExceptionResponse(ex.getLocalizedMessage()));
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handle(MethodArgumentNotValidException ex) {
		return ResponseEntity.unprocessableEntity()
				.body(new ExceptionResponse(ex.getAllErrors().get(0).getDefaultMessage()));
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handle(BindException ex) {
		return ResponseEntity.unprocessableEntity()
				.body(new ExceptionResponse(ex.getAllErrors().get(0).getDefaultMessage()));
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handle(InvalidFormatException ex) {

		String message = ex.getLocalizedMessage();

		if (ex.getTargetType().isAssignableFrom(TipoChave.class)) {
			message = "tipoChave inválido. Valores permitidos:" + Arrays.toString(TipoChave.values());
		} else if (ex.getTargetType().isAssignableFrom(TipoConta.class)) {
			message = "tipoConta inválido. Valores permitidos:" + Arrays.toString(TipoConta.values());
		}

		return ResponseEntity.unprocessableEntity().body(new ExceptionResponse(message));
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handle(EntidadeNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(ex.getMessage()));
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handle(ValidacaoException ex) {
		return ResponseEntity.unprocessableEntity().body(new ExceptionResponse(ex.getMessage()));
	}

}
