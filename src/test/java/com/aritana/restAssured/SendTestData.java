package com.aritana.restAssured;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
public class SendTestData {

    @Test
    public void deveriaEnviarValorViaQuery(){
        given()
                  .log().all()
                .when()
                    .get("https://restapi.wcaquino.me/v2/users?format=json")
                .then()
                    .log().all()
                    .contentType(ContentType.JSON);
    }
    @Test
    public void deveriaEnviarQueryViaParam(){
        given()
                .log().all()
                .queryParam("format","json")
                .when()
                    .get("https://restapi.wcaquino.me/v2/users")
                .then()
                    .log().all()
                    .contentType(ContentType.JSON);
    }
    @Test
    public void deveriaEnviarValorViaHeader(){
        given()
                .log().all()
                .accept(ContentType.HTML)//Oque eu quero aceitar na resposta
                .when()
                    .get("https://restapi.wcaquino.me/v2/users")
                .then()
                    .log().all()
                    .contentType(ContentType.HTML);
    }
}
