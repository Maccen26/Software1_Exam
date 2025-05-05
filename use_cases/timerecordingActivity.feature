#Mads HH
Feature: Register worked hours on Activity
    Description: Let a developer register worked hours on a activity. 
    The developer should also be able to register when the person is not assigned
    to the project or activity.
    The developer should only be permitted to log worked
    hours in increments of 30 minutes or one hour.
    Actors: Deveoper

    Background: A Developer, project and Activity exists in the App
        Given A project with the number 20251 is contained in the app
        And the activity with the name "Activity1" is connected to the project with the number 20251
        And "Activty1" is the only activty within the project
        And a developer with the name "thfa" is contained in the app
        And "thfa" is logged in

    Scenario: Developer register allowed time on a activity
        Given the activity "Activity1" from the project 20251
        When "thfa" register 1 hour on the activity "Activity1" from the project 20251
        Then 1 hour is registered

    Scenario: Developer try to registed to little time on activity
         Given the activity "Activity1" from the project 20251
        When "thfa" register 0.1 hours on the activity "Activity1" from the project 20251
        Then an error message is given to the Developer: "Time can only be logged in 0.5 hours increments"

    Scenario: Developer try to register time on a Activity that doesn't exists
        When "thfa" register 1 hours on the activity "Activity2" from the project 20251
        Then an error message is given to the Developer: "Cannot log time. Activty2 not found within project 20251"
        
