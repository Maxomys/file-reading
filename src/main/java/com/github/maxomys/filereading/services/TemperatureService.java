package com.github.maxomys.filereading.services;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.github.maxomys.filereading.model.AverageTemperature;
import com.github.maxomys.filereading.model.AverageTemperatureDto;
import com.google.common.base.Splitter;

import ch.randelshofer.fastdoubleparser.JavaDoubleParser;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TemperatureService {
  
  public List<AverageTemperatureDto> getAverageTemperaturesForCity(String cityName) {
    
    List<AverageTemperature> averageTemperatures = new ArrayList<>();
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    try (
      Stream<String> fileStream = Files.lines(new ClassPathResource("data.csv").getFile().toPath())
    ) {
      Iterator<String> iterator = fileStream.iterator();

      Splitter splitter = Splitter.on(";");

      while(iterator.hasNext()) {
        String line = iterator.next();
        Iterator<String> split = splitter.split(line).iterator();

        String city = split.next();
        int year = LocalDateTime.parse(split.next(), formatter).getYear();
        
        double temperature = JavaDoubleParser.parseDouble(split.next());

        if (!city.equals(cityName)) {
          continue;
        }

        // check if the list contains an object with current year and city, if not - create a new one
        AverageTemperature foundAverageTemperature = averageTemperatures.stream()
          .filter(t -> t.getYear() == year)
          .findFirst()
          .orElseGet(() -> {
            AverageTemperature newAverageTemperature = new AverageTemperature(cityName, year);
            averageTemperatures.add(newAverageTemperature);
            return newAverageTemperature;
          });

        foundAverageTemperature.update(temperature);
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }

    return averageTemperatures.stream()
      .map(t -> {
        return new AverageTemperatureDto(String.valueOf(t.getYear()), t.getAverageTemperature());
      })
      .collect(Collectors.toList());
  }

}
