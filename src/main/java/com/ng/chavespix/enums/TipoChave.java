package com.ng.chavespix.enums;

import java.util.regex.Pattern;

public enum TipoChave {
	
	celular("^[+][0-9]{2}[0-9]{1,3}[0-9]{9}"), 
	email("/^[a-zA-Z0-9,]*[@][a-zA-Z0-9,]*$/{77}"), 
	cpf("^[0-9]{11}"), 
	cnpj("^[0-9]{14}"), 
	aleatorio("^[0-9a-z]{36}");
	
	private Pattern pattern;
	
	TipoChave(String regex) {
		pattern = Pattern.compile(regex);
	}
	
	public boolean isMatch(String toTest) {
		return pattern.matcher(toTest).matches();
	}
}
