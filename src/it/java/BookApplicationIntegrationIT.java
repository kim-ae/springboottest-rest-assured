import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;

import com.google.gson.Gson;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import core.AbstractIT;
import io.restassured.http.ContentType;

public class BookApplicationIntegrationIT extends AbstractIT {

    @Test
    public void noBooksTestIT() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/books/")
        .then()
            .statusCode(200)
            .body("$", empty());
    }

    @Test
    public void noAvailabilityTestIT() {
        given()
            .contentType(ContentType.JSON)
            .pathParam("id", 100)
        .when()
            .get("/books/{id}/availability")
        .then()
            .statusCode(404);
    }

    @Test
    public void createBooksTestIT() {
        Book book = new Book().builder().name("My Book").price(10.0).build();
        given()
            .contentType(ContentType.JSON)
            .body(new Gson().toJson(book))
        .when()
            .post("/books/")
        .then()
            .statusCode(201);

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/books/")
        .then()
            .statusCode(200)
        .body("$", hasSize(greaterThan(0)));
    }

}
