package com.ng.chavespix.dto;

import javax.validation.constraints.Pattern;

import com.ng.chavespix.constraint.AgenciaContaDependentes;
import com.ng.chavespix.constraint.PeloMenosUmaDataDeveSerNull;
import com.ng.chavespix.constraint.SoIDPodeEstarPreenchido;
import com.ng.chavespix.enums.TipoChave;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@SoIDPodeEstarPreenchido
@AgenciaContaDependentes
@PeloMenosUmaDataDeveSerNull
@NoArgsConstructor
public class ConsultaChaveRequest {

	private String id;
	private TipoChave tipoChave;
	private Integer numeroAgencia;
	private Integer numeroConta;
	private String nomeCorrentista;
	@Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "{formato.data}")
	private String dataInclusao;
	@Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "{formato.data}")
	private String dataInativacao;

}
