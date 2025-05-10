#Mads HH
Feature: Register worked hours on Activity
    Description: Let a developer register worked hours on a activity. 
    The developer should also be able to register when the person is not assigned
    to the project or activity.
    The developer should only be permitted to log worked
    hours in increments of 30 minutes or one hour.
    Actors: Deveoper

    Background: A Developer, project and Activity exists in the App
        Given project "20251" and "20252" exists
        Given the developers "huba" "mahh" and "thfa" is in the app
        And the developers "huba" "mahh" and "thfa" are contained in the app
        And "thfa" is logged in
        And "thfa" is project leader on project "20251"
        Given the activity with name "Activity1", under project "20251" with startweek 10, endweek 15 and startyear 2025, endyear 2025
        And "thfa" is only working on the activity "Activity1" in the project "20251"

    Scenario: Developer register allowed time on a activity
        Given the activity "Activity1" from the project "20251"
        When "thfa" register 1.0 hour on the activity "Activity1" from the project "20251"
        Then 1 hour is registered on "Activity1" in the project "20251" for "thfa"


    Scenario: Developer add more time to a activty 
        Given "thfa" register 1.0 hour on the activity "Activity1" from the project "20251"
        When "thfa" add 2.0 hours to "Activity1" in the project "20251" 
        Then 3.0 hour is registered on "Activity1" in the project "20251" for "thfa"


    Scenario: Developer try to registed to little time on activity
        Given the activity "Activity1" from the project "20251"
        When "thfa" register 0.1 hour on the activity "Activity1" from the project "20251"
        Then an error message is given to the Developer: "Time can only be logged in 0.5 hours increments"

