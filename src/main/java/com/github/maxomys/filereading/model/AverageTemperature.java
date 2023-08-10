package com.github.maxomys.filereading.model;

import lombok.Getter;

@Getter
public class AverageTemperature {
  
  private String cityName;
  private int year;
  private double averageTemperature;
  private long count;

  public AverageTemperature(String cityName, int year) {
    this.cityName = cityName;
    this.year = year;
    this.averageTemperature = 0;
    this.count = 1;
  }

  public void update(double temperature) {
    this.averageTemperature = this.averageTemperature + (temperature - this.averageTemperature) / count;
    this.count++;
  }

}
