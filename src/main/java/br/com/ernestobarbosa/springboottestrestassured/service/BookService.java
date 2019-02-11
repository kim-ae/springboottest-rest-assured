package br.com.ernestobarbosa.springboottestrestassured.service;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import br.com.ernestobarbosa.springboottestrestassured.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private BookRepository repository;

    public List<Book> findAll(){
        return repository.findAll();
    }

    public Book getOne(Long bookId){
        return repository.findById(bookId).get();
    }

    @Transactional
    public void save(Book book){
        repository.save(book);
        availabilityService.setAvailability(book);
    }

    public void update(Book book){
        Book b = repository.findById(book.getBookId()).get();
        b.setName(book.getName());
        b.setPrice(book.getPrice());
        repository.save(b);
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
        availabilityService.deleteAvailability(id);
    }
}
