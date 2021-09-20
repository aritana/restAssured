package com.aritana.restAssured;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Builder
public class User {

  private String name;
  private Integer age;
  private Double salary;

}
