#Freja
Feature: Finish project
    Background: 
        Given a project with the name "20251" 
        And developer named "thfa" is in app
        And developer named "mahh" is in app
        And and the developer "thfa" is logged in 
        And and the developer "mahh" is logged in
        And "thfa" has status project leader for project "20251"
        And status for project "20251" is "ongoing"
        And the project "20251" has assigned "activity1", start week: 15, start and year: 2025, end week: 25
        And "mahh" has status developer on project "20251" for "activity1"
        And "activity1" in project "20251" has status "Finished"

    
    Scenario: other than project leader try to change project status and fail
        When "mahh" wants to set status to "Finished" for project "20251"
        Then shows errormessage "not project leader, status change failed"
        And status for project "20251" not changed from "ongoing"

    Scenario: project leader succesfully change project status
        When "thfa" change project "20251" status to "Finished"
        Then status for project "20251" changed to "Finished"
        
    Scenario: Project leader can't change status due to activities where status isn't finished
        Given a project with the name "20252"
        And "thfa" has status project leader for project "20252"
        And the project "20252" has assigned "activity1", start week: 15, start and year: 2025, end week: 25
        And "thfa" has status developer on project "20252" for "activity1"
        And "activity1" in project "20252" has status "ongoing"
        When "thfa" change project "20252" status to "Finished"
        Then shows errormessage "Still ongoing activities, status change failed"
        And status for project "20252" not changed from "ongoing"