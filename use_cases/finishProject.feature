Feature: Finish project
    Background: 
        Given a project "20251"
        And status for project "20251" is "ongoing"
        And developer with name "thfa" is in app
        And "thfa" is logged in 
        And "thfa" has status project leader for project "20251"
        And the project "20251" has assigned "activity1"
        And "activity1" has status "finished"
        And developer with name "mahh" is in app
        And "mahh" is logged in
        And "mahh" has status developer on project "20251"
    
    Scenario: other than project leader try to change project status and fail
        When "mahh" wants to set project status to finish
        Then shows errormessage "not project leader, status change failed"
        And status for project not changed

    Scenario: project leader succesfully change project status
        When "thfa" change project status to finished
        Then status change succeed
        
    Scenario: Project leader can't change status due to activities where status isn't finished
        And "activity2" with status "ongoing" has been added to project "20251"
        When "thfa" change status for project to finished
        Then gets errormessage "Still ongoing activities, status change failed"
        And status for project not changed