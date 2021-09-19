package com.aritana.restAssured;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;

public class UserJsonTest {

    @Test
    public void deveVerificarPrimeiroNivel(){

        given()
                .when()
                    .get("https://restapi.wcaquino.me/users/1")
                .then()
                    .statusCode(200)
                    .body("id", Matchers.is(1))
                    .body("name",containsString("Silva"))
                    .body("age",greaterThan(18));
    }

    @Test
    public void deveVerificarPrimeiroNivelOutraForma(){

    }
}
