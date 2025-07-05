package Mersys;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
public class US_101_State extends MersysParent {
    String id = "";

    @Test
    public void createCountry() {
        Map<String, Object> country = new HashMap<>();
        country.put("id", "6859a88e8ed86951f650b890"); // Geçerli country ID

        Map<String, Object> state = new HashMap<>();
        state.put("id", null);
        state.put("name", random.address().state());
        state.put("shortName", random.address().stateAbbr());
        state.put("country", country);
        state.put("translateName", new String[]{});

        id = given()
                .spec(reqSpec)
                .body(state)
                .when()
                .post("/school-service/api/states")
                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id");

        System.out.println("Created State ID: " + id);
    }

    @Test(dependsOnMethods = "createCountry")
    public void updateState() {
        Map<String, Object> country = new HashMap<>();
        country.put("id", "6859a88e8ed86951f650b890");

        Map<String, Object> state = new HashMap<>();
        state.put("id", id);
        state.put("name", random.address().state() + "_Updated");
        state.put("shortName", random.address().stateAbbr());
        state.put("country", country);
        state.put("translateName", new String[]{});

        id = given()
                .spec(reqSpec)
                .body(state)
                .when()
                .put("/school-service/api/states")
                .then()
                .log().body()
                .statusCode(200)
                .extract().path("id");
    }

    @Test(dependsOnMethods = "updateState")
    public void deleteState() {
        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/states/" + id)
                .then()
                .log().body()
                .statusCode(200);
    }
}