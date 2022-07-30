package com.phoenix.consumer.service;

import com.phoenix.consumer.WeatherDataWorkerQueueListener;
import com.phoenix.consumer.domain.WeatherData;
import com.phoenix.consumer.integration.WeatherDataIntegrationService;
import com.phoenix.consumer.integration.model.WeatherDataResponse;
import com.phoenix.consumer.repository.WeatherDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataService {

  private static final Logger LOG = LoggerFactory.getLogger(WeatherDataWorkerQueueListener.class);

  private final WeatherDataRepository weatherDataRepository;

  private final WeatherDataIntegrationService weatherDataIntegrationService;

  public WeatherDataService(WeatherDataRepository weatherDataRepository,
      WeatherDataIntegrationService weatherDataIntegrationService) {
    this.weatherDataRepository = weatherDataRepository;
    this.weatherDataIntegrationService = weatherDataIntegrationService;
  }

  public void getWeatherData(String cityName) throws InterruptedException {

    LOG.info("getWeatherData started with param : {}", cityName);
    final WeatherDataResponse weatherDataByCityName = weatherDataIntegrationService.getWeatherDataByCityName(
        cityName);

    final WeatherData weatherData = new WeatherData();
    weatherData.setCityName(cityName);
    weatherData.setDate(weatherDataByCityName.getLocation().getLocaltime());
    weatherData.setCloud(String.valueOf(weatherDataByCityName.getCurrent().getCloud()));
    weatherData.setConditionWeather(weatherDataByCityName.getCurrent().condition.text);
    weatherData.setTempC(String.valueOf(weatherDataByCityName.getCurrent().temp_c));
    weatherData.setFeelslikeC(String.valueOf(weatherDataByCityName.getCurrent().feelslike_c));
    weatherData.setHumidity(String.valueOf(weatherDataByCityName.getCurrent().getHumidity()));

    weatherDataRepository.save(weatherData);

    LOG.info("getWeatherData finished with param : {}", cityName);

    Thread.sleep(5000);

  }


}
