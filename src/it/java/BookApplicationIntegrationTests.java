import br.com.ernestobarbosa.springboottestrestassured.BookSpringBootApplication;
import br.com.ernestobarbosa.springboottestrestassured.model.Availability;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import context.Config;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.defaultParser;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        classes = BookSpringBootApplication.class
)
@ActiveProfiles("it")
@ContextConfiguration(classes = Config.class)
public class BookApplicationIntegrationTests {

    @Value("${availability-id}")
    private Long bookId;

    @LocalServerPort
    private Integer port;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(2345));

    @Before
    public void setUp(){
        baseURI = "http://localhost:" + port;
        stubFor(
            get(urlEqualTo("/" + bookId))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"bookName\": \"Teste\", \"available\": false}")
                )
        );
    }

    @Test
    public void availabilityTestIT() {
        assertFalse(
            given()
                .pathParam("id", bookId)
            .when()
                .get("/availability/{id}")
            .then()
                .statusCode(200)
                .extract().as(Availability.class)
                    .isAvailable()
        );
    }

}
