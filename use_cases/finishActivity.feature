#Freja
Feature: Finish activity
    Background: 
        Given a project "20251"
        And developer with name "thfa" is in app
        And developer with name "mahh" is in app
        And the developer "mahh" is logged in
        And the developer "thfa" is logged in
        And "thfa" is projectleader for the project "20251"
        And project "20251" has an activity with name: "activity1", start week: 15, start and year: 2025, end week: 25  
        And project "20251" has an activity with name: "activity2", start week: 15, start and year: 2025, end week: 25 
        And "thfa" is assigned to "activity1"
        And "thfa" is assigned to "activity2"
        And "thfa" changes "activity1" with status: "ongoing"
        And "thfa" changes "activity2" with status: "Not started"
        
    Scenario: developer assigned to activity change status to finished 
        When "thfa" sets the status as "Finished" for the activity "activity1"
	    Then the activity "activity1" has status "Finished"

    Scenario: developer not assigned to activity can’t change status for project to finished
	    When "mahh" tries to set the status as "Finished" for the activity "activity1"
	    Then gets errormessage "not assigned to activity, status change failed"
        And status for "activity1" not changed from "ongoing"

    Scenario: Developer assigned to activity can't change status before activity is started
        When "thfa" tries to change status to "Finished" for "activity2"
        Then gets errormessage "activity pending, status change failed"
        And status for "activity2" not changed from "Not started"