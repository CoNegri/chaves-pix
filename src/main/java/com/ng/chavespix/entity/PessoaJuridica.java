package com.ng.chavespix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pessoa_juridica")
public class PessoaJuridica extends Titular {
	
	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "razao_social")
	private String razaoSocial;
	
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	
	
}
