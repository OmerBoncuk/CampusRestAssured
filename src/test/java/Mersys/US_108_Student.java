package Mersys;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class US_108_Student extends MersysParent{
    String id ="";

    @Test
    public void createStudentGroup () {
        Map<String, Object> group = new HashMap<>();
        group.put("id", null);
        group.put("schoolId", "6576fd8f8af7ce488ac69b89");
        group.put("name", random.company().name());
        group.put("description", random.job().field());
        group.put("active", true);
        group.put("publicGroup", true);
        group.put("showToStudent", false);

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
    @Test(dependsOnMethods = "createStudentGroup")
    public void addStudentsToGroup() {
        String[] studentIds = {
                "657711ca8af7ce488ac6a628", "657711ca8af7ce488ac6a62d",
                "657711ca8af7ce488ac6a629", "657711ca8af7ce488ac6a631",
                "657711ca8af7ce488ac6a62e", "657711ca8af7ce488ac6a62f",
                "657711ca8af7ce488ac6a62b", "657711ca8af7ce488ac6a630",
                "657711ca8af7ce488ac6a62c", "657711ca8af7ce488ac6a62a"
        };

        given()
                .spec(reqSpec)
                .body(studentIds)
                .when()
                .post("/school-service/api/student-group/" + id + "/add-students?page=0&size=10")
                .then()
                .log().body()
                .statusCode(200);
    }
    @Test(dependsOnMethods = "addStudentsToGroup")
    public void getStudentGroup() {
        given()
                .spec(reqSpec)
                .when()
                .get("/school-service/api/student-group/" + id)
                .then()
                .log().body()
                .statusCode(200);
    }
    @Test(dependsOnMethods = "getStudentGroup")
    public void removeStudentsFromGroup() {
        String[] studentIds = {
                "657711ca8af7ce488ac6a628", "657711ca8af7ce488ac6a629",
                "657711ca8af7ce488ac6a62d", "657711ca8af7ce488ac6a62e",
                "657711ca8af7ce488ac6a62f", "657711ca8af7ce488ac6a631",
                "657711ca8af7ce488ac6a62a", "657711ca8af7ce488ac6a62b",
                "657711ca8af7ce488ac6a62c", "657711ca8af7ce488ac6a630"
        };

        given()
                .spec(reqSpec)
                .body(studentIds)
                .when()
                .post("/school-service/api/student-group/" + id + "/remove-students?page=0&size=10")
                .then()
                .log().body()
                .statusCode(200);
    }
    @Test(dependsOnMethods = "removeStudentsFromGroup")
    public void deleteStudentGroup() {
        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/student-group/" + id)
                .then()
                .log().body()
                .statusCode(200);
    }

}
