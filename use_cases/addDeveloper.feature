Feature: Add developer to an activity
  Description: A developer is added to an activity on a project by a projectleader 
  by providing the specific developer name and activity they will be working on. 
  Actors: Developer (with projectleader permissions)

Background: A project has a set of activities, activities contain a set of developers
    Given A project with the number "20251" is contained in the app
    And a developer with the name "thfa" is contained in the app
    And a developer with the name "mahh" is contained in the app
    And "thfa" is logged in
    And "thfa" has projectleader permission for the project with the number "20251"
    And the activity with the name "Activity1" is connected to the project with the number "20251"

Scenario: Projectleader succefully adds a developer to an activity
	   When the developer with the name "thfa" adds "mahh" to "Activity1" from project "20251"
	   Then the developer with the name "mahh" is added to the "Activity1" from project "20251"
	
Scenario: Projectleader adds a developer to an activity they already are working on
     	Given the developer with the name "mahh" already has been added by "thfa" activity with the name "Activity1" from project "20251"
	    When the developer with the name "thfa" tries to add "mahh" to "Activity1" from project "20251"
	    And the error message "Developer already working on activity" is displayed