package com.ng.chavespix.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ng.chavespix.dto.ConsultaChaveRequest;
import com.ng.chavespix.entity.ChavePix;
import com.ng.chavespix.entity.Conta;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePix, String> {
	
	ChavePix findFirstById(UUID id);	
	
	@Query("SELECT c FROM ChavePix c WHERE CAST(c.id as text) = :#{#id}")
	ChavePix buscarChaveID(String id);	

	ChavePix findFirstByValorChave(String valorChave);	
	
	@Query("SELECT COUNT(c)"
			+ " FROM ChavePix c"
			+ " WHERE c.conta.numeroConta = :#{#conta.numeroConta}"
			+ " AND c.conta.numeroAgencia = :#{#conta.numeroAgencia}"
			+ " AND c.conta.tipoConta = :#{#conta.tipoConta}"
			+ " AND c.dataInativacao IS NULL")
	long obterQuantidadeChaves(@Param("conta") Conta conta);
	
	@Query(value = "SELECT c "
			+ " FROM ChavePix c"
			+ " WHERE (:#{#req.id} IS NULL OR CAST(c.id as text) = :#{#req.id})"
			+ " AND (:#{#req.tipoChave} IS NULL OR c.tipoChave = :#{#req.tipoChave})"
			+ " AND (:#{#req.numeroAgencia} IS NULL OR c.conta.numeroAgencia = :#{#req.numeroAgencia})"
			+ " AND (:#{#req.numeroConta} IS NULL OR c.conta.numeroConta = :#{#req.numeroConta})"
			+ " AND (:#{#req.nomeCorrentista} IS NULL OR c.nomeExibicao = :#{#req.nomeCorrentista})"
			+ " AND (:#{#req.dataInclusao} IS NULL OR   CAST(CAST(c.dataInclusao AS date) AS text) = :#{#req.dataInclusao})"
			+ " AND (:#{#req.dataInativacao} IS NULL OR CAST(CAST(c.dataInativacao AS date) AS text) = :#{#req.dataInativacao})")
	List<ChavePix> consultarChaves(@Param("req") ConsultaChaveRequest req);

}
