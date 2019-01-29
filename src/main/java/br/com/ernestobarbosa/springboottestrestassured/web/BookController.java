package br.com.ernestobarbosa.springboottestrestassured.web;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import br.com.ernestobarbosa.springboottestrestassured.model.Availability;
import br.com.ernestobarbosa.springboottestrestassured.repository.BookRepository;
import br.com.ernestobarbosa.springboottestrestassured.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository repository;

    @Autowired
    AvailabilityService availabilityService;

    @GetMapping("/")
    public List<Book> listBooks(){
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public Book getOneBook(@PathVariable(value = "id") Long bookId){
        return repository.getOne(bookId);
    }

    @PutMapping("/")
    public Book newBook(@Valid @RequestBody Book book){
        return repository.save(book);
    }

    @PostMapping("/{id}")
    public Book updateBook(@PathVariable(value = "id") Long bookId, @Valid @RequestBody Book book){
        Book b = repository.findById(bookId).get();
        b.setName(book.getName());
        b.setPrice(book.getPrice());
        return repository.save(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable(value = "id") Long bookId){
        repository.deleteById(bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/availability/{id}")
    public Availability bookAvailability(@PathVariable(value = "id") Long bookId){
        return availabilityService.getAvailabilityById(bookId);
    }

}
