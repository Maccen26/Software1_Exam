Feature: Get report
Background: 
    Given a project with projectnumber 20251
    And a developer with the name "thfa" is contained in the app
    And "thfa" is logged in
    And "thfa" has projectleader permission for the project with the number 20251
        
Scenario: Getting a report for a newly started project (no activities)
    When "thfa" get the report for the project
    Then get the report "20251, no activities"

Scenario: Getting a report for an ongoing project (with activities)
    Given the project has an activity with name: "Activity1", start week: 10, start year: 2025, end week: 20, end year: 2025 and budgetted time: 20 hours
    And the project has an activity with name: "Activity2", start date: 15 marts, end date: 30 marts and budgetted time: 30 hours
    And "thfa" has registered 5 hours on "Activity1"
    And "thfa" has registered 15 hours on "Activity2"
    When "thfa" get the report for the project
    Then get the report "20251, 20 used time, 50 budgetted time. Activity1: 5 out of 20 hours. Activity2: 15 our of 30 hours."

Scenario: Getting a report for a finished project (with finished activities)
    Given the project has an activity with name: "Activity1", start week: 10, start year: 2025, end week: 20, end year: 2025 and budgetted time: 20 hours
    And the activity is finished
    When "thfa" get the report for the project
    Then get the report "20251, finished all activities"