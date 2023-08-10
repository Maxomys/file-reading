package com.github.maxomys.filereading.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maxomys.filereading.model.AverageTemperatureDto;
import com.github.maxomys.filereading.services.TemperatureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/temperature")
@RequiredArgsConstructor
public class TemperatureRestController {

  private final TemperatureService temperatureService;
  
  @GetMapping("/{cityName}")
  public List<AverageTemperatureDto> getAverageTemperatures(@PathVariable String cityName) {
    return temperatureService.getAverageTemperaturesForCity(cityName);
  }

}
