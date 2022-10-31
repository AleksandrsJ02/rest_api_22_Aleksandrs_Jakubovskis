package final_task.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static final_task.constants.ProjectConstants.API_TOKEN;

public class ClickUpClient {
    public static Response getSpaceInfo(String spaceId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .queryParam("archived", false)
                .header("Authorization", API_TOKEN)
                .when()
                .get("https://api.clickup.com/api/v2/space/" + spaceId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }
}
