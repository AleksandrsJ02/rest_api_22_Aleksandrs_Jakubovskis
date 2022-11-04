package final_task.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

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

    public static Response postFolder(String folderName, String spaceId) {

        var payload = new JSONObject();
        payload.put("name", folderName);

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .body(payload.toString())
                .when()
                .post("https://api.clickup.com/api/v2/space/" + spaceId + "/folder")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createListFolder(String listName, String folderId) {

        var payload = new JSONObject();
        payload.put("name", listName);

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .body(payload.toString())
                .when()
                .post("https://api.clickup.com/api/v2/folder/" + folderId + "/list")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response verifyListName(String listId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .get("https://api.clickup.com/api/v2/list/" + listId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createTaskInList(String taskName, String listId) {

        var payload = new JSONObject();
        payload.put("name", taskName);

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .body(payload.toString())
                .when()
                .post("https://api.clickup.com/api/v2/list/" + listId + "/task")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response checkTaskName(String taskId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .get("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response deleteTask(String taskId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .delete("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .statusCode(204)
                .extract().response();
    }

    public static Response verifyTaskCantBeFound(String taskId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .get("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .statusCode(404)
                .extract().response();
    }

    public static Response deleteFolder(String folderId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .delete("https://api.clickup.com/api/v2/folder/" + folderId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }
}

//payload.put("content", "New List Content");
//payload.put("due_date", 1567780450202l);
//payload.put("due_date_time", false);
//payload.put("priority", 1);
//payload.put("assignee", 43296421);
//payload.put("status", "red");
