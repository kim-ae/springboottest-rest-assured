package br.com.ernestobarbosa.springboottestrestassured.repository;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}