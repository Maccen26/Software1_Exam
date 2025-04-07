Feature: Time recording on a Activity
    As a user, #Describtion
    I want to be able to register time on a activity
    So that I can keep track of my progress.
    #Actor Employee

    Scenario: Main Scenario
        Given a user "John Doe" exists
        And a project "TestProject" and activity "TestActivity" exists
        When "John Doe" register 1 hour on the project "TestProject" and activity "TestActivity" 
        Then 1 hour is registered

    Scenario: Alternative Scenario
       Given a user "John Doe" exists
        And a project "TestProject" and activity "TestActivity" exists
        When "John Doe" register 20 minutes on the project "TestProject" and activity "TestActivity" 
        Then an error message is given to the Employee: "Registed only in hours or per 30 minutes" 
        
        
