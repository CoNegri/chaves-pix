package com.ng.chavespix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ng.chavespix.entity.Conta;
import com.ng.chavespix.entity.Titular;

@Repository
public interface TitularRepository extends JpaRepository<Titular, Integer> {

	@Query(" SELECT t"
			+ " FROM Titular t"
			+ " JOIN t.contas c"
			+ " WHERE c.numeroConta = :#{#conta.numeroConta}"
			+ " AND c.numeroAgencia = :#{#conta.numeroAgencia}"
			+ " AND c.tipoConta = :#{#conta.tipoConta}")
	public List<Titular> obterTitularConta(Conta conta);
	
}
