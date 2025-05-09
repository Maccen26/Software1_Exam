#Mads HH 

Feature: App setup with Developers, Projects and activities

Scenario: Setting up Projects
    Given project "20251" and "20252" exists
    Then project "20251" and "20252" is contained within the app 


Scenario: Setting up Developers
    Given the developers "huba" "mahh" and "thfa" is in the app
    Then the developers "huba" "mahh" and "thfa" are contained in the app 


Scenario: Setting up Activities 
    Given project "20251" and "20252" exists 
    And the developers "huba" "mahh" and "thfa" is in the app 
    And "thfa" is project leader on project "20251"
    And "thfa" is logged into the app
    And the activity with name "Activity1", under project "20251" with startweek 10, endweek 15 and startyear 2025, endyear 2025
    Then the activity "Activity1" exist in the project "20251" 
