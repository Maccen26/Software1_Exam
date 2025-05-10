Feature: Finish activity
    Background: 
        Given a project "20251"
        And "activity1" with status: "ongoing", start and end year: "2025", startweek: "10", endweek: "15"
        And "activity2" with status: "Not started", start and end year: "2025", startweek: "15", endweek: "20"
        And developer with name "thfa" is in app
        And "thfa" is logged in 
        And "thfa" is assigned to "activity1"
        And "thfa" is assigned to "activity2"
        And developer with name "mahh" is in app
        And "mahh" is logged in
        
    Scenario: developer assigned to activity change status to finished 
        When "thfa" change status for "activity1" to finished
	    Then status to finished for "activity1" succeed

    Scenario: developer not assigned to activity can’t change status for project to finished
	    When "mahh" change status for "activity1" 
	    Then gets errormessage "not assigned to activity, status change failed"
        And status for "activity1" not changed

    Scenario: Developer assigned to activity can't change status before activity is started
        When "thfa" change status for "activity2" in week "14" to finished then gets errormessage "activity pending, status change failed"
        And status for "activity2" not changed from "Not started"