package com.ng.chavespix.constraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ng.chavespix.constraint.AgenciaContaDependentes;
import com.ng.chavespix.dto.ConsultaChaveRequest;

public class AgenciaContaDependentesValidator
		implements ConstraintValidator<AgenciaContaDependentes, ConsultaChaveRequest> {

	@Override
	public boolean isValid(ConsultaChaveRequest req, ConstraintValidatorContext context) {

		return (req.getNumeroAgencia() == null && req.getNumeroConta() == null)
				|| (req.getNumeroAgencia() != null && req.getNumeroConta() != null);
	}

}
