package com.phoenix.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SmsSenderApplication {

  public static void main(String[] args) {
    SpringApplication.run(SmsSenderApplication.class, args);
  }

}
