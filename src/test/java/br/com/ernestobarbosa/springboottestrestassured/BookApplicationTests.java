package br.com.ernestobarbosa.springboottestrestassured;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import br.com.ernestobarbosa.springboottestrestassured.model.Availability;
import br.com.ernestobarbosa.springboottestrestassured.service.AvailabilityService;
import br.com.ernestobarbosa.springboottestrestassured.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    classes = BookSpringBootApplication.class
)
@AutoConfigureMockMvc
public class BookApplicationTests {

    @Value("${server-url}")
    private String host;

    @LocalServerPort
    private int port;

    @Autowired
    private BookService bookService;

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private RestTemplate rest;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService mockBookService;

    @MockBean
    private AvailabilityService mockAvailabilityService;

    @Mock
    Book book;

    @Test
    public void getBooksMockedTest() throws Exception {
        mvc.perform(get(host + ":" + port + "/books/")).andExpect(status().isOk());
        verify(mockBookService, times(1)).findAll();
    }

    @Test
    public void getBooksTest() throws Exception {
        assertTrue(rest.getForEntity(host + ":" + port + "/books/", List.class).getStatusCode() == HttpStatus.OK);
        verify(bookService, times(1)).findAll();
    }

    @Test
    public void getAvailabilityMockedTest() throws Exception {
        mvc.perform(get(host + ":" + port + "/books/" + book.getBookId() + "/availability")).andExpect(status().isOk());
        verify(mockAvailabilityService, times(1)).getAvailabilityById(book.getBookId());
    }

    @Test
    public void getAvailabilityTest() throws Exception {
        assertTrue(rest.getForEntity(host + ":" + port + "/books/" + book.getBookId() + "/availability", List.class).getStatusCode() == HttpStatus.OK);
        verify(availabilityService, times(1)).getAvailabilityById(book.getBookId());
    }

}
