package com.ng.chavespix.entity;

import java.io.Serializable;

import com.ng.chavespix.enums.TipoConta;

import lombok.Getter;

@Getter
public class ContaPrimaryKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private TipoConta tipoConta;
	private int numeroAgencia;
	private int numeroConta;
}
