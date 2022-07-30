package com.phoenix.producer.web;

import com.phoenix.producer.service.WeatherDataRequestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather-data")
public class WeatherDataTaskController {

  private final WeatherDataRequestService weatherDataRequestService;

  public WeatherDataTaskController(WeatherDataRequestService weatherDataRequestService) {
    this.weatherDataRequestService = weatherDataRequestService;
  }

  @PostMapping("/create-task")
  public String sendSms() {
    weatherDataRequestService.createTaskForWeatherData();
    return "Tasks for Weather Data sent to the RabbitMQ Successfully";
  }
}
