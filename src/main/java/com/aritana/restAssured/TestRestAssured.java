package com.aritana.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import javax.swing.SpringLayout;

public class TestRestAssured {



  public static void main(String[] args) {

    Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
    System.out.println("----------------------------------------------------------------------------");
    System.out.println(response.getBody().asString());
    System.out.println(response.statusCode());

    //testando
    System.out.println(response.getBody().asString().equals("Ola Mundo!"));
    System.out.println(response.statusCode()==200);

    ValidatableResponse validation = response.then();
    validation.statusCode(200);

  }

}
