package Mersys;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class US_109_GradingScheme extends MersysParent {

    Faker randomMaker = new Faker();
    public String gradingID = "";

    @Test
    public void getGradingSchemeList() {

        String tenantID = "646cb816433c0f46e7d44cb0";

        given()
                .spec(reqSpec)

                .when()
                .get("/school-service/api/grading-schemes/tenant/" + tenantID + "/search")

                .then()
                .log().body()
                .statusCode(200);
    }


    @Test(dependsOnMethods = "getGradingSchemeList")
    public void postCreateGrading() {

        String randomUserName = random.name().username();
        int randomMinPoint = random.number().numberBetween(5, 100);


        Map<String, Object> createGrading = new HashMap<>();
        createGrading.put("name", randomUserName);
        createGrading.put("tenantId", "646cb816433c0f46e7d44cb0");
        createGrading.put("type", "POINT");
        createGrading.put("minPointToPass", randomMinPoint);

        gradingID =
                given()
                        .spec(reqSpec)
                        .body(createGrading)

                        .when()
                        .post("/school-service/api/grading-schemes")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().body().path("id")
        ;
    }

    @Test(dependsOnMethods = "postCreateGrading")
    public void putUpdateGrading() {

        String randomUserName = random.name().username();
        int randomMinPoint = random.number().numberBetween(5, 100);


        Map<String, Object> updateGrading = new HashMap<>();
        updateGrading.put("id", gradingID);
        updateGrading.put("name", randomUserName);
        updateGrading.put("tenantId", "646cb816433c0f46e7d44cb0");
        updateGrading.put("type", "POINT");
        updateGrading.put("minPointToPass", randomMinPoint);


        given()
                .spec(reqSpec)
                .body(updateGrading)

                .when()
                .put("/school-service/api/grading-schemes")

                .then()
                .log().body()
                .statusCode(200)
        ;
    }

    // Bu test icin sistemsel hata alindi, onun icin BUG ticket acilmistir
    @Test(dependsOnMethods = "putUpdateGrading")
    public void deleteGrading(){

        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/grading-schemes/" + gradingID)

                .then()
                .log().body()
                .statusCode(200);
    }

    // Bu test icin sistemsel hata alindi, onun icin BUG ticket acilmistir
    @Test(dependsOnMethods = "deleteGrading")
    public void deleteGradingNegative(){

        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/grading-schemes/" + gradingID)

                .then()
                .log().body()
                .statusCode(400);
    }
}

