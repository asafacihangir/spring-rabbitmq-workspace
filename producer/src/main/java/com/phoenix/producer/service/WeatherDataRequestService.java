package com.phoenix.producer.service;

import com.phoenix.producer.model.WeatherDataRequest;
import com.phoenix.producer.utills.StringUtils;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataRequestService {

  private final AmqpTemplate amqpTemplate;

  private static final String EXCHANGE = "weather_data_work_exhange";
  private static final String ROUTING_KEY = "weather_data_work_routing_key";

  private static final Logger LOG = LoggerFactory.getLogger(WeatherDataRequestService.class);

  public WeatherDataRequestService(@Qualifier("projectRabbitTemplate") AmqpTemplate amqpTemplate) {
    this.amqpTemplate = amqpTemplate;
  }

  public void createTaskForWeatherData() {

    final String currentDate = LocalDate.now().toString();
    getCityNames()
        .forEach(
            cityName -> {
              final WeatherDataRequest request = new WeatherDataRequest();
              request.setCityName(StringUtils.clearTurkishChars(cityName));
              request.setDate(currentDate);

              amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, request);

              LOG.info("Task Sent to Queue {}", request);
            });
  }

  private List<String> getCityNames() {
    return List.of(
        "Adana",
        "Adıyaman",
        "Afyonkarahisar",
        "Ağrı",
        "Aksaray",
        "Amasya",
        "Ankara",
        "Antalya",
        "Ardahan",
        "Artvin",
        "Aydın",
        "Balıkesir",
        "Bartın",
        "Batman",
        "Bayburt",
        "Bilecik",
        "Bingöl",
        "Bitlis",
        "Bolu",
        "Burdur",
        "Bursa",
        "Çanakkale",
        "Çankırı",
        "Çorum",
        "Denizli",
        "Diyarbakır",
        "Düzce",
        "Edirne",
        "Elazığ",
        "Erzincan",
        "Erzurum",
        "Eskişehir",
        "Gaziantep",
        "Giresun",
        "Gümüşhane",
        "Hakkari",
        "Hatay",
        "Iğdır",
        "Isparta",
        "İstanbul",
        "İzmir",
        "Kahramanmaraş",
        "Karabük",
        "Karaman",
        "Kars",
        "Kastamonu",
        "Kayseri",
        "Kırıkkale",
        "Kırklareli",
        "Kırşehir",
        "Kilis",
        "Kocaeli",
        "Konya",
        "Kütahya",
        "Malatya",
        "Manisa",
        "Mardin",
        "Mersin",
        "Muğla",
        "Muş",
        "Nevşehir",
        "Niğde",
        "Ordu",
        "Osmaniye",
        "Rize",
        "Sakarya",
        "Samsun",
        "Siirt",
        "Sinop",
        "Sivas",
        "Şanlıurfa",
        "Şırnak",
        "Tekirdağ",
        "Tokat",
        "Trabzon",
        "Tunceli",
        "Uşak",
        "Van",
        "Yalova",
        "Yozgat",
        "Zonguldak");
  }
}
