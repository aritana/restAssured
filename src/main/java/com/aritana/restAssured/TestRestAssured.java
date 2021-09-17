package com.aritana.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import javax.swing.SpringLayout;

public class TestRestAssured {

  public static void main(String[] args) {

    Response request = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
    System.out.println("Opa: ");
    System.out.println(request.getBody().asString());
  }

}
