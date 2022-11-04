package final_task.stepdefinitions;

import final_task.domain.Folder;
import final_task.domain.List;
import final_task.domain.Space;
import final_task.domain.Task;
import final_task.helpers.TestCaseContext;
import final_task.helpers.RandomNameGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import static final_task.clients.ClickUpClient.*;
import static final_task.constants.ProjectConstants.SPACE_ID;
import static final_task.constants.ProjectConstants.SPACE_NAME;

public class ClickUpSteps {
    @Given("Space exists and contains correct information")
    public void getSpaceAndCheckInfo() {
        Response response = getSpaceInfo(SPACE_ID);
        Space space = response.as(Space.class);

        Assertions.assertThat(space.getId())
                .as("We assert that id of the space is correct")
                .isEqualTo(SPACE_ID);

        Assertions.assertThat(space.getName())
                .as("We assert that name of the space is correct")
                .isEqualTo(SPACE_NAME);
    }

    @When("Create, save folder with {string} name and verify that the name is correct")
    public void createAndSaveFolder(String folderName) {
        Response response = postFolder(folderName, SPACE_ID);
        Folder folder = response.as(Folder.class);

        Assertions.assertThat(folder.getName())
                .as("We assert that the name of the folder is correct")
                .isEqualTo(folderName);

        TestCaseContext.setFolder(folder);
    }

    @Then("Create a new List with {string} name in the Folder")
    public void createList(String listName) {
        Response response = createListFolder(listName, TestCaseContext.getFolder().getId());
        List list = response.as(List.class);

        TestCaseContext.setList(list);
    }

    @And("Verify that the List name is correct and added to the correct folder")
    public void verifyListNameCorrect() {
        Response response = verifyListName(TestCaseContext.getList().getId());
        List list = response.as(List.class);

        Assertions.assertThat(list.getName())
                .as("We assert that the List name is correct")
                .isEqualTo(TestCaseContext.getList().getName());

        Assertions.assertThat(list.getFolderObject().getId())
                .as("We assert that the list is added to the correct folder")
                .isEqualTo(TestCaseContext.getFolder().getId());
    }

    @Then("Create Task with with auto-generated name in the list")
    public void createTask() {
        RandomNameGenerator classObj = new RandomNameGenerator();
        String taskName = classObj.generateRandomName();

        Response response = createTaskInList(taskName, TestCaseContext.getList().getId());
        Task task = response.as(Task.class);

        TestCaseContext.setTask(task);
    }

    @And("Verify Task name is correct")
    public void verifyTaskName() {
        Response response = checkTaskName(TestCaseContext.getTask().getId());
        Task task = response.as(Task.class);

        Assertions.assertThat(task.getName())
                .as("We assert that the task name is correct")
                .isEqualTo(TestCaseContext.getTask().getName());
    }

    @Then("Remove the Task from the list")
    public void removeTheTask() {
        Response response = deleteTask(TestCaseContext.getTask().getId());
    }

    @And("Fetch the List and verify the Task can't be found")
    public void fetchTheList() {
        Response response = verifyTaskCantBeFound(TestCaseContext.getTask().getId());

        Assertions.assertThat((response.statusCode()))
                .as("We assert that the task can not be found anywhere")
                .isEqualTo(404);
    }
}
