package rw.ac.rca.termOneExam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    private ICityRepository cityRepositoryMock;

    @InjectMocks
    private CityService cityService;

    @Test
    public void getAll_success() {

        when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City(104, "Kigali", 24, 70),
                new City(2, "Bujumbura", 26, 10)));
        assertEquals(75.2, cityService.getAll().get(0).getFahrenheit());
    }

    @Test
    public void getById_success() {
        Long id = 104L;
        City city = new City(id, "Kigali", 24, 5);
        when(cityRepositoryMock.findById(id)).thenReturn(Optional.of(city));
        assertEquals(75.2, cityService.getById(id).getFahrenheit());
    }

    @Test
    public void getById_fail() {
        Long id = 1000L;
        when(cityRepositoryMock.findById(id)).thenReturn(Optional.empty());
        City city = cityService.getById(id);
        assertTrue(city == null);
    }

    @Test
    public void save_success() {
        CreateCityDTO dto = new CreateCityDTO();
        dto.setName("Kigali");
        dto.setWeather(24);
        City city = new City(dto.getName(), dto.getWeather());
        when(cityRepositoryMock.save(city)).thenReturn(city);
        City createCity = cityService.save(dto);
        assertTrue(createCity.getName() == "Kigali");
    }

}
