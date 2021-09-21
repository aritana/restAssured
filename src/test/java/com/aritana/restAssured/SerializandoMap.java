package com.aritana.restAssured;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SerializandoMap {

    @Test
    public void salvarObjetoComMap() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "Usuario via map");
        params.put("age", 25);

        given()
                .log().all() //mostra parametros enviados
                .contentType("application/json")
                .body(params)
                .when()
                .post("https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void salvarUsuarioUsandoObjeto() {

        User user = User.builder()
                .name("Usuario via objeto")
                .age(25)
                .salary(20.0)
                .build();

        given()
                .log().all() //mostra parametros enviados
                .contentType("application/json")
                .body(user)
                .when()
                .post("https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void deveDeserializarObjetoAoSalvarUsuario() {

        User user = User.builder()
                .name("Usuario via objeto")
                .age(25)
                .salary(20.0)
                .build();

        User usuarioInserido = given()
                .log().all() //mostra parametros enviados
                .contentType("application/json")
                .body(user)
                .when()
                .post("https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .body()
                .as(User.class);

        System.out.println(usuarioInserido);
        Assert.assertThat(usuarioInserido.getAge(),Matchers.is(25));
    }
}
