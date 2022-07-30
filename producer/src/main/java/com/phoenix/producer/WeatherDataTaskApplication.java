package com.phoenix.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WeatherDataTaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(WeatherDataTaskApplication.class, args);
  }

}
