package Mersys;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class US_009_Student extends MersysParent{

String GroupId="";

@Test
    public void creatNewStudentGroup(){

    Map<String, Object> group = new HashMap<>();
    group.put("active", true);
    group.put("description", "Lighting and more.");
    group.put("name", random.company().name());
    group.put("publicGroup", true);
    group.put("schoolId", "6576fd8f8af7ce488ac69b89");
    group.put("showToStudent", false);

    GroupId = given()
            .spec(reqSpec)
            .body(group)

            .when()
            .post("/school-service/api/student-group")

            .then()
            .log().body()
            .statusCode(201)
            .extract().path("id");

}

@Test (dependsOnMethods = "creatNewStudentGroup")
    public void addNewStudentsToNewGroup(){

    String[] StudentIds = {
            "657711ca8af7ce488ac6a629", "657711ca8af7ce488ac6a631",
            "657711ca8af7ce488ac6a62e","657711ca8af7ce488ac6a62f",
            "657711ca8af7ce488ac6a62b"
    };
    given()
            .spec(reqSpec)
            .body(StudentIds)

            .when()
            .post("/school-service/api/student-group/" + GroupId + "/add-students")

            .then()
            .log().body()
            .statusCode(200);

}

@Test (dependsOnMethods = "addNewStudentsToNewGroup")
    public void getGroupsStudentList(){

    given()
            .spec(reqSpec)

            .when()
            .get("/school-service/api/student-group/" + GroupId)

            .then()
            .log().body()
            .statusCode(200);

}

@Test (dependsOnMethods= "getGroupsStudentList" )
    public void removeStudentFromGroup(){

    String[] StudentId={"657711ca8af7ce488ac6a629"};

    given()
            .spec(reqSpec)
            .body(StudentId)

            .when()
            .post("/school-service/api/student-group/" + GroupId + "/remove-students")

            .then()
            .log().body()
            .statusCode(200);

    }

    @Test (dependsOnMethods = "removeStudentFromGroup")
        public void studentInfo(){

        given()
                .spec(reqSpec)

                .when()
                .get("/school-service/api/student-group/" + GroupId + "?page=0&size=10")

                .then()
                .log().body()
                .statusCode(200);

    }

}
