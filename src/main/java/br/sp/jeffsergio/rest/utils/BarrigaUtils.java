package br.sp.jeffsergio.rest.utils;

import io.restassured.RestAssured;

public class BarrigaUtils {
	
	public static Integer getIdDaContaPeloNome(String nome) {
		return RestAssured.get("/contas?nome="+ nome).then().extract().path("id[0]");
	}
	

}
