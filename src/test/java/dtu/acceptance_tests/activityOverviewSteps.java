package dtu.acceptance_tests;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import dtu.app.App;
import dtu.domain.Activity;
import dtu.domain.Developer;
import dtu.domain.Project;



public class activityOverviewSteps { //Mads 


    
    private App app;
    private ArrayList<Activity> activityList;

    public activityOverviewSteps(App app, ArrayList<Activity> activityList) {
        this.app = app;
        this.activityList = activityList;
    }

//DEVELOPER has Activie or Schesculed activies
    @Given("{string} is only working on the activity {string} in the project {string}") //Mads
    public void isOnlyWorkingOnTheActivityInTheProject(String developerInitials, String activityName, String projectNumber) throws Exception {
        assertEquals(0, activityList.size()); //Check that no activty is assgined
        activityList = app.getActivitesForDeveloper(developerInitials);
        if (activityList.size() != 0)
        {
            assertEquals("Developer has a Activty:" + app.getActivitesForDeveloper(developerInitials).get(0).getName() , 0, app.getActivitesForDeveloper(developerInitials).size());
        }

        this.app.addDeveloperToActivity(projectNumber, activityName, developerInitials); 
    }

    @When("the developer {string} get an overview of all activities") //MAds
    public void theDeveloperGetAnOverviewOfAllActivitiesForTheProject(String developerInitials) throws Exception {
       this.activityList = this.app.getActivitesForDeveloper(developerInitials);
    }

    @Then("he should see the following activities: {string}") //Mads
    public void heShouldSeeTheFollowingActivities(String string) {
        // Write code here that turns the phrase above into concrete actions

        assertEquals(1, activityList.size());
        assertEquals(string, activityList.get(0).getName());
    }

//DEVELOPER HAS NO ACTIVE OR SCHESCULED ACTIVITES
    @Given("{string} doesn't have any active or scheduled activities") //Mads
    public void isDoesnTHaveAnyActiveOrScheduledActivities(String string) { 
        //Do nothing because no activty is assigned

    }
    @When("{string} get a overview")
    public void getAOverview(String string) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        this.activityList = this.app.getActivitesForDeveloper(string);
    }
    @Then("no activites is shown")
    public void noActivitesIsShown() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(0, this.activityList.size());
    }




}