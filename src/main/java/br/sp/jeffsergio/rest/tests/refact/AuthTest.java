package br.sp.jeffsergio.rest.tests.refact;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import br.sp.jeffsergio.rest.core.BaseTests;
import io.restassured.RestAssured;
import io.restassured.specification.FilterableRequestSpecification;

public class AuthTest extends BaseTests {

	@Test
	public void t11_naoDeveAcessarAPISemToken() {
		FilterableRequestSpecification req = (FilterableRequestSpecification) RestAssured.requestSpecification;
		req.removeHeader("Authorization");
		
		given()
		.when()
			.get("/contas")
		.then()
			.statusCode(401)
			;
	}	
}
