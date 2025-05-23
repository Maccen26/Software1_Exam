#Thomas
Feature: Remove a developer from an activity
	Description: A developer is removed from an activity on a project by a projectleader 
	by providing the specific developer name and activity they are being removed from. 
	Actors: Developer (with projectleader permissions)

Background: A project has a set of activities, activities contain a set of developers
    Given A project with the number "20251" is contained in the app
    And a developer with the name "thfa" is contained in the app
    And a developer with the name "mahh" is contained in the app
    And "thfa" is logged in
    And "thfa" has projectleader permission for the project with the number "20251"
    And the activity with the name "Activity1" is connected to the project with the number "20251"
	
Scenario: Projectleader succefully removes a developer from an activity
	Given the developer with the name "mahh" has been added by "thfa" to "Activity1" from "20251"
	When the developer with the name "thfa" removes "mahh" from "Activity1" in project "20251"
	Then the developer with the name "mahh" is no longer working on "Activity1" in project "20251"
	
Scenario: Projectleader removes a developer from an activity they are not working on
	Given the developer with the name "mahh" has not added to "Activity1" from project "20251"
	When the developer with the name "thfa" removes "mahh" from the activity "Activity1" in project "20251"
	Then the following error message "Developer is not working on activity" is displayed