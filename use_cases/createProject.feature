Feature: Creates a project
    Background: 
        Given the App contains a developer with the initials "thfa"
        And the developer "thfa" is logged into the App

    Scenario: Creating a project with a valid name
        When the developer "thfa" creates a project with the name "Project1"
        Then the project with the name "Project1" exists in the app

    Scenario: Creating a project with an invalid name
        When the developer "thfa" creates a project with the name "Project1"
        And the developer "thfa" creates a project with the name "Project1"
        Then the error message "Project name already exists" is given