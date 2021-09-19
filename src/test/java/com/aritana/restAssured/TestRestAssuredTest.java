package com.aritana.restAssured;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class TestRestAssuredTest {

    @Test
    public void testeOlaMundo(){

        Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println(response.getBody().asString());
        System.out.println(response.statusCode());
        //testando
        assertTrue("A mensagem deveria ser \"Ola Mundo!\" ",response.getBody().asString().equals("Ola Mundo!"));
        assertTrue("0 status code deveria ser \"200\" ", response.statusCode()==200);
        assertEquals(200, response.statusCode());
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
    @Test public void devoConhecerMatcherHamcrest(){

        Integer numero = 128;
        List<Integer> impares = Arrays.asList(1,3,5,7);
       // Assert.assertThat("Maria", Matchers.is("Maria"));
        assertThat(numero, Matchers.instanceOf(Integer.class));
        assertThat(128,Matchers.lessThan(140));
        assertThat(impares,Matchers.hasSize(4));
        assertThat(impares,Matchers.contains(1,3,5,7));
        assertThat(impares,Matchers.containsInAnyOrder(1,5,3,7));
        assertThat(impares,Matchers.hasItem(1));
        assertThat(impares,Matchers.hasItems(1,3));
        assertThat("Maria",Matchers.not("João"));
        assertThat("Maria",Matchers.allOf(Matchers.startsWith("M"),Matchers.endsWith("ia"), containsString("ar")));
      //  assertThat("Maria", Matchers.anyOf(Matchers.startsWith("M"),Matchers.endsWith("ia"), containsString("ar")));
    }

    @Test
    public void devoValidarBody(){
        given()
                .when()
                    .get("https://restapi.wcaquino.me/ola")
                .then()
                    .assertThat()//optional
                    .statusCode(200)
                    .body(Matchers.is("Ola Mundo!"))
                    .body(containsString("Mundo"))
                    .body(is(not(nullValue())));//corpo nao vazio

    }

}