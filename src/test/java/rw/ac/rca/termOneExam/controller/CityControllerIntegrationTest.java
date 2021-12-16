package rw.ac.rca.termOneExam.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.utils.APIResponse;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String apiPath = "/api/cities";

    @Test
    public void getAll_success() throws JSONException {
        String response = this.restTemplate.getForObject(apiPath+"/all",String.class);

        JSONAssert.assertEquals("[{id:101},{id:102},{id:103},{id:104}]",response,false);

    }

    @Test
    public void getById_success() throws JSONException {
        ResponseEntity<City> city = this.restTemplate.getForEntity(apiPath+"/id/101",City.class);

        assertTrue(city.getStatusCode().is2xxSuccessful());
        assertEquals("Kigali",city.getBody().getName());
        assertEquals(24,city.getBody().getWeather());

    }

    @Test
    public void getById_404() throws JSONException {
        ResponseEntity<APIResponse> response = this.restTemplate.getForEntity(apiPath+"/id/600",APIResponse.class);

        assertTrue(response.getStatusCodeValue()==404);
        assertFalse(response.getBody().isStatus());
        assertEquals("City not found with id 600",response.getBody().getMessage());

    }

    @Test
    public void postCity_success() throws JSONException {
        City body = new City(105,"Cairo",70,12);
        ResponseEntity<City> item = this.restTemplate.postForEntity(apiPath+"/add",body, City.class);

        assertTrue(item.getStatusCode().is2xxSuccessful());
        assertEquals("Cairo",item.getBody().getName());

    }

    @Test
    public void postCity_404() throws JSONException {
        City body = new City(101,"Kigali",70,12);
        ResponseEntity<APIResponse> response = this.restTemplate.postForEntity(apiPath+"/add",body,APIResponse.class);

        assertTrue(response.getStatusCodeValue()==400);
        assertFalse(response.getBody().isStatus());
        assertEquals("City name Kigali is registered already",response.getBody().getMessage());

    }

}
