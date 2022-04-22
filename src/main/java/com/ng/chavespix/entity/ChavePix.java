package com.ng.chavespix.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.ng.chavespix.enums.TipoChave;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_chave_pix")
public class ChavePix {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Type(type = "uuid-char")
	@Column(name = "id")
	private UUID id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_chave")
	private TipoChave tipoChave;
	
	@Column(name = "valor_chave")
	private String valorChave;
	
	@Column(name = "data_hora_inclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;
	
	@Column(name = "data_hora_inativacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInativacao;
	
	@Column(name = "nome_exibicao")
	private String nomeExibicao;
	
	@Column(name = "sobrenome_exibicao")
	private String sobrenomeExibicao;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="numero_agencia"),
		@JoinColumn(name="numero_conta"),
		@JoinColumn(name="tipo_conta")
		})
	private Conta conta;
	

	public ChavePix() {}
}
