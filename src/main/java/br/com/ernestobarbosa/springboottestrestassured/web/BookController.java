package br.com.ernestobarbosa.springboottestrestassured.web;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import br.com.ernestobarbosa.springboottestrestassured.model.Availability;
import br.com.ernestobarbosa.springboottestrestassured.model.ClientError;
import br.com.ernestobarbosa.springboottestrestassured.service.AvailabilityService;
import br.com.ernestobarbosa.springboottestrestassured.service.BookService;

//localhost:porta/books
@RestController
@RequestMapping("/books")
public class BookController implements BookControllerApi{

    @Autowired
    private BookService bookService;

    @Autowired
    private AvailabilityService availabilityService;

    // GET localhost:porta/books/
    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    public List<Book> listBooks(){
        return bookService.findAll();
    }

    //localhost:porta/books/2
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getOneBook(@PathVariable(value = "id") Long bookId){
        return bookService.getOne(bookId);
    }

    //localhost:porta/books/
    @PostMapping({"","/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Book newBook(@Valid @RequestBody Book book){
        return bookService.save(book);
    }

    @PutMapping({"","/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@Valid @RequestBody Book book){
        bookService.update(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable(value = "id") Long bookId){
        bookService.delete(bookId);
    }

    @PutMapping("/availability")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bookAvailability(@Valid @RequestBody Availability availability){
        availabilityService.updateAvailability(availability);
    }

    @GetMapping("/{id}/availability")
    @ResponseStatus(HttpStatus.OK)
    public Availability bookAvailability(@PathVariable(value = "id") Long bookId){
        return availabilityService.getAvailabilityById(bookId);
    }

    @PutMapping("/{id}/loan")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void loanBook(@PathVariable(value = "id") Long bookId){
        availabilityService.removeStock(bookId);
    }

    @PutMapping("/{id}/devolution")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void devolutionBook(@PathVariable(value = "id") Long bookId){
        availabilityService.addStock(bookId);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ClientError> handleConstraintError(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ClientError("Duplicated Resource"));
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundError(){}

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ClientError> handleExternalService(HttpClientErrorException e){
        return ResponseEntity.status(e.getStatusCode()).body(new ClientError(e.getMessage()));
    }
}
