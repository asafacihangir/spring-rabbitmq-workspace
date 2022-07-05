package com.phoenix.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean("projectObjectMapper")
  public ObjectMapper objectMapper() {
    return JsonMapper.builder().findAndAddModules().build();
  }


  @Bean
  public Faker faker(){
    return new Faker();
  }

}
