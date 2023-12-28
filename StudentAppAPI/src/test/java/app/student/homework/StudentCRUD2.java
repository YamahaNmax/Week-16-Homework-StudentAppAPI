package app.student.homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StudentCRUD2 {

    Response response;
    ValidatableResponse validatableResponse;
    RequestSpecification requestSpecification;

    @Test
    public void getStudentList() {
        given().log().all()
                .get("http://localhost:8080/student/list")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void getStudentInfoById() {
        given().log().all()
                .pathParam("id", "45")
                .when()
                .get("http://localhost:8080/student/{id}")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void createStudentInfo() {
        String jsonData = "{\n" +
                "        \"firstName\": \"ab\",\n" +
                "        \"lastName\": \"cd\",\n" +
                "        \"email\": \"asdf@gmail.com\",\n" +
                "        \"programme\": \"Spy\",\n" +
                "        \"courses\": [\n" +
                "            \"Flyinsfsg\",\n" +
                "            \"Drisfving\"\n" +
                "        ]}";
        response = given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonData)
                .post("http://localhost:8080/student");
        response.then().log().all().statusCode(201)
                .body("msg", equalTo("Student added"));

    }

    @Test
    public void updateStudentInfo() {

        String jsonData = "{\n" +
                "    \"id\": 44,\n" +
                "    \"firstName\": \"Bond\",\n" +
                "    \"lastName\": \"Patel\",\n" +
                "    \"email\": \"bond007@gmail.com\",\n" +
                "    \"programme\": \"Agent\",\n" +
                "    \"courses\": [\n" +
                "        \"Swimming\",\n" +
                "        \"Running\"\n" +
                "    ]\n" +
                "}";
        response = given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonData)
                .put("http://localhost:8080/student/70");
        response.then().statusCode(200)
                .body("msg", equalTo("Student Updated"));

    }

    @Test
    public void verifyNewStudentInfoDeletedByID() {
        given().log().all()
                .pathParam("id", 41)
                .when()
                .delete("http://localhost:8080/student/{id}")
                .then().log().all()
                .statusCode(204);
    }
}

