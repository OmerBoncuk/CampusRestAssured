package Mersys;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class US_002_Country extends MersysParent{


    @Test
    public void createCountry() {
        Map<String, Object> country = new HashMap<>();
        country.put("id", null);
        country.put("name", random.country().name());
        country.put("code", random.country().currencyCode());
        country.put("translateName", null);
        country.put("hasState", true);

             given()
                .spec(reqSpec)
                .body(country)

                .when()
                .post("/school-service/api/countries")

                .then()
                .log().body()
                .statusCode(201);
    }
}
