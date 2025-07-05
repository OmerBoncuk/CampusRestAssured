package Mersys;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class US_105_CreateGroups extends MersysParent{
    String id ="";
    @Test
    public void createGroup () {
        Map<String, Object> group = new HashMap<>();
        group.put("id", null);
        group.put("schoolId", "6576fd8f8af7ce488ac69b89");
        group.put("name", random.company().name());
        group.put("description", random.job().field());
        group.put("active", true);
        group.put("publicGroup", true);
        group.put("showToStudent", true);

        id = given()
                .spec(reqSpec)
                .body(group)
                .when()
                .post("/school-service/api/student-group")
                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id");

        System.out.println("Group ID = " + id);
    }
    @Test(dependsOnMethods = "createGroup")
    public void editGroup () {
        Map<String, Object> group = new HashMap<>();
        group.put("id", id);
        group.put("schoolId", "6576fd8f8af7ce488ac69b89");
        group.put("name", random.company().name());
        group.put("description", random.job().field());
        group.put("active", true);
        group.put("publicGroup", true);
        group.put("showToStudent", true);

        id = given()
                .spec(reqSpec)
                .body(group)
                .when()
                .put("/school-service/api/student-group")
                .then()
                .log().body()
                .statusCode(200)
                .extract().path("id");

        System.out.println("Group ID = " + id);
    }
    @Test(dependsOnMethods = "editGroup")
    public void listGroups () {
        Map<String, Object> group = new HashMap<>();
        group.put("publicGroup", true);
        group.put("showToStudent", true);
        group.put("schoolId", "6576fd8f8af7ce488ac69b89");

        given()
                .spec(reqSpec)
                .body(group)
                .when()
                .post("/school-service/api/student-group/search")
                .then()
                .log().body()
                .statusCode(200);
    }
    @Test(dependsOnMethods = "listGroups")
    public void deleteGroup () {

        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/student-group/" + id)
                .then()
                .log().body()
                .statusCode(200)
        ;
    }
}
