Feature: Activity Overview
    Description: Let a Developer get a overview of active activities and scheduled activities. 
    Actors: Developer

    Background: A Developer, project and Activity exists in the App
        Given A project with the number 20251 is contained in the app
        And the activity with the name "Activity1" is connected to the project with the number 20251
        And a developer with the name "thfa" is contained in the app
        And "thfa" is logged in

    Scenario: Developer has active or scheduled activities
        Given "thfa" is working on the activity "Activity1" in the project 20251
        When the developer get a overview of activities
        Then he should see the following activities:
            | "Activity1" | 
    Scenario: Developer has no active or scheduled activities
        Given "thfa" is doesn't have any active or scheduled activities
        When the developer get a overview of activities
        Then no activites is shown

