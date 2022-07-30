package com.phoenix.consumer;

import com.phoenix.consumer.model.WeatherDataRequest;
import com.phoenix.consumer.service.WeatherDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataWorkerQueueListener {
  private static final Logger LOG = LoggerFactory.getLogger(WeatherDataWorkerQueueListener.class);

  private final WeatherDataService weatherDataService;

  public WeatherDataWorkerQueueListener(WeatherDataService weatherDataService) {
    this.weatherDataService = weatherDataService;
  }

  @RabbitListener(queues = "weather_data_work.queue", concurrency = "5")
  public void receive1(WeatherDataRequest weatherDataRequest) throws InterruptedException {
    LOG.info("Received : " + weatherDataRequest);
    weatherDataService.getWeatherData(weatherDataRequest.getCityName());
  }
}
