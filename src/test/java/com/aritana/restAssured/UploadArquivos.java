package com.aritana.restAssured;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
public class UploadArquivos {

    @Test
    public void deveObrigarEnvioArquivo() {

        given()
                .log().all() //mostra parametros enviados
                .contentType("application/json")
                .when()
                .post("https://restapi.wcaquino.me/upload")
                .then()
                .log().all()
                .statusCode(404)
                .body("error",Matchers.is("Arquivo não enviado"))
            ;
    }

    @Test
    public void deveFazerUploadDoArquivo() {

        given()
                .log().all() //mostra parametros enviados
                .multiPart("arquivo",new File("src/main/resources/file.pdf"))
                .when()
                .post("https://restapi.wcaquino.me/upload")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", Matchers.is("file.pdf"));
    }
}
