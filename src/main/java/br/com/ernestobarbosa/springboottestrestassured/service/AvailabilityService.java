package br.com.ernestobarbosa.springboottestrestassured.service;

import br.com.ernestobarbosa.springboottestrestassured.model.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AvailabilityService {

    @Value("${availability-url}")
    private String AVAILABILITY_URL;

//    @Autowired
    RestTemplate restTemplate;

    public Availability getAvailabilityById(Long id) {
        restTemplate = new RestTemplate();
        return restTemplate.getForEntity(AVAILABILITY_URL + id, Availability.class).getBody();
    }
}
