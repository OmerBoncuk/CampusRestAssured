package Mersys;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class US_010_Incident extends MersysParent {
    public String incidentID = "";

    @Test(priority = 1)
    public void getIncidentList() {
        Map<String, Object> incident = new HashMap<>();
        incident.put("tenantId", "646cb816433c0f46e7d44cb0");

        given()
                .spec(reqSpec)
                .body(incident)

                .when()
                .post("/school-service/api/incident-type/search")

                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "getIncidentList")
    public void postIncident() {
        Map<String, Object> incident = new HashMap<>();
        incident.put("name", "Incident" + random.number().randomNumber());
        incident.put("active", true);
        incident.put("tenantId", "646cb816433c0f46e7d44cb0");
        incident.put("minPoint", 2);
        incident.put("maxPoint", 6);
        incident.put("academicBased", false);
        incident.put("enabled", false);
        incident.put("notifyWithMessage", false);
        incident.put("notifyWithEmail", false);

        incidentID = given()
                .spec(reqSpec)
                .body(incident)

                .when()
                .post("/school-service/api/incident-type")

                .then()
                .log().body()
                .statusCode(201)
                .extract().body().path("id");

        System.out.println("incidentID = " + incidentID);
    }

    @Test(dependsOnMethods = "postIncident")
    public void putIncident() {
        Map<String, Object> incident = new HashMap<>();
        incident.put("id", incidentID);
        incident.put("name", "Incident" + random.number().randomDigit());
        incident.put("active", true);
        incident.put("tenantId", "646cb816433c0f46e7d44cb0");
        incident.put("minPoint", 2);
        incident.put("maxPoint", 6);
        incident.put("academicBased", false);
        incident.put("enabled", false);
        incident.put("notifyWithMessage", false);
        incident.put("notifyWithEmail", false);

        given()
                .spec(reqSpec)
                .body(incident)

                .when()
                .put("/school-service/api/incident-type")

                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "putIncident")
    public void deleteIncident() {
        System.out.println("incidentID = " + incidentID);
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/incident-type/" + incidentID)

                .then()
                .log().body()
                .statusCode(200);
    }
}
