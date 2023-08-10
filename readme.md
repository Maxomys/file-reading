## File reading challenge

Using Spring Boot, [Fastdoubleparser](https://github.com/wrandelshofer/FastDoubleParser) for faster parsing and Splitter from the Guava library for more efficient string splitting.

### Endpoint
> GET /api/temperature/{cityName}

Example response for a given city: 
```json
[
  {
	"year": "2021",
	"averageTemperature": 13.1
  }
]
```

