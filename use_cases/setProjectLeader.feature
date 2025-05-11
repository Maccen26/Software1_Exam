Feature: Set a projectleader for a project
Background:
    Given project "20251" and "20252" exists
    And the App contains a developer with the initials "thfa"
    And the developer "thfa" is logged into the App

Scenario: Setting a projectleader when no projectleader is assigned
    Given no projectleader is assigned to project "20251"
    When the developer "thfa" tries to set the projectleader of project "20251" to "thfa"
    Then "thfa" is projectleader of project "20251"

Scenario: Setting a new projectleader when projectleader
    Given the developer "thfa" tries to set the projectleader of project "20251" to "thfa"
    And "thfa" is projectleader of project "20251"
    Given the App contains a developer with the initials "mahh"
    When the developer "thfa" tries to set the projectleader of project "20251" to "mahh"
    Then "mahh" is projectleader of project "20251"
#
Scenario: Setting a projectleader when projectleader already exists
    Given the developer "thfa" tries to set the projectleader of project "20251" to "thfa"
    And "thfa" is projectleader of project "20251"
    And the App contains a developer with the initials "mahh"
    And the developer "mahh" is logged into the App
    When the developer "mahh" tries to set the projectleader of project "20251" to "mahh"
    Then the error message "Only the current project leader can reassign the project leader" is thrown