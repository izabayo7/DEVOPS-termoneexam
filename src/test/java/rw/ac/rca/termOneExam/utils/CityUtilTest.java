package rw.ac.rca.termOneExam.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CityUtilTest {

    @Autowired
    private ICityRepository cityRepository;

    @Test
    public void noCityHasWeatherMoreThan40() {
        boolean result = false;
        List<City> cities = cityRepository.findAll();
        for (City city: cities)
            if(city.getWeather() > 40)
                result = true;


        assertEquals(false, result);
    }

    @Test
    public void noCityHasWeatherLessThan10() {
        boolean result = false;
        List<City> cities = cityRepository.findAll();
        for (City city: cities)
            if(city.getWeather() < 10)
                result = true;


        assertEquals(false, result);
    }

    @Test
    public void CitiesContainMusanzeAndKigali() {
        boolean found = false;
        List<City> cities = cityRepository.findAll();
        assertEquals(true, cityRepository.existsByName("Musanze") && cityRepository.existsByName("Kigali"));
    }

}
