package br.com.ernestobarbosa.springboottestrestassured.config;

import static com.google.common.collect.Lists.newArrayList;

import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.ernestobarbosa.springboottestrestassured.web"))
            .build()
            .apiInfo(apiInfo())
            .securitySchemes(newArrayList(apiKey()))
            .directModelSubstitute(Calendar.class, String.class)
            .directModelSubstitute(LocalDate.class, String.class)
            .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
            .title("Book Store")
            .description("Documentação dos serviços de Demonstração.")
            .version("v1")
            .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("key", "Authorization", "header");
    }
}