package com.ng.chavespix.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ng.chavespix.entity.Conta;
import com.ng.chavespix.enums.TipoConta;
import com.ng.chavespix.repository.ContaRepository;
import com.ng.chavespix.service.IContaService;

@Service
public class ContaServiceImpl implements IContaService{

	private ContaRepository repository;

	@Autowired
	public ContaServiceImpl(ContaRepository repository) {
		this.repository = repository;
	}
	
	public Conta obterConta(TipoConta tipoConta, Integer numeroAgencia, Integer numeroConta) {
		Conta conta = this.repository.findByNumeroAgenciaAndNumeroContaAndTipoConta(numeroAgencia, numeroConta, tipoConta);
		return conta;
	}

}
