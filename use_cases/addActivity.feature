Feature: Add Activities to Project
Background:
    Given the App contains a project with the project number 20251
    And the App contains a developer with the initials "thfa"
    And the developer "thfa" is logged into the App
    
Scenario: Adding an activity when projectleader
    Given the developer "thfa" is the projectleader of project 20251
    When the developer "thfa" adds an activity with the title "Activity1", start week 20, start year 2025, end week 20, end year 2025 to project 20251
    Then the activity with the title "Activity1" exists in project 20251
    
Scenario: Adding an activity when not projectleader
    Given the App contains a developer with the initials "mahh"
    And the developer "mahh" is logged into the App
    And the developer "mahh" is the projectleader of project 20251
    When the developer "thfa" tries to add an activity with the title "Activity1", start week 20, start year 2025, end week 20, end year 2025 to project 20251
    Then the error message "Developer is not projectleader" is given
    
Scenario: Adding a duplicate activity
    Given the developer "thfa" is the projectleader of project 20251
    And the project 20251 has an activity with the title "Activity1"
    When the developer "thfa" adds an activity with the title "Activity1", start week 20, start year 2025, end week 20, end year 2025 to project 20251
    Then the error message "Activity title already exists" is given
    
Scenario: Adding an activity when no projectleader exists
    Given the project 20251 has no assigned projectleader
    When the developer "thfa" adds an activity with the title "Activity1", start week 20, start year 2025, end week 20, end year 2025 to project 20251
    Then the activity with the title "Activity1" exists in project 20251