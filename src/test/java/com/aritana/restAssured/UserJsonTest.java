package com.aritana.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.util.Arrays;
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
                    .body("filhos.name", hasItem( "Zezinho"))
                    .body("filhos.name", hasItems( "Zezinho","Luizinho"));
    }

    @Test
    public void deveRetornarErroUsuarioInexistente(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/users/4")
                .then()
                .statusCode(404)
                .body("error", is( "Usuário inexistente"));
    }
    @Test
    public void deveVerificarListaNaRaiz(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/users")
                .then()
                .statusCode(200)
                .body("$", hasSize(3));
//ou
        String names[] = new String[]{"Zezinho","Luizinho"};
        given()
                .when()
                .get("https://restapi.wcaquino.me/users")
                .then()
                .statusCode(200)
                .body("filhos.name", hasItem( Arrays.asList(names)));
    }
    @Test
    public void deveFazerVerificacoesAvancadas() {

        given()
                .when()
                    .get("https://restapi.wcaquino.me/users")
                .then()
                    .statusCode(200)
                    .body("$", hasSize(3))
                    .body("age.findAll{it <= 25}.size()",is(2))//tra todos, iterage em todos.
                    .body("age.findAll{it <= 25 && it > 20}.size()",is(1))
                    .body("findAll{it.age <= 25 && it.age > 20}.name",hasItem("Maria Joaquina"))
                    .body("findAll{it.age <= 25}[0].name",is("Maria Joaquina"))
                    .body("find{it.age <= 25}.name",is("Maria Joaquina"))
                    .body("findAll{it.name.contains('n')}.name",hasItems("Maria Joaquina","Ana Júlia"))
                    .body("findAll{it.name.length()>10}.name",hasItems("Maria Joaquina"))
                    .body("name.collect{it.toUpperCase()}",hasItems("MARIA JOAQUINA"))//Opera o upperCase
                    .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}",hasItems("MARIA JOAQUINA"))//Opera o upperCase
                    .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}.toArray()",allOf(arrayContaining("MARIA JOAQUINA"),arrayWithSize(1)))//Opera o upperCase

        ;


    }
}
