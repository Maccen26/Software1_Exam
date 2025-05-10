Feature: Add Activities to Project
Background:
    Given the App contains a project with the project number "20251"
    And the App contains a developer with the initials "thfa"
    And the developer "thfa" is logged into the App
    And the App contains a developer with the initials "mahh"
    And developer "thfa" is the projectleader of project "20251"
    
Scenario: Adding an activity when projectleader
    When the developer "thfa" adds an activity with the title "Activity1", start week 20, start year 2025, end week 20, end year 2025 to project "20251"
    Then the activity with the title "Activity1" exists in project "20251"
    
Scenario: Adding an activity when not projectleader
    Given the developer "mahh" is the projectleader of project "20251"
    When the developer "thfa" adds the activity with the title "Activity1", start week 20, start year 2025, end week 20, end year 2025 to project "20251"
    Then the error message "Developer is not projectleader" is then given
    
Scenario: Adding a duplicate activity
    Given the developer "thfa" has added the activity with the title "Activity1", start week 20, start year 2025, end week 20, end year 2025 to project "20251"
    When "thfa" adds the activity with the title "Activity1", start week 20, start year 2025, end week 20, end year 2025 to project "20251"
    Then the error message "Activity title already exists" is then given