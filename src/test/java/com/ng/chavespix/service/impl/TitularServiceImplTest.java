package com.ng.chavespix.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ng.chavespix.entity.Conta;
import com.ng.chavespix.entity.Titular;
import com.ng.chavespix.enums.TipoConta;
import com.ng.chavespix.enums.TipoPessoa;
import com.ng.chavespix.repository.TitularRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TitularServiceImplTest {
	
	@MockBean
	private TitularRepository repository;

	private Conta conta;
	
	@BeforeEach
	void setUp() throws Exception {
		
		this.conta = new Conta();
		this.conta.setNumeroAgencia(123);
		this.conta.setNumeroConta(456);
		this.conta.setTipoConta(TipoConta.corrente);
		
	}

	@Test
	void testObterTipoPessoa_NaoHaTitularParaContaRetornarNull() {
		
		when(this.repository.obterTitularConta(this.conta))
		.thenReturn(null);
		
		TitularServiceImpl service = new TitularServiceImpl(this.repository);
		
		TipoPessoa expected = null;
		TipoPessoa actual = service.obterTipoPessoa(this.conta);
		
		assertEquals(expected, actual);

	
	}
	
	@Test
	void testObterTipoPessoa_ListaVaziaDeveRetornarNull() {
		
		List<Titular> titulares = new LinkedList<>();

		when(this.repository.obterTitularConta(this.conta))
		.thenReturn(titulares);
		
		TitularServiceImpl service = new TitularServiceImpl(this.repository);
		
		TipoPessoa expected = null;
		TipoPessoa actual = service.obterTipoPessoa(this.conta);
		
		assertEquals(expected, actual);

	
	}
	
	@Test
	void testObterTipoPessoa_HaTitularDeveRetornarPF() {
		
		Titular titular = new Titular();
		titular.setTipoPessoa(TipoPessoa.pf);
		
		List<Titular> titulares = new LinkedList<>();
		titulares.add(titular);
			
		when(this.repository.obterTitularConta(this.conta))
		.thenReturn(titulares);
		
		TitularServiceImpl service = new TitularServiceImpl(this.repository);
		
		TipoPessoa expected = TipoPessoa.pf;
		TipoPessoa actual = service.obterTipoPessoa(this.conta);
		
		assertEquals(expected, actual);

	
	}
	
	@Test
	void testObterTipoPessoa_HaTitularDeveRetornarPJ() {
		
		Titular titular = new Titular();
		titular.setTipoPessoa(TipoPessoa.pj);
		
		List<Titular> titulares = new LinkedList<>();
		titulares.add(titular);
			
		when(this.repository.obterTitularConta(this.conta))
		.thenReturn(titulares);
		
		TitularServiceImpl service = new TitularServiceImpl(this.repository);
		
		TipoPessoa expected = TipoPessoa.pj;
		TipoPessoa actual = service.obterTipoPessoa(this.conta);
		
		assertEquals(expected, actual);

	
	}

}
