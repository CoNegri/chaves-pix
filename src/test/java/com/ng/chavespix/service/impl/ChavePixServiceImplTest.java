package com.ng.chavespix.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ng.chavespix.dto.AlteracaoChaveRequest;
import com.ng.chavespix.dto.ConsultaChaveRequest;
import com.ng.chavespix.dto.InsercaoChaveRequest;
import com.ng.chavespix.dto.response.ChavePixResponse;
import com.ng.chavespix.dto.response.InsercaoResponse;
import com.ng.chavespix.entity.ChavePix;
import com.ng.chavespix.entity.Conta;
import com.ng.chavespix.enums.TipoChave;
import com.ng.chavespix.enums.TipoConta;
import com.ng.chavespix.enums.TipoPessoa;
import com.ng.chavespix.exception.EntidadeNotFoundException;
import com.ng.chavespix.exception.ValidacaoException;
import com.ng.chavespix.repository.ChavePixRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ChavePixServiceImplTest {

	@MockBean
	private ChavePixRepository chaveRepository;

	@MockBean
	private ContaServiceImpl contaService;

	@MockBean
	private TitularServiceImpl titularService;

	private InsercaoChaveRequest request;

	private AlteracaoChaveRequest alteracaoRequest;

	private Conta conta;

	@BeforeEach
	void setUp() throws Exception {

		this.request = new InsercaoChaveRequest();

		this.request.setTipoChave(TipoChave.cpf);
		this.request.setValorChave("41887678808");
		this.request.setTipoConta(TipoConta.corrente);
		this.request.setNumeroAgencia(1234);
		this.request.setNumeroConta(12345678);
		this.request.setNomeCorrentista("nombre");
		this.request.setSobrenomeCorrentista("apellido");

		this.conta = new Conta();

		this.conta.setTipoConta(TipoConta.corrente);
		this.conta.setNumeroAgencia(1234);
		this.conta.setNumeroConta(12345678);

		this.alteracaoRequest = new AlteracaoChaveRequest();

		this.alteracaoRequest.setId(UUID.randomUUID());
		this.alteracaoRequest.setNomeCorrentista("nuevo nombre");
		this.alteracaoRequest.setSobrenomeCorrentista("nuevo apellido");
		this.alteracaoRequest.setTipoConta(TipoConta.corrente);
		this.alteracaoRequest.setNumeroAgencia(4578);
		this.alteracaoRequest.setNumeroConta(12345699);

	}

	@Test
	void testInserirChave_PFComChavesDisponiveisDevePermitirInsercao() throws ValidacaoException, ValidacaoException {

		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(TipoPessoa.pf);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(3L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.conta.getTipoConta(), this.conta.getNumeroAgencia(),
				this.conta.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		InsercaoResponse result = service.inserirChave(this.request);

		assertEquals(uuid.toString(), result.getId());

	}

	@Test
	void testInserirChave_PFSemChavesDisponiveisDeveRetornarValidacaoException() {

		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(TipoPessoa.pf);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(5L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.conta.getTipoConta(), this.conta.getNumeroAgencia(),
				this.conta.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		assertThrows(ValidacaoException.class, () -> {
			service.inserirChave(this.request);
		});

	}

	@Test
	void testInserirChave_PJComChavesDisponiveisDevePermitirInsercao() throws ValidacaoException, ValidacaoException {

		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(TipoPessoa.pj);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(19L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.conta.getTipoConta(), this.conta.getNumeroAgencia(),
				this.conta.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		InsercaoResponse result = service.inserirChave(this.request);

		assertEquals(uuid.toString(), result.getId());

	}

	@Test
	void testInserirChave_PJSemChavesDisponiveisDeveRetornarValidacaoException() {

		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(TipoPessoa.pj);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(20L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.conta.getTipoConta(), this.conta.getNumeroAgencia(),
				this.conta.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		assertThrows(ValidacaoException.class, () -> {
			service.inserirChave(this.request);
		});

	}

	@Test
	void testInserirChave_ContaNaoCadastradaDeveRetornarValidacaoException() {

		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(TipoPessoa.pj);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(20L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.conta.getTipoConta(), this.conta.getNumeroAgencia(),
				this.conta.getNumeroConta())).thenReturn(null);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		assertThrows(ValidacaoException.class, () -> {
			service.inserirChave(this.request);
		});

	}

	@Test
	void testInserirChave_ChaveJaCadastradaDeveRetornarValidacaoException() {

		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);

		when(this.chaveRepository.findFirstByValorChave(this.request.getValorChave())).thenReturn(new ChavePix());
		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(TipoPessoa.pj);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(20L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.conta.getTipoConta(), this.conta.getNumeroAgencia(),
				this.conta.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		assertThrows(ValidacaoException.class, () -> {
			service.inserirChave(this.request);
		});

	}

	@Test
	void testInserirChave_TipoPessoNullDeveRetornarValidacaoException() {

		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(null);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(20L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.conta.getTipoConta(), this.conta.getNumeroAgencia(),
				this.conta.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		assertThrows(ValidacaoException.class, () -> {
			service.inserirChave(this.request);
		});

	}

	@Test
	void testAlterarChave_IDNaoExisteDeveRetornarEntidadeNotFoundException() {
		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(null);
		when(this.chaveRepository.findFirstById(this.alteracaoRequest.getId())).thenReturn(null);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(20L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.alteracaoRequest.getTipoConta(), this.alteracaoRequest.getNumeroAgencia(),
				this.alteracaoRequest.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		assertThrows(EntidadeNotFoundException.class, () -> {
			service.alterarChave(this.alteracaoRequest);
		});
	}

	@Test
	void testAlterarChave_IDExisteMasJaEstaInativadaDeveRetornarValidacaoException() {
		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);
		chave.setDataInativacao(new Date());

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(TipoPessoa.pf);
		when(this.chaveRepository.findFirstById(this.alteracaoRequest.getId())).thenReturn(chave);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(20L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.alteracaoRequest.getTipoConta(), this.alteracaoRequest.getNumeroAgencia(),
				this.alteracaoRequest.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		assertThrows(ValidacaoException.class, () -> {
			service.alterarChave(this.alteracaoRequest);
		});
	}

	@Test
	void testAlterarChave_IDExisteMasNovaContaNullDeveRetornarValidacaoException() {
		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(TipoPessoa.pf);
		when(this.chaveRepository.findFirstById(this.alteracaoRequest.getId())).thenReturn(chave);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(20L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.alteracaoRequest.getTipoConta(), this.alteracaoRequest.getNumeroAgencia(),
				this.alteracaoRequest.getNumeroConta())).thenReturn(null);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		assertThrows(ValidacaoException.class, () -> {
			service.alterarChave(this.alteracaoRequest);
		});
	}
	
	@Test
	void testAlterarChave_IDExisteContaTambemMasNaoHaChavesDeveRetornarValidacaoException() {
		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);
		chave.setConta(this.conta);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(TipoPessoa.pf);
		when(this.chaveRepository.findFirstById(this.alteracaoRequest.getId())).thenReturn(chave);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(20L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.alteracaoRequest.getTipoConta(), this.alteracaoRequest.getNumeroAgencia(),
				this.alteracaoRequest.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		assertThrows(ValidacaoException.class, () -> {
			service.alterarChave(this.alteracaoRequest);
		});
	}
	
	@Test
	void testAlterarChave_TodasCondicoesSaoCumpridasEntaoDeveRetornarChavePixResponse() throws EntidadeNotFoundException, ValidacaoException {
		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);
		chave.setConta(this.conta);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(TipoPessoa.pf);
		when(this.chaveRepository.findFirstById(this.alteracaoRequest.getId())).thenReturn(chave);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(4L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.alteracaoRequest.getTipoConta(), this.alteracaoRequest.getNumeroAgencia(),
				this.alteracaoRequest.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);
		
		ChavePixResponse actual = service.alterarChave(this.alteracaoRequest);
		
		assertEquals(chave.getId().toString(), actual.getId());
		
	}
	
	@Test
	void testInativarChave_IDNaoExisteDeveRetornarEntidadeNotFoundException() throws EntidadeNotFoundException, ValidacaoException {
		
		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(null);
		when(this.chaveRepository.findFirstById(uuid)).thenReturn(null);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(20L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.alteracaoRequest.getTipoConta(), this.alteracaoRequest.getNumeroAgencia(),
				this.alteracaoRequest.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		assertThrows(EntidadeNotFoundException.class, () -> {
			service.inativarChave(uuid.toString());
		});
	}
	
	@Test
	void testInativarChave_IDExisteMasEstaInativadoDeveRetornarValidacaoException() throws EntidadeNotFoundException, ValidacaoException {
		
		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);
		chave.setDataInativacao(new Date());

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(null);
		when(this.chaveRepository.buscarChaveID(uuid.toString())).thenReturn(chave);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(20L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.alteracaoRequest.getTipoConta(), this.alteracaoRequest.getNumeroAgencia(),
				this.alteracaoRequest.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		assertThrows(ValidacaoException.class, () -> {
			service.inativarChave(uuid.toString());
		});
	}
	
	@Test
	void testInativarChave_TodasCondicoesSaoCumpridasEntaoDeveRetornarChavePixResponse() throws EntidadeNotFoundException, ValidacaoException {
		
		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);
		chave.setConta(this.conta);

		when(this.titularService.obterTipoPessoa(this.conta)).thenReturn(null);
		when(this.chaveRepository.buscarChaveID(uuid.toString())).thenReturn(chave);
		when(this.chaveRepository.obterQuantidadeChaves(this.conta)).thenReturn(20L);
		when(this.chaveRepository.save(Mockito.any(ChavePix.class))).thenReturn(chave);
		when(this.contaService.obterConta(this.alteracaoRequest.getTipoConta(), this.alteracaoRequest.getNumeroAgencia(),
				this.alteracaoRequest.getNumeroConta())).thenReturn(this.conta);

		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);

		ChavePixResponse actual = service.inativarChave(uuid.toString());
		
		assertEquals(chave.getId().toString(), actual.getId());
	}


	@Test
	void testBuscarChave_DeveRetornarListaDeChavesComTamanho1() {
		
		UUID uuid = UUID.randomUUID();

		ChavePix chave = new ChavePix();
		chave.setId(uuid);
		chave.setConta(this.conta);
		
		List<ChavePix> chaves = new LinkedList<>();
		chaves.add(chave);
		
		when(this.chaveRepository.consultarChaves(any())).thenReturn(chaves);
		
		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);
		
		List<ChavePixResponse> response = service.buscarChave(new ConsultaChaveRequest());
		
		assertTrue(response.size() == 1);
	}
	
	@Test
	void buscarChavePorId_DeveRetornarNull() {
		
		when(this.chaveRepository.buscarChaveID(any())).thenReturn(null);
		
		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);	
		
		ChavePixResponse response = service.buscarChavePorId("");
		
		assertNull(response);
		
	}
	
	@Test
	void buscarChavePorId_DeveNaoRetornarNull() {
		
		UUID uuid = UUID.randomUUID();
		
		ChavePix chave = new ChavePix();
		chave.setId(uuid);
		chave.setConta(this.conta);

		when(this.chaveRepository.buscarChaveID(any())).thenReturn(chave);
		
		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);	
				
		ChavePixResponse response = service.buscarChavePorId("");
		
		assertNotNull(response);
		
	}
	
	@Test
	void buscarConvertToDto_DataInclusaoNotNullEPatternSet() {
		
		UUID uuid = UUID.randomUUID();
		
		ChavePix chave = new ChavePix();
		chave.setId(uuid);
		chave.setConta(this.conta);
		chave.setDataInclusao(new Date());

		when(this.chaveRepository.buscarChaveID(any())).thenReturn(chave);
		
		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);	
				
		ChavePixResponse response = service.buscarChavePorId("");
		
		assertNotEquals("", response.getDataInclusao());
		
	}
	
	@Test
	void buscarConvertToDto_DataInativacaoNotNullEPatternSet() {
		
		UUID uuid = UUID.randomUUID();
		
		ChavePix chave = new ChavePix();
		chave.setId(uuid);
		chave.setConta(this.conta);
		chave.setDataInativacao(new Date());

		when(this.chaveRepository.buscarChaveID(any())).thenReturn(chave);
		
		ChavePixServiceImpl service = new ChavePixServiceImpl(this.chaveRepository, this.contaService,
				this.titularService);	
				
		ChavePixResponse response = service.buscarChavePorId("");
		
		assertNotEquals("", response.getDataInativacao());
		
	}

}
