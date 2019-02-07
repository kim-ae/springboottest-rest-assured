package br.com.ernestobarbosa.springboottestrestassured.service;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import br.com.ernestobarbosa.springboottestrestassured.model.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AvailabilityService {

    @Value("${availability-url}")
    private String AVAILABILITY_URL;

    @Autowired
    private RestTemplate restTemplate;

    public Availability getAvailabilityById(Long bookId) {
        return restTemplate.getForEntity(AVAILABILITY_URL + bookId, Availability.class).getBody();
    }

    public void setAvailability(Book book){
        Availability availability = new Availability(book.getBookId(), 0);
        restTemplate.postForLocation(AVAILABILITY_URL, availability);
    }

    public void deleteAvailability(Long bookId){
        restTemplate.delete(AVAILABILITY_URL + bookId);
    }

    public void updateAvailability(Availability availability){
        restTemplate.put(AVAILABILITY_URL, availability);
    }

    public void addStock(Long bookId){
        restTemplate.put(AVAILABILITY_URL + bookId + "/devolution",null);
    }

    public void removeStock(Long bookId){
        restTemplate.put(AVAILABILITY_URL + bookId + "/loan", null);
    }
}
