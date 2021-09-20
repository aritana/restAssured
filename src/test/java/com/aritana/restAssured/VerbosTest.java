package com.aritana.restAssured;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class VerbosTest {

    @Test
    public void deveSalvarUsuario(){

        given()
                .log().all() //mostra parametros enviados
                .contentType("application/json")
                .body("{\"name\":\"Jiraya da Silva\",\"age\":30,\"salary\":1234.5678}")
        .when()
                .post("https://restapi.wcaquino.me/users")
        .then()
            .log().all()
            .statusCode(201)
            .body("id", Matchers.is(Matchers.notNullValue()));
    }
}
