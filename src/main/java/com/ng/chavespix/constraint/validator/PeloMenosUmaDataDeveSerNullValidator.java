package com.ng.chavespix.constraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ng.chavespix.constraint.PeloMenosUmaDataDeveSerNull;
import com.ng.chavespix.dto.ConsultaChaveRequest;

public class PeloMenosUmaDataDeveSerNullValidator
		implements ConstraintValidator<PeloMenosUmaDataDeveSerNull, ConsultaChaveRequest> {

	@Override
	public boolean isValid(ConsultaChaveRequest req, ConstraintValidatorContext context) {
		return req.getDataInativacao() == null || req.getDataInclusao() == null;
	}

}
