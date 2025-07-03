package Mersys;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class US_102_Cities extends MersysParent{

    Faker randomMaker = new Faker();
    public String cityID="";

    @Test
    public void getCitiesList(){
        given()
                .spec(reqSpec)

                .when()
                .get("/school-service/api/cities")

                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "getCitiesList")
    public void postCreateCity(){

        String randomCity = randomMaker.address().cityName();

        Map<String, Object> countryID = new HashMap<>();
        countryID.put("id","6859a88e8ed86951f650b890");

        Map<String, Object> createCity = new HashMap<>();
        createCity.put("name",randomCity);
        createCity.put("country", countryID);
        createCity.put("state", null);
        createCity.put("translateName", new ArrayList<>());

        cityID =
                given()
                .spec(reqSpec)
                .body(createCity)

                .when()
                .post("/school-service/api/cities")

                .then()
                .log().body()
                .statusCode(201)
                .extract().body().path("id")
        ;
    }


    @Test(dependsOnMethods = "postCreateCity")
    public void updateCity() {

        String randomCity = randomMaker.address().cityName();

        Map<String, Object> countryID = new HashMap<>();
        countryID.put("id", "6859a88e8ed86951f650b890");

        Map<String, Object> updateCity = new HashMap<>();
        updateCity.put("id", cityID);
        updateCity.put("name", randomCity);
        updateCity.put("country", countryID);
        updateCity.put("hasState", "false");
        updateCity.put("state", new HashMap<>());
        updateCity.put("translateName", new ArrayList<>());


        given()
                .spec(reqSpec)
                .body(updateCity)

                .when()
                .put("/school-service/api/cities")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }

    @Test(dependsOnMethods = "updateCity")
    public void deleteCity(){

        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/cities/"+cityID)

                .then()
                .log().body()
                .statusCode(200)
        ;

    }


}
