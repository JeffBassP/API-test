package br.sp.jeffsergio.rest.tests.refact;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import br.sp.jeffsergio.rest.core.BaseTests;
import io.restassured.RestAssured;

public class SaldoTest extends BaseTests {
	
	@Test
	public void deveCalcularSaldoContas() {
		Integer CONTA_ID = getIdDaContaPeloNome("Conta para saldo");
		given()
		.when()
			.get("/saldo")
		.then()
			.statusCode(200)
			.body("find{it.conta_id == " + CONTA_ID + "}.saldo", is("534.00"))
		;
	}
	
	public Integer getIdDaContaPeloNome(String nome) {
		return RestAssured.get("/contas?nome="+ nome).then().extract().path("id[0]");
	}
	
}
