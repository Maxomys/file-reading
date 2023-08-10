package com.github.maxomys.filereading.model;

import lombok.Data;

@Data
public class AverageTemperatureDto {
  
  private String year;
  private Double averageTemperature;

  public AverageTemperatureDto(String year, Double averageTemperature) {
    this.year = year;
    this.averageTemperature = averageTemperature;
    this.averageTemperature = Math.floor(10 * averageTemperature) / 10;
  }

}
