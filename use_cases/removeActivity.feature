Feature: Delete Activities in a Project
Background:
    Given the App contains a project with the project number "20251"
    And the App contains a developer with the initials "thfa"
    And the App contains a developer with the initials "mahh"
    And the developer "mahh" is logged into the App
    And the developer "thfa" is logged into the App
    And the developer "thfa" is the projectleader of project "20251"

Scenario: Projectleader deletes an activity
    Given the developer "thfa" has added the activity with the title "Activity1" to the project "20251"
    When the developer "thfa" deletes the activity with the title "Activity1" from project "20251"
    Then the activity with the title "Activity1" no longer exists in project "20251"

Scenario: Non-projectleader tries to delete an activity
    Given the developer "mahh" is the projectleader of project "20251"
    And developer "mahh" has added the activity with the title "Activity1" to project "20251"
    When the developer "thfa" tries to delete the activity with the title "Activity1" from project "20251"
    Then the error message "Developer is not projectleader" is given

Scenario: Deleting an activity that does not exist
    When the developer "thfa" tries to delete the activity with the title "Activity1" from project "20251"
    Then the error message "Activity not found" is given
