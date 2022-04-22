package com.ng.chavespix.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ng.chavespix.enums.TipoChave;
import com.ng.chavespix.enums.TipoConta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ChavePixResponse {
	
	private String id;
	private TipoChave tipoChave;
	private String valorChave;
	private TipoConta tipoConta;
	private Integer numeroAgencia;
	private Integer numeroConta;
	private String nomeCorrentista;
	private String sobrenomeCorrentista;
	private String dataInclusao;
	private String dataInativacao;

}
