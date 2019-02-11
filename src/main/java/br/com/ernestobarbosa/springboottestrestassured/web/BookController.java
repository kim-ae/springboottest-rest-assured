package br.com.ernestobarbosa.springboottestrestassured.web;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import br.com.ernestobarbosa.springboottestrestassured.model.Availability;
import br.com.ernestobarbosa.springboottestrestassured.model.ClientError;
import br.com.ernestobarbosa.springboottestrestassured.service.AvailabilityService;
import br.com.ernestobarbosa.springboottestrestassured.service.BookService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

//localhost:porta/books
@RestController
@RequestMapping("/books")
public class BookController implements BookControllerApi{

    @Autowired
    private BookService bookService;

    @Autowired
    private AvailabilityService availabilityService;

    // GET localhost:porta/books/
    @GetMapping("/")
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
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void newBook(@Valid @RequestBody Book book){
        bookService.save(book);
    }

    @PutMapping("/")
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
    @ResponseStatus(HttpStatus.CONFLICT)
    public ClientError handleConstraintError(){
        return new ClientError("Duplicated Resource");
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundError(){}
}
