package com.ng.chavespix.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.ng.chavespix.constraint.ChaveDeveRespeitarTipo;
import com.ng.chavespix.enums.TipoChave;
import com.ng.chavespix.enums.TipoConta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@ChaveDeveRespeitarTipo
public class InsercaoChaveRequest {

	@NotNull(message = "{tipoChave.notnull}")
	TipoChave tipoChave;

	@NotNull(message = "{valorChave.notnull}")
	private String valorChave;

	@NotNull(message = "{tipoConta.notnull}")
	private TipoConta tipoConta;

	@NotNull(message = "{numeroAgencia.notnull}")
	@Digits(integer = 4, fraction = 0, message = "{numeroAgencia.digits}")
	private Integer numeroAgencia;

	@NotNull(message = "{numeroConta.notnull}")
	@Digits(integer = 8, fraction = 0, message = "{numeroConta.digits}")
	private Integer numeroConta;

	@NotNull(message = "{nomeCorrentist.notnull}")
	@Size(max = 30, message = "{nomeCorrentista.size}")
	private String nomeCorrentista;

	@Size(max = 45, message = "{sobrenomeCorrentista.size}")
	private String sobrenomeCorrentista;

}
