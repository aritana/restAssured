package com.aritana.restAssured;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class UploadDownloadArquivos {

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
                .body("error", Matchers.is("Arquivo não enviado"));
    }

    @Test
    public void deveFazerUploadDoArquivo() {

        given()
                .log().all() //mostra parametros enviados
                .multiPart("arquivo", new File("src/main/resources/file.pdf"))
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
                .multiPart("arquivo", new File("src/main/resources/file2"))
                .when()
                .post("https://restapi.wcaquino.me/upload")
                .then()
                .log().all()
                .time(Matchers.lessThan(8000l))
                .statusCode(413);
    }

    @Test
    public void deveBaixarArquivo() throws IOException {

        byte[] imagem = given()
                .log().all() //mostra parametros enviados
                .when()
                .get("https://restapi.wcaquino.me/download")
                .then()
                .statusCode(200)
                .extract().asByteArray();

        File imagem2 = new File("src/main/resources/img.jpg");
        OutputStream out =  new FileOutputStream(imagem2);
        out.write(imagem);
        out.close();
    }
}
