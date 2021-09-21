package com.aritana.restAssured;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
        import org.junit.jupiter.api.Test;

        import static io.restassured.RestAssured.given;

public class ValidacaoJson {
    @Test
    public void deveValidarSchemaJson() {

        given()
                .log().all() //mostra parametros enviados
                .when()
                .get("https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("users.json"));
    }
}