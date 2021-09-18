package com.aritana.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class TestRestAssuredTest {

    @Test
    public void testeOlaMundo(){

        Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println(response.getBody().asString());
        System.out.println(response.statusCode());
        //testando
        Assert.assertTrue("A mensagem deveria ser \"Ola Mundo!\" ",response.getBody().asString().equals("Ola Mundo!"));
        Assert.assertTrue("0 status code deveria ser \"200\" ", response.statusCode()==200);
        Assert.assertEquals(200, response.statusCode());
        ValidatableResponse validation = response.then();
        validation.statusCode(200);
    }

    @Test
    public void devoCOnhecerOutrasFormasRestAssured(){

        get("https://restapi.wcaquino.me/ola").then().statusCode(200);

        ///modo fluente Given When Then
        given() //pre condicoes
                .when() //acao de fato
                    .get("https://restapi.wcaquino.me/ola")
                .then() //verificacoes
                    .statusCode(200);
    }

}