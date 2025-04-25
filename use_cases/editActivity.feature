Feature: Editting an activity on a project
Background: 
    Given a project with projectnumber 20251
    And the project has an activity with name: "Activity1", start week: 10, start year: 2025, end week: 20, end year: 2025 and budgetted time: 20 hours
    And a developer with the name "thfa" is contained in the app
    And "thfa" is logged in
    And "thfa" has projectleader permission for the project with the number 20251
        
Scenario: Editting start and end week
    When "thfa" edit the start week of the activity to 15 in 2025
    And "thfa" edit the end week of the activity to 25 in 2025
    Then the activity has the start week 15 and end week 25 in 2025

Scenario: Editting budgetted time
    When "thfa" edit the budgetted time of the activity to 25 hours
    Then the activity has the budgetted time 25 hours
 
Scenario: Trying to edit a non-existing activity
    When "thfa" try to change the budget time to 25 on an activity with name "Activity2"
    Then recieve the error message "Activity not found"

Scenario: Trying to edit an activity when not the projectleader
    Given a developer with the name "mahh" is contained in the app
    And "mahh" is logged in
    When "mahh" edit the budgetted time of the activity to 25 hours
    Then recieve the error message "Does not have permission to edit"