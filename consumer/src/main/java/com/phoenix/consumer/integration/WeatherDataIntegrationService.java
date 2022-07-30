package com.phoenix.consumer.integration;

import com.phoenix.consumer.integration.model.WeatherDataResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataIntegrationService {

  private final RestTemplateBuilder restTemplateBuilder;

  public WeatherDataIntegrationService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplateBuilder = restTemplateBuilder;
  }

  public WeatherDataResponse getWeatherDataByCityName(String cityName) {
    final String url =
        "http://api.weatherapi.com/v1/current.json?key=XXXXXXXXXX&q="
            + cityName
            + "+ &aqi=no";
    final HttpHeaders headers = new HttpHeaders();
    headers.add("content-type", "application/json");

    HttpEntity<?> request = new HttpEntity<>(headers);
    ResponseEntity<WeatherDataResponse> responseEntity;
    try {
      responseEntity =
          restTemplateBuilder
              .build()
              .exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<>() {});
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }

    return responseEntity.getBody();
  }
}
