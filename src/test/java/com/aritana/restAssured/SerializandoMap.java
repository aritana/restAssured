package com.aritana.restAssured;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
        import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SerializandoMap {

    @Test
    public void salvarObjetoComMap() {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("name", "Usuario via map");
        params.put("age",25);

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
}
