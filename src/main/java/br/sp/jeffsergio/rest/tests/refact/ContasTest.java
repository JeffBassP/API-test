package br.sp.jeffsergio.rest.tests.refact;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import br.sp.jeffsergio.rest.core.BaseTests;
import br.sp.jeffsergio.rest.utils.BarrigaUtils;

public class ContasTest extends BaseTests {

	@Test
	public void deveIncluirContaComSucesso() {
		
		given()
			.body("{ \"nome\" : \"Conta incluida\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(201)
		;
	}
	
	@Test	
	public void deveAlterarContaComSucesso() {
		Integer CONTA_ID = BarrigaUtils.getIdDaContaPeloNome("Conta para alterar");
		given()
			.body("{ \"nome\" : \"Conta Alterada\"}")
			.pathParam("id", CONTA_ID)
		.when()
			.put("/contas/{id}")
		.then()
			.statusCode(200)
			.body("nome", is("Conta Alterada"))
		;
	}
	
	@Test
	public void naoDeveIncluirContaComMesmoNome() {
		
		given()
			.body("{ \"nome\" : \"Conta mesmo nome\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(400)
			.body("error", is("Já existe uma conta com esse nome!"))
		;
	}
	
}
