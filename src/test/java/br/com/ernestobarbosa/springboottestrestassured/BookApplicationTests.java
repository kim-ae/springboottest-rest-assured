package br.com.ernestobarbosa.springboottestrestassured;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import br.com.ernestobarbosa.springboottestrestassured.service.AvailabilityService;
import br.com.ernestobarbosa.springboottestrestassured.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
    webEnvironment = MOCK
)
@AutoConfigureMockMvc
public class BookApplicationTests {

    private MockMvc mockMvc;

    @MockBean
    private BookService mockBookService;

    @MockBean
    private AvailabilityService mockAvailabilityService;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(context)
            .build();
    }

    @Test
    public void getBooksMockedTest() throws Exception {
        mockMvc.perform(get("/books/")).andExpect(status().isOk());
        verify(mockBookService, times(1)).findAll();
    }

    @Test
    public void getAvailabilityMockedTest() throws Exception {
        final Book book = Book.builder().bookId(1l).build();
        mockMvc.perform(get("/books/" + book.getBookId() + "/availability")).andExpect(status().isOk());
        verify(mockAvailabilityService, times(1)).getAvailabilityById(book.getBookId());
    }

    @Test
    public void getAvailabilityTest() throws Exception {
        final Book book = Book.builder().bookId(1l).build();
        mockMvc.perform(get("/books/" + book.getBookId() + "/availability")).andExpect(
            status().isOk());
        verify(mockAvailabilityService, times(1)).getAvailabilityById(book.getBookId());
    }

}
