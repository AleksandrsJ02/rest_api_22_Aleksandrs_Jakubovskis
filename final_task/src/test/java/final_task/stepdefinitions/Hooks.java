package final_task.stepdefinitions;

import final_task.helpers.TestCaseContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static final_task.clients.ClickUpClient.deleteFolder;

public class Hooks {

    @Before
    public void beforeHook() {
        TestCaseContext.init();
        System.out.println("THE SCENARIO HAS STARTED");
    }

    @After
    public void afterHook() {
        deleteFolder(TestCaseContext.getFolder().getId());
        System.out.println("THE SCENARIO HAS ENDED");
    }
}
