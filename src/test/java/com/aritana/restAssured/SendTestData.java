package com.aritana.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.User;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
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
                    .contentType(ContentType.JSON)
                ;

    }
}
