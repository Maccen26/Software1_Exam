Feature: Get report
Background: 
    Given a project with projectnumber "20251"
    And a developer with the name "thfa" is contained in the app
    And "thfa" is logged in
    And "thfa" is projectleader for project "20251"
        
Scenario: Getting a report for a newly started project (no activities)
    When "thfa" gets the report for project "20251"
    Then get the report 
        | 20251: No activities |

Scenario: Getting a report for an ongoing project (with activities)
    Given project "20251" has an activity with name: "Activity1", start week: 10, start year: 2025, end week: 20, end year: 2025 and budgetted time: 20 hours
    And project "20251" has an activity with name: "Activity2", start week: 150, start year: 2025, end week: 25, end year: 2025 and budgetted time: 30 hours
    And "thfa" has registered 5 hours on "Activity1"
    And "thfa" has registered 15 hours on "Activity2"
    When "thfa" gets the report for project "20251"
    Then get the report 
        | 20251: Ongoing - 20.0/50.0 |
        | Activity1: Ongoing - 5.0/20.0 |
        | Activity2: Ongoing - 15.0/30.0 |

Scenario: Getting a report for a finished project (with finished activities)
    Given project "20251" has an activity with name: "Activity1", start week: 10, start year: 2025, end week: 20, end year: 2025 and budgetted time: 20 hours
    And the activity is finished
    When "thfa" gets the report for project "20251"
    Then get the report 
        | 20251: Finished |