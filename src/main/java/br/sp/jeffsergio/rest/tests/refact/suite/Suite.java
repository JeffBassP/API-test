package br.sp.jeffsergio.rest.tests.refact.suite;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import br.sp.jeffsergio.rest.core.BaseTests;
import br.sp.jeffsergio.rest.tests.refact.AuthTest;
import br.sp.jeffsergio.rest.tests.refact.ContasTest;
import br.sp.jeffsergio.rest.tests.refact.MovimentacaoTest;
import br.sp.jeffsergio.rest.tests.refact.SaldoTest;
import io.restassured.RestAssured;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
	ContasTest.class,
	MovimentacaoTest.class,
	SaldoTest.class,
	AuthTest.class
})
public class Suite extends BaseTests {
	
	@BeforeClass
	public static void login() {
		Map<String, String> login = new HashMap<>();
		login.put("email","jeferson_jfs@hotmail.com");
		login.put("senha","password");
		
		String TOKEN = given()
			.body(login)
		.when()
			.post("/signin")
		.then()
			.statusCode(200)
			.extract().path("token")
			;
		RestAssured.requestSpecification.header("Authorization", "JWT " + TOKEN);
		
		RestAssured.get("/reset").then().statusCode(200);
		
	}
	

}
