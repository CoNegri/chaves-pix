package com.ng.chavespix.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.ng.chavespix.dto.AlteracaoChaveRequest;
import com.ng.chavespix.dto.InsercaoChaveRequest;
import com.ng.chavespix.dto.response.ChavePixResponse;
import com.ng.chavespix.dto.response.InsercaoResponse;
import com.ng.chavespix.entity.ChavePix;
import com.ng.chavespix.entity.Conta;
import com.ng.chavespix.enums.TipoChave;
import com.ng.chavespix.enums.TipoConta;
import com.ng.chavespix.service.impl.ChavePixServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class ChavesPixControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ChavePixServiceImpl service;

	private InsercaoChaveRequest chave;
	
	private AlteracaoChaveRequest alteracaoRequest;

	@BeforeEach
	void setUp() throws Exception {

		this.chave = new InsercaoChaveRequest();

		chave.setTipoChave(TipoChave.cpf);
		chave.setValorChave("41887678808");
		chave.setNumeroAgencia(1192);
		chave.setNumeroConta(19204);
		chave.setTipoConta(TipoConta.corrente);
		chave.setNomeCorrentista("Leonardo");
		chave.setSobrenomeCorrentista("Coelho Negri");
		
		this.alteracaoRequest = new AlteracaoChaveRequest();
		
		alteracaoRequest.setNumeroAgencia(1192);
		alteracaoRequest.setNumeroConta(19204);
		alteracaoRequest.setTipoConta(TipoConta.corrente);
		alteracaoRequest.setNomeCorrentista("Leonardo");
		alteracaoRequest.setSobrenomeCorrentista("Coelho Negri");
	}

	@Test
	void testInserirChave_TipoChaveNullDeveRetornar422() throws Exception {

		this.chave.setTipoChave(null);

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void testInserirChave_ValorChaveNullDeveRetornar422() throws Exception {

		this.chave.setValorChave(null);

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void testInserirChave_NumeroAgenciaNullDeveRetornar422() throws Exception {

		this.chave.setNumeroAgencia(null);

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void testInserirChave_NumeroContaNullDeveRetornar422() throws Exception {

		this.chave.setNumeroConta(null);

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void testInserirChave_TipoContaNullDeveRetornar422() throws Exception {

		this.chave.setTipoConta(null);

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void testInserirChave_NomeNullDeveRetornar422() throws Exception {

		this.chave.setNomeCorrentista(null);

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void testInserirChave_TipoCPFComFormatoInvalidoDeveRetornar422() throws Exception {

		this.chave.setTipoChave(TipoChave.cpf);
		this.chave.setValorChave("418876788001");

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void testInserirChave_TipoCPFComFormatoValidoDeveRetornar200() throws Exception {

		when(this.service.inserirChave(this.chave)).thenReturn(new InsercaoResponse("1"));

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isOk());
	}

	@Test
	void testInserirChave_TipoCNPJComFormatoInvalidoDeveRetornar422() throws Exception {

		this.chave.setTipoChave(TipoChave.cnpj);
		this.chave.setValorChave("41887678808");

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void testInserirChave_TipoCNPJComFormatoValidoDeveRetornar200() throws Exception {

		this.chave.setTipoChave(TipoChave.cnpj);
		this.chave.setValorChave("41887678841887");

		when(this.service.inserirChave(this.chave)).thenReturn(new InsercaoResponse("1"));

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isOk());
	}

	@Test
	void testInserirChave_TipoCelularComFormatoInvalidoDeveRetornar422() throws Exception {

		this.chave.setTipoChave(TipoChave.celular);
		this.chave.setValorChave("-5511991975391");

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void testInserirChave_TipoCelularComFormatoValidoDeveRetornar200() throws Exception {

		this.chave.setTipoChave(TipoChave.celular);
		this.chave.setValorChave("+5511991975391");

		when(this.service.inserirChave(this.chave)).thenReturn(new InsercaoResponse("1"));

		mockMvc.perform(post("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isOk());
	}

	@Test
	void testBuscarChave_IDPreenchidoComOutroFiltroDeveRetornar422() throws Exception {

		when(this.service.inserirChave(this.chave)).thenReturn(new InsercaoResponse("1"));

		mockMvc.perform(get("/chaves-pix").param("id", "1").param("numeroConta", "123"))
				.andExpect(status().isUnprocessableEntity());
	}

	@Test
	void testBuscarChave_SoIdPreenchidoDeveRetornar200() throws Exception {

		UUID uuid = UUID.randomUUID();

		ChavePix cp = new ChavePix();
		cp.setId(uuid);
		cp.setConta(new Conta());

		ChavePixResponse cps = new ChavePixResponse();
		cps.setId(cp.getId().toString());

		List<ChavePixResponse> lcps = new LinkedList<>();
		lcps.add(cps);

		when(this.service.buscarChave(any())).thenReturn(lcps);

		mockMvc.perform(get("/chaves-pix").param("id", uuid.toString())).andExpect(status().isOk());
	}

	@Test
	void testBuscarChave_AmbasDatasPreenchidasDevemRetornar422() throws Exception {

		mockMvc.perform(get("/chaves-pix").param("dataInclusao", "2022-01-01").param("dataInativacao", "2022-01-02"))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	void testBuscarChave_AgenciaNullContaComValorRetorna422() throws Exception {

		mockMvc.perform(get("/chaves-pix").param("numeroConta", "1"))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	void testBuscarChave_AgenciaComValorContaNullRetorna422() throws Exception {

		mockMvc.perform(get("/chaves-pix").param("numeroAgencia", "1"))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	void testBuscarChave_AgenciaComValorContaComValorRetorna200() throws Exception {
		
		UUID uuid = UUID.randomUUID();

		ChavePix cp = new ChavePix();
		cp.setId(uuid);
		cp.setConta(new Conta());

		ChavePixResponse cps = new ChavePixResponse();
		cps.setId(cp.getId().toString());

		List<ChavePixResponse> lcps = new LinkedList<>();
		lcps.add(cps);

		when(this.service.buscarChave(any())).thenReturn(lcps);

		mockMvc.perform(get("/chaves-pix").param("numeroAgencia", "1").param("numeroConta", "2").param("tipoChave", "cpf"))
				.andExpect(status().isOk());
	}
	
	@Test
	void testRemoverChave_DeveRetornar200() throws Exception {
		
		UUID uuid = UUID.randomUUID();
		
		ChavePix cp = new ChavePix();
		cp.setId(uuid);
		cp.setConta(new Conta());

		ChavePixResponse cps = new ChavePixResponse();
		cps.setId(cp.getId().toString());

		when(this.service.inativarChave(any())).thenReturn(cps);

		mockMvc.perform(delete("/chaves-pix/"+uuid.toString()))
				.andExpect(status().isOk());
	}
	
	@Test
	void alterarChavePix_NumeroAgenciaNullDeveRetornar422() throws Exception {

		this.chave.setNumeroAgencia(null);

		mockMvc.perform(put("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void alterarChavePix_NumeroContaNullDeveRetornar422() throws Exception {

		this.chave.setNumeroConta(null);

		mockMvc.perform(put("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void alterarChavePix_TipoContaNullDeveRetornar422() throws Exception {

		this.chave.setTipoConta(null);

		mockMvc.perform(put("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}

	@Test
	void alterarChavePix_NomeNullDeveRetornar422() throws Exception {

		this.chave.setNomeCorrentista(null);

		mockMvc.perform(put("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(this.chave))).andExpect(status().isUnprocessableEntity());
	}
	
	
	@Test
	void alterarChavePix_DeveRetornar200() throws Exception {
		
		UUID uuid = UUID.randomUUID();
		
		
		ChavePix cp = new ChavePix();
		cp.setId(uuid);
		cp.setConta(new Conta());

		ChavePixResponse cps = new ChavePixResponse();
		cps.setId(cp.getId().toString());

		when(this.service.alterarChave(any())).thenReturn(cps);
		
		this.alteracaoRequest.setId(uuid);

		mockMvc.perform(put("/chaves-pix").contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(this.alteracaoRequest))).andExpect(status().isOk());
	}

}
