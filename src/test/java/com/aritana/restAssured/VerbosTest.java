package com.aritana.restAssured;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class VerbosTest {

    @Test
    public void deveSalvarUsuario() {

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

    @Test
    public void naoDeveSalvarUsuarioSemNome() {
        given()
                .log().all() //mostra parametros enviados
                .contentType("application/json")
                .body("{\"age\":30,\"salary\":1234.5678}")
                .when()
                .post("https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(400)
                .body("id", Matchers.is(Matchers.nullValue()))
                .body("error", Matchers.is("Name é um atributo obrigatório"));

    }

    @Test
    public void deveAlterarUsuario() {

        given()
                .log().all() //mostra parametros enviados
                .contentType("application/json")
                .body("{\"name\":\"Jiraya da Saudade\",\"age\":200,\"salary\":1234.5678}")
                .when()
                .put("https://restapi.wcaquino.me/{entidade}/{userId}","users","1")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", Matchers.is(1));
    }
    @Test
    public void deveCustomizarUrl() {

        given()
                .log().all() //mostra parametros enviados
                .contentType("application/json")
                .body("{\"name\":\"Jiraya da Saudade\",\"age\":200,\"salary\":1234.5678}")
                .pathParam("entidade","users")
                .pathParam("userId",1)
            .when()
                .put("https://restapi.wcaquino.me/{entidade}/{userId}","users","1")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", Matchers.is(1));
    }

    @Test
    public void deveDeletarUsuario() {

        given()
                .log().all() //mostra parametros enviados
                .pathParam("entidade","users")
                .pathParam("userId",1)
                .when()
                .delete("https://restapi.wcaquino.me/{entidade}/{userId}","users","1000")
                .then()
                .log().all()
                .statusCode(204);

    }
}
