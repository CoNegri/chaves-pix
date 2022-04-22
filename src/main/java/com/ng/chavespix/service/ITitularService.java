package com.ng.chavespix.service;

import com.ng.chavespix.entity.Conta;
import com.ng.chavespix.enums.TipoPessoa;

public interface ITitularService {
	
	TipoPessoa obterTipoPessoa(Conta conta);

}
