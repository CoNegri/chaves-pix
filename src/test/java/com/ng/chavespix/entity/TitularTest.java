package com.ng.chavespix.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ng.chavespix.enums.TipoPessoa;

class TitularTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSetGetId() {
		Integer id = 1;
		Titular t = new Titular();
		t.setId(id);
		Integer actual = t.getId();
		assertEquals(id, actual);
	}

	@Test
	void testSetGetTipoPessoa() {
		TipoPessoa tipoPessoa = TipoPessoa.pf;
		Titular t = new Titular();
		t.setTipoPessoa(tipoPessoa);
		TipoPessoa actual = t.getTipoPessoa();
		assertEquals(tipoPessoa, actual);	
	}

	@Test
	void testSetGetContas() {
		Set<Conta> contas = new LinkedHashSet<>();
		contas.add(new Conta());
		Titular t = new Titular();
		t.setContas(contas);
		Set<Conta> actual = t.getContas();
		assertEquals(contas, actual);	
	}



}
