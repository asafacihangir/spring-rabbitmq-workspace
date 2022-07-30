package com.phoenix.consumer.model;


public class WeatherDataRequest {

  private String cityName;

  private String date;

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "WeatherDataRequest{" +
        "cityName='" + cityName + '\'' +
        ", date='" + date + '\'' +
        '}';
  }
}
