package com.ng.chavespix.constraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ng.chavespix.constraint.ChaveDeveRespeitarTipo;
import com.ng.chavespix.dto.InsercaoChaveRequest;

public class ChaveDeveRespeitarTipoValidator
		implements ConstraintValidator<ChaveDeveRespeitarTipo, InsercaoChaveRequest> {

	@Override
	public boolean isValid(InsercaoChaveRequest value, ConstraintValidatorContext context) {
		if (value.getTipoChave() != null && value.getValorChave() != null) {
			return value.getTipoChave().isMatch(value.getValorChave());
		}
		return true;

	}

}
