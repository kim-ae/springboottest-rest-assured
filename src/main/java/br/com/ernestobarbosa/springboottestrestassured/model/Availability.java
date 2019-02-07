package br.com.ernestobarbosa.springboottestrestassured.model;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Availability {
    private Long bookId;
    private Integer stock;
}