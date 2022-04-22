package com.ng.chavespix.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ng.chavespix.dto.response.ExceptionResponse;

class ExceptionHandlersTest {
	

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testHandleEntidadeNotFoundException_DeveRetornar404() {
		
		String msg = "RANDOM MSG";
		
		EntidadeNotFoundException ex = new EntidadeNotFoundException(msg);
		ExceptionHandlers exh = new ExceptionHandlers();
		ResponseEntity<ExceptionResponse> response = exh.handle(ex);
		
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
		assertTrue(msg.equals(response.getBody().getMessage()));
		
	}

	@Test
	void testHandleValidacaoException_DeveRetornar422() {

		String msg = "RANDOM MSG";
		
		ValidacaoException ex = new ValidacaoException(msg);
		ExceptionHandlers exh = new ExceptionHandlers();
		ResponseEntity<ExceptionResponse> response = exh.handle(ex);
		
		assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
		assertTrue(msg.equals(response.getBody().getMessage()));
	
	}

}
