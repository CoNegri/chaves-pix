package com.ng.chavespix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ng.chavespix.entity.Conta;
import com.ng.chavespix.entity.ContaPrimaryKey;
import com.ng.chavespix.enums.TipoConta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, ContaPrimaryKey> {

	Conta findByNumeroAgenciaAndNumeroContaAndTipoConta(int numeroAgencia, int numeroConta,
			TipoConta tipoConta);

}


