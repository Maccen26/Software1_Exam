#Lukas
Feature: Editting an activity on a project
Background: 
    Given a project with projectnumber "20251"
    And a developer with the name "thfa" is contained in the app
    And "thfa" is logged in
    And "thfa" is projectleader for project "20251"
    And project "20251" has an activity with name: "Activity1", start week: 10, start year: 2025, end week: 20, end year: 2025 and budgetted time: 20 hours
        
Scenario: Editting start and end week
    When "thfa" edits the start week of "Activity1" to 15 in 2025
    And "thfa" edits the end week of "Activity1" to 25 in 2025
    Then "Activity1" has the start week 15 and end week 25 in 2025

Scenario: Editting budgetted time
    When "thfa" edits the budgetted time of "Activity1" to 25 hours
    Then "Activity1" has the budgetted time 25 hours
 
Scenario: Trying to edit a non-existing activity
    When "thfa" edits the budgetted time of "Activity2" to 25 hours
    Then recieve the error message "Activity not found"

Scenario: Trying to edit an activity when not the projectleader
    Given a developer with the name "mahh" is contained in the app
    And "mahh" is logged in
    When "mahh" edits the budgetted time of "Activity1" to 25 hours
    Then recieve the error message "Does not have permission to edit"