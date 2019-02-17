package core;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.defaultParser;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import br.com.ernestobarbosa.springboottestrestassured.BookSpringBootApplication;
import context.Config;
import io.restassured.parsing.Parser;

@ActiveProfiles("it")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = BookSpringBootApplication.class)
@ContextConfiguration(classes = Config.class)
public class BaseTest {

    @LocalServerPort
    private Integer port;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(2345));
        
    @Before
    public void setUp(){
        defaultParser = Parser.JSON;
        baseURI = "http://localhost:" + port;
//        stubFor(
//            post(urlEqualTo("/"))
//                .willReturn(
//                    aResponse()
//                        .withStatus(201)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody(
//                            new Gson().toJson(
//                                new Availability().builder().bookId(1L).stock(10)
//                            )
//                        )
//                )
//        );
    }
}
