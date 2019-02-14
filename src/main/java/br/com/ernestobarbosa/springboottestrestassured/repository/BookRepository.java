package br.com.ernestobarbosa.springboottestrestassured.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}