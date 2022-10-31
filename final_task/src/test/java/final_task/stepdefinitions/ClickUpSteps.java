package final_task.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

//import static final_task.clients.ClickUpClient.createFolder;
import static final_task.clients.ClickUpClient.getSpaceInfo;
import static final_task.constants.ProjectConstants.SPACE_ID;

public class ClickUpSteps {
    @Given("Space exists and contains correct information")
    public void getSpaceAndCheckInfo() {
        Response response = getSpaceInfo(SPACE_ID);
    }

    @When("Create folder with {string} name")
    public void createFolder(String folderName) {
        System.out.println("2nd step executed");
        //Response response = createFolder(folderName, SPACE_ID);
    }

    @And("Save folder data and verify that the name of the folder is correct")
    public void saveFolderData() {
        System.out.println("3rd step executed");
    }

    @Then("Create a new List in the Folder")
    public void createList() {
        System.out.println("4th step executed");
    }

    @And("Verify that the List name is correct and added to the correct folder")
    public void verifyListNameCorrect() {
        System.out.println("5th step executed");
    }

    @Then("Create one Task in the list")
    public void createTask() {
        System.out.println("6th step executed");
    }

    @And("Verify Task name is correct")
    public void verifyTaskName() {
        System.out.println("7th step executed");
    }

    @Then("Remove the Task from the list")
    public void removeTheTask() {
        System.out.println("8th step executed");
    }

    @And("Fetch the List and verify the Task can't be found")
    public void fetchTheList() {
        System.out.println("9th step executed");
    }
}
