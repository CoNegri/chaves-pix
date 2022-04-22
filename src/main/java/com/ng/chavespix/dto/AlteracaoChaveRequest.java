package com.ng.chavespix.dto;

import java.util.UUID;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.ng.chavespix.enums.TipoConta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Validated
@NoArgsConstructor
public class AlteracaoChaveRequest {

	@NotNull(message = "{id.notnull}")
	private UUID id;

	@NotNull(message = "{tipoChave.notnull}")
	private TipoConta tipoConta;

	@NotNull(message = "{numeroAgencia.notnull}")
	@Digits(integer = 4, fraction = 0, message = "{numeroAgencia.digits}")
	private Integer numeroAgencia;

	@NotNull(message = "{numeroConta.notnull}")
	@Digits(integer = 8, fraction = 0, message = "{numeroConta.digits}")
	private Integer numeroConta;

	@NotNull(message = "{nomeCorrentista.notnull}")
	@Size(max = 30, message = "{nomeCorrentista.size}")
	private String nomeCorrentista;

	@Size(max = 45, message = "{sobrenomeCorrentista.size}")
	private String sobrenomeCorrentista;

}
