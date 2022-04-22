package com.ng.chavespix.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ng.chavespix.entity.Conta;
import com.ng.chavespix.enums.TipoConta;
import com.ng.chavespix.repository.ContaRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ContaServiceImplTest {

	@MockBean
	private ContaRepository repository;

	private Conta conta;

	@BeforeEach
	void setUp() throws Exception {

		this.conta = new Conta();
		this.conta.setNumeroAgencia(123);
		this.conta.setNumeroConta(456);
		this.conta.setTipoConta(TipoConta.corrente);

	}

	@Test
	void testObterTipoPessoa_DeveRetornarConta() {

		when(this.repository.findByNumeroAgenciaAndNumeroContaAndTipoConta(this.conta.getNumeroAgencia(),
				this.conta.getNumeroConta(), this.conta.getTipoConta())).thenReturn(this.conta);

		ContaServiceImpl service = new ContaServiceImpl(this.repository);

		Conta actual = service.obterConta(this.conta.getTipoConta(), this.conta.getNumeroAgencia(),
				this.conta.getNumeroConta());

		assertEquals(this.conta, actual);

	}

	@Test
	void testObterTipoPessoa_DeveRetornarNull() {

		when(this.repository.findByNumeroAgenciaAndNumeroContaAndTipoConta(this.conta.getNumeroAgencia(),
				this.conta.getNumeroConta(), this.conta.getTipoConta())).thenReturn(null);

		ContaServiceImpl service = new ContaServiceImpl(this.repository);

		Conta actual = service.obterConta(this.conta.getTipoConta(), this.conta.getNumeroAgencia(),
				this.conta.getNumeroConta());

		assertNull(actual);

	}

}
