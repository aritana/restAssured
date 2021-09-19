package com.aritana.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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

        Response response = request(Method.GET, "https://restapi.wcaquino.me/users/1");
        Object id = response.path("id");

        //path
        Assert.assertEquals(new Integer(1),id);
        Assert.assertEquals("Deveria retornar 1 ",new Integer(1),id);
        Assert.assertEquals(new Integer(1),response.path("%s","id"));

        //jsonPath
        JsonPath jpath = new JsonPath(response.asString());
        Assert.assertEquals(1,jpath.getInt("id"));

        //from
        int jsonId =JsonPath.from(response.asString()).getInt("id");
        Assert.assertEquals(1,jsonId);
    }

    @Test
    public void deveVerificarSegundoNivel(){
        given()
                .when()
                    .get("https://restapi.wcaquino.me/users/2")
                .then()
                    .statusCode(200)
                    .body("endereco.rua", Matchers.is( "Rua dos bobos"));
    }

    @Test
    public void deveVerificarLista(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/users/3")
                .then()
                .statusCode(200)
                .body("filhos", hasSize( 2))
                .body("filhos[0].name", Matchers.is( "Zezinho"))
                .body("filhos[0].name", hasItem( "Zezinho"))
                .body("filhos[0].name", hasItems( "Zezinho","Luizinho"));
    }
}
