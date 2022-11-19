package com.ingseoft.swapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// pruebas
// - arrange / act / verify
// - given / when / Then

@SpringBootTest
class SwAppApplicationTests {

	// fixture

	@BeforeAll
	static void antesDeTodasLasPruebas() {
		// crea el contenedor para la prueba
	}

	@BeforeEach
	void antesDeCadaPrueba() {
		// crea el esquema de base de datos
		// inserto datos
	}

	// prueba

	@Test
	void contextLoads() {
	}

	@Test
	void prueba1() {
	}

	@Test
	void prueba2() {

	}

	// cleanup

	@AfterEach
	void luegoDeCadaPrueba() {
		// borro las tablas
		// borro la base de datos
	}

	@AfterAll
	static void luegoDeTodasLasPruebas() {
		// apago el contenedor
	}

}
