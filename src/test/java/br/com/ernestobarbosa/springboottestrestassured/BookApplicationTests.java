package br.com.ernestobarbosa.springboottestrestassured;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import br.com.ernestobarbosa.springboottestrestassured.model.Availability;
import br.com.ernestobarbosa.springboottestrestassured.service.AvailabilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AvailabilityService service;

    @MockBean
    private AvailabilityService mockService;

    @MockBean
    private RestTemplate template;

    @Value("${availability-id}")
    private Long bookId;

    @Test
    public void unitTestMockedTest() throws Exception {
        //controller
        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().json("[]"));
        verify(service, times(1));
    }

    //vendo
    @Test
    public void unitMockedTest() throws Exception {
        this.mvc.perform(put("/").content("{\"id\": 1, \"name\": \"Ze\", \"price\": 10.0}")).andExpect(status().isOk());

        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().json("[{\"id\": 1, \"name\": \"Ze\", \"price\": 10.0}]"));

        verify(service, times(1));
    }


    @Test
    public void serviceAvailabilityFalseTestMockedTest() {
        //mock
        Availability mockAvailability = new Availability("Teste", false);
        when(template.getForEntity(any(String.class), any(Class.class))).thenReturn(new ResponseEntity(mockAvailability, HttpStatus.OK));

        //service
        assertFalse(service.getAvailabilityById(bookId).isAvailable());
    }

    @Test
    public void serviceAvailabilityMockedTest() {
        //mock service
        when(mockService.getAvailabilityById(any(Long.class))).thenReturn(new Availability("Teste", false));

        assertFalse(mockService.getAvailabilityById(bookId).isAvailable());
    }

}
