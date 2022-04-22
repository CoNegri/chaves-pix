package com.ng.chavespix.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ng.chavespix.entity.Conta;
import com.ng.chavespix.entity.Titular;
import com.ng.chavespix.enums.TipoPessoa;
import com.ng.chavespix.repository.TitularRepository;
import com.ng.chavespix.service.ITitularService;

@Service
public class TitularServiceImpl implements ITitularService {

	private TitularRepository repository;

	@Autowired
	public TitularServiceImpl(TitularRepository repository) {
		this.repository = repository;
	}

	public TipoPessoa obterTipoPessoa(Conta conta) {
		List<Titular> titulares = this.repository.obterTitularConta(conta);
		if ( titulares == null  || titulares.isEmpty()) {
			return null;
		} else {
			return titulares.get(0).getTipoPessoa();
		}
	}
}
