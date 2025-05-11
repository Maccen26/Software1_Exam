#Lukas
Feature: Creates a project
    Background: 
        Given the App contains a developer with the initials "thfa"
        And the developer "thfa" is logged into the App

    Scenario: Creating a project with a valid number
        When the developer "thfa" creates a project
        Then the project with the number "20251" exists in the app

    Scenario: Creating multiple projects
        When the developer "thfa" creates a project
        And the developer "thfa" creates a project
        Then the project with the number "20251" and "20252" exists in the app