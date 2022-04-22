package com.ng.chavespix.enums;

import java.util.regex.Pattern;

public enum TipoChave {
	
	celular("^[+][0-9]{2}[0-9]{1,3}[0-9]{9}"), 
	email("(?=.{3,77}$)[a-z0-9\\._%+-]+@[a-z0-9\\.-]+"), 
	cpf("^[0-9]{11}"), 
	cnpj("^[0-9]{14}"), 
	aleatorio("^[0-9a-z]{1,36}");
	
	private Pattern pattern;
	
	TipoChave(String regex) {
		pattern = Pattern.compile(regex);
	}
	
	public boolean isMatch(String toTest) {
		return pattern.matcher(toTest).matches();
	}
}
