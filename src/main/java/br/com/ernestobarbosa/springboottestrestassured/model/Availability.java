package br.com.ernestobarbosa.springboottestrestassured.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Availability {
    private String bookName;
    private boolean available;
}