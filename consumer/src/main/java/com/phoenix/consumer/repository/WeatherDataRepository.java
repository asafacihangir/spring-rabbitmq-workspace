package com.phoenix.consumer.repository;

import com.phoenix.consumer.domain.WeatherData;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WeatherDataRepository extends PagingAndSortingRepository<WeatherData, Long> {

}
