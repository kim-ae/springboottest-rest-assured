package br.com.ernestobarbosa.springboottestrestassured.config;

import br.com.ernestobarbosa.springboottestrestassured.service.AvailabilityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RestTemplate getRestTemplate (){
        return new RestTemplate();
    }

}
