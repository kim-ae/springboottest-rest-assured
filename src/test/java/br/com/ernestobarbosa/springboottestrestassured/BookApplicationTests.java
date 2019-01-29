package br.com.ernestobarbosa.springboottestrestassured;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private RestTemplate template;

    @Value("${availability-id}")
    private Long bookId;

    @Test
    public void unitTestMockedTest() throws Exception {
        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void componentAvailabilityFalseTestMockedTest() {
        Availability mockAvailability = new Availability("Teste", false);
        when(template.getForEntity(any(String.class), any(Class.class))).thenReturn(new ResponseEntity(mockAvailability, HttpStatus.OK));

        Availability av = service.getAvailabilityById(bookId);

        assertFalse(av.isAvailable());
    }
}
