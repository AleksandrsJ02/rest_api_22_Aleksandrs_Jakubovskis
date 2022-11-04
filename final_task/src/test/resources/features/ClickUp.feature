Feature: This feature tests ClickUp api

  Scenario: Create, delete a new task in the folder list
    Given Space exists and contains correct information
    When Create, save folder with "Folder" name and verify that the name is correct
    Then Create a new List with "List" name in the Folder
    And Verify that the List name is correct and added to the correct folder
    Then Create Task with with auto-generated name in the list
    And Verify Task name is correct
    Then Remove the Task from the list
    And Fetch the List and verify the Task can't be found
