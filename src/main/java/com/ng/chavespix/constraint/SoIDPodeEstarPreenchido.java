package com.ng.chavespix.constraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ng.chavespix.constraint.validator.SoIDPodeEstarPreenchidoValidator;

@Constraint(validatedBy = SoIDPodeEstarPreenchidoValidator.class)
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface SoIDPodeEstarPreenchido {

	String message() default "{validacao.preenchimento.id}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
