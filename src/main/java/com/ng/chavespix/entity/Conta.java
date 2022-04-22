package com.ng.chavespix.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ng.chavespix.enums.TipoConta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(ContaPrimaryKey.class)
@Table(name = "tb_conta")
public class Conta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_conta")
	private TipoConta tipoConta;
	
	@Id
	@Column(name = "numero_agencia")
	private Integer numeroAgencia;
	
	@Id
	@Column(name = "numero_conta")
	private Integer numeroConta;
	
	@ManyToMany
	@JoinTable(
			name = "tb_titulares",
			joinColumns = {
				@JoinColumn(name = "tipo_conta", referencedColumnName = "tipo_conta"),
				@JoinColumn(name = "numero_agencia", referencedColumnName = "numero_agencia"),
				@JoinColumn(name = "numero_conta", referencedColumnName = "numero_conta")
				},
			inverseJoinColumns = @JoinColumn(name = "id")
			)
	private Set<Titular> titulares;

	
}
