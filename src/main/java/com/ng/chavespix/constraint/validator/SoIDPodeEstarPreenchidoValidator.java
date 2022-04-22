package com.ng.chavespix.constraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ng.chavespix.constraint.SoIDPodeEstarPreenchido;
import com.ng.chavespix.dto.ConsultaChaveRequest;

public class SoIDPodeEstarPreenchidoValidator
		implements ConstraintValidator<SoIDPodeEstarPreenchido, ConsultaChaveRequest> {

	@Override
	public boolean isValid(ConsultaChaveRequest value, ConstraintValidatorContext context) {

		if (value.getId() != null) {
			return value.getNumeroAgencia() == null && value.getNumeroConta() == null
					&& value.getDataInativacao() == null & value.getDataInclusao() == null
					&& value.getNomeCorrentista() == null && value.getTipoChave() == null;
		} else {
			return true;
		}
	}

}
