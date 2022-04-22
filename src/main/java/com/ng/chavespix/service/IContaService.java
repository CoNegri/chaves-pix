package com.ng.chavespix.service;

import com.ng.chavespix.entity.Conta;
import com.ng.chavespix.enums.TipoConta;

public interface IContaService {
	
	Conta obterConta(TipoConta tipoConta, Integer numeroAgencia, Integer numeroConta);
}
