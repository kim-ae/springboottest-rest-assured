import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import category.ReportsTests;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import utils.AllureReport;

@Feature("[Treinamento] Avaliação Report Allure")
@Story("Conhecendo as funcionalidade do Report Allure")
public class ReportsAllureTestIT {

    @Before
    public void setUp() {
        RestAssured.reset();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        requestSpecBuilder.setBaseUri("https://swapi.co/api");
        RestAssured.requestSpecification = requestSpecBuilder.build();
    }
    
    
    @Test
    @Category(ReportsTests.class)
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Display Name - Teste 01.")
    @Description("Teste 01.")
    @Issue("1")
    @Step("Consulta realizada GET /people/1/")
    public void testeUmIT() {
        given().log().all().contentType(ContentType.JSON).when().get("/people/1/").then().statusCode(200);
        
        HashMap<String, String> valores = new HashMap<String, String>();
        valores.put("Pessoa Pesquisada: ", "1");

        AllureReport.attachInfoCliente(valores);
        
    }

    @Test
    @Category(ReportsTests.class)
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Display Name - Teste 2.")
    @Description("Descrição - Teste 2.")
    @Issue("2")
    public void testeDoisIT() {
        given().log().all().contentType(ContentType.JSON).when().get("/people/1/").then().statusCode(200);
    }

    @Test
    @Category(ReportsTests.class)
    @Link("http://www.cwi.com.br")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Display Name - Teste 3.")
    @Description("Descrição - Teste 3.")
    public void testTresIT() {
        
        given().log().all().contentType(ContentType.JSON).when().get("/people/1/").then().statusCode(200);

    }
    
    @Ignore
    @Test
    @Category(ReportsTests.class)
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Display Name - Teste 4 - Ignorado.")
    @Description("Descrição - Teste 4 - Ignorado.")
    public void testQuatroIT() {
        given().log().all().contentType(ContentType.JSON).when().get("/people/1/").then().statusCode(200);

    }
    
    @Test
    @Category(ReportsTests.class)
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Display Name - Teste 5.")
    @Description("Descrição - Teste 5.")
    public void testCincoIT() {
        
        given().log().all().contentType(ContentType.JSON).when().get("/people/1/").then().statusCode(400);

    }
    
    @Test
    @Flaky
    @Category(ReportsTests.class)
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Display Name - Teste 6.")
    @Description("Descrição - Teste 6.")
    public void testSeisIT() {
        
        given().log().all().contentType(ContentType.JSON).when().get("/people/1/").then().statusCode(200);

    }

}
