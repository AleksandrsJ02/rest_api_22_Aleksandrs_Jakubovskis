Feature: This feature tests ClickUp api

  Scenario: Create, delete a new task in the folder list
    Given Space exists and contains correct information
    When Create folder with "Folder" name
    And Save folder data and verify that the name of the folder is correct
    Then Create a new List in the Folder
    And Verify that the List name is correct and added to the correct folder
    Then Create one Task in the list
    And Verify Task name is correct
    Then Remove the Task from the list
    And Fetch the List and verify the Task can't be found
