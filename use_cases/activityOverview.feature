#Mads HH
Feature: Activity Overview
    Description: Let a Developer get a overview of active activities and scheduled activities. 
    Actors: Developer

    Background: A Developer, project and Activity exists in the App
        Given project "20251" and "20252" exists
        And the developers "huba" "mahh" and "thfa" are contained in the app 
        And "thfa" is logged into the app
        And the activity with name "Activity1", under project "20251" with startweek 10, endweek 15 and startyear 2025, endyear 2025

   Scenario: Developer has active or scheduled activities
        Given "thfa" is only working on the activity "Activity1" in the project "20251"
        When the developer "thfa" get an overview of all activities
        Then he should see the following activities: "Activity1"  

    Scenario: Developer has no active or scheduled activities
        Given "thfa" is doesn't have any active or scheduled activities
        When "thfa" get a overview 
        Then no activites is shown