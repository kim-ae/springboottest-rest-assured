package br.com.ernestobarbosa.springboottestrestassured.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import br.com.ernestobarbosa.springboottestrestassured.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private BookRepository repository;

    public List<Book> findAll(){
        return repository.findAll();
    }

    public Book getOne(Long bookId){
        return repository.findOne(bookId);
    }

    @Transactional
    public Book save(Book book){
        log.info("Trying to create book: {}", book);
        final Book newBook = repository.save(book);
        availabilityService.setAvailability(book);
        return newBook;

    }

    public void update(Book book){
        log.info("Trying to update book: {}", book);
        Book b = repository.findOne(book.getBookId());
        b.setName(book.getName());
        b.setPrice(book.getPrice());
        repository.save(b);
    }

    @Transactional
    public void delete(Long id){
        log.info("Trying to delete book: {}", id);
        repository.delete(id);
        availabilityService.deleteAvailability(id);
    }
}
