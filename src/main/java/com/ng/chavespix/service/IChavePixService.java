package com.ng.chavespix.service;

import java.util.List;

import com.ng.chavespix.dto.AlteracaoChaveRequest;
import com.ng.chavespix.dto.ConsultaChaveRequest;
import com.ng.chavespix.dto.InsercaoChaveRequest;
import com.ng.chavespix.dto.response.ChavePixResponse;
import com.ng.chavespix.dto.response.InsercaoResponse;
import com.ng.chavespix.exception.EntidadeNotFoundException;
import com.ng.chavespix.exception.ValidacaoException;

public interface IChavePixService {

	InsercaoResponse inserirChave(InsercaoChaveRequest request) throws ValidacaoException, EntidadeNotFoundException;

	ChavePixResponse alterarChave(AlteracaoChaveRequest request) throws  ValidacaoException, EntidadeNotFoundException;

	ChavePixResponse inativarChave(String id) throws  ValidacaoException, EntidadeNotFoundException;

	List<ChavePixResponse> buscarChave(ConsultaChaveRequest request);
	
	ChavePixResponse buscarChavePorId(String id);

}
