package com.phoenix.consumer.bootstrap;

import com.phoenix.consumer.service.WeatherDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class DataBootstrap implements CommandLineRunner {





  @Override
  public void run(String... strings) throws Exception {

  }
}
