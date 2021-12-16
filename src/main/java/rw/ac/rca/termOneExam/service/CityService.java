package rw.ac.rca.termOneExam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

@Service
public class CityService {

	@Autowired
	private ICityRepository cityRepository;
	
	public City getById(long id) {
		Optional<City> found = cityRepository.findById(id);
		if(found.isPresent()) {
			City city = found.get();
			city.setFahrenheit(convertCelsiusToFahrenheit(city.getWeather()));
			return city;
		}

		return null;
	}

	public List<City> getAll() {
		List<City> cities = cityRepository.findAll();

		for(City city:cities) {
			city.setFahrenheit(convertCelsiusToFahrenheit(city.getWeather()));
		}

		return cities;
	}

	private Double convertCelsiusToFahrenheit(double degrees){
		return ((degrees * 9)/5) + 32;
	}

	public boolean existsByName(String name) {
		
		return cityRepository.existsByName(name);
	}

	public City save(CreateCityDTO dto) {
		City city =  new City(dto.getName(), dto.getWeather());
		return cityRepository.save(city);
	}
	

}
