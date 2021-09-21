package com.aritana.restAssured;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
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
                .body("error",Matchers.is("Arquivo não enviado"));
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

    @Test
    public void NãodeveFazerUploadDeArquivoGrande() {

        given()
                .log().all() //mostra parametros enviados
                .multiPart("arquivo",new File("src/main/resources/file2"))
                .when()
                .post("https://restapi.wcaquino.me/upload")
                .then()
                .log().all()
                .time(Matchers.lessThan(8000l))
                .statusCode(413);
    }
}
