Feature: Activity Overview
    As a user, #Describtion
    I want to view an overview of my activities,
    So that I can keep track of my schedule and progress.
    #Actor Employee

    Scenario: Main Scenario
        Given a user "John Doe" exists
        And he has the following activities:
            | "Finish Project | 
        When "John Doe" view his activities 
        Then he should see the following activities:
            | "Finish Project | 

    Scenario: Alternative Scenario
        Given a user "John Doe" exists
        And he has hasn't any activities 
        When "John Doe" view his activities 
        Then he should get the message: "No activites active" 
        
