package com.ng.chavespix.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ng.chavespix.dto.AlteracaoChaveRequest;
import com.ng.chavespix.dto.ConsultaChaveRequest;
import com.ng.chavespix.dto.InsercaoChaveRequest;
import com.ng.chavespix.dto.response.ChavePixResponse;
import com.ng.chavespix.dto.response.InsercaoResponse;
import com.ng.chavespix.exception.EntidadeNotFoundException;
import com.ng.chavespix.exception.ValidacaoException;
import com.ng.chavespix.service.impl.ChavePixServiceImpl;

@Validated
@RestController
@RequestMapping("chaves-pix")
public class ChavesPixController {

	private ChavePixServiceImpl service;

	@Autowired
	public ChavesPixController(ChavePixServiceImpl chaveService) {
		this.service = chaveService;
	}

	@PostMapping
	public ResponseEntity<InsercaoResponse> inserirChave(@RequestBody @Valid InsercaoChaveRequest request)
			throws ValidacaoException, EntidadeNotFoundException {
		InsercaoResponse response = this.service.inserirChave(request);
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<ChavePixResponse> alterarChave(@RequestBody @Valid AlteracaoChaveRequest request)
			throws EntidadeNotFoundException, ValidacaoException {
		ChavePixResponse response = this.service.alterarChave(request);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ChavePixResponse> inativarChave(@PathVariable("id") String id)
			throws EntidadeNotFoundException, ValidacaoException {
		ChavePixResponse response = this.service.inativarChave(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<ChavePixResponse>> consultarChave(@Valid ConsultaChaveRequest req) {
		List<ChavePixResponse> listaChaves = this.service.buscarChave(req);
		return ResponseEntity.ok(listaChaves);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ChavePixResponse> consultarChave(@PathVariable("id") String id)
			throws EntidadeNotFoundException {
		ChavePixResponse chaveResponse = this.service.buscarChavePorId(id);
		return ResponseEntity.ok(chaveResponse);
	}

}
