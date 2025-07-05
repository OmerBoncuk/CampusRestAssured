
package Mersys;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class US_108_Education extends MersysParent {

    String id = "";

    @Test(priority = 1)
    public void getEducationById() {
        given()
                .spec(reqSpec)
                .when()
                .get("/school-service/api/subjects/646cb816433c0f46e7d44cb0/tenant/6576fd8f8af7ce488ac69b89/school/keyvalue")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(priority = 2)
    public void createEducation() {
        Map<String, Object> body = new HashMap<>();
        body.put("id", null);
        body.put("name", random.name().fullName()); // random full name
        body.put("description", random.job().title()); // random job descriptor
        body.put("gradeLevelId", "646cca8aacf2ee0d37c6d998");
        body.put("subjectId", "646ccc7eacf2ee0d37c6d9ab");
        body.put("gradeCategoriesTemplateId", "657707e18af7ce488ac69ba9");
        body.put("gradeCategoryId", "59ef-d8ab");
        body.put("calculationMode", "MEAN");
        body.put("numberOfScores", 5);
        body.put("scoreWeights", Arrays.asList(1, 1, 1, 1, 1));
        body.put("parentStandardCalculationStarategy", "TURN_OFF");

        id = given()
                .spec(reqSpec)
                .body(body)
                .when()
                .post("/school-service/api/education-standard")
                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id");

        System.out.println("Created Education ID: " + id);
    }

    @Test(priority = 3)
    public void updateEducation() {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("name", random.name().fullName()); // random full name
        body.put("description", random.job().title()); // random job descriptor
        body.put("gradeLevelId", "646cca8aacf2ee0d37c6d998");
        body.put("subjectId", "646ccc7eacf2ee0d37c6d9ab");
        body.put("gradeCategoriesTemplateId", "657707e18af7ce488ac69ba9");
        body.put("gradeCategoryId", "59ef-d8ab");
        body.put("calculationMode", "MEAN");
        body.put("numberOfScores", 5);
        body.put("scoreWeights", Arrays.asList(1, 1, 1, 1, 1));
        body.put("parentStandardCalculationStarategy", "TURN_OFF");

        given()
                .spec(reqSpec)
                .body(body)
                .when()
                .put("/school-service/api/education-standard")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(priority = 4)
    public void deleteEducation() {
        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/education-standard/" + id)
                .then()
                .log().body()
                .statusCode(204);
    }
}