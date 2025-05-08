package dtu.acceptance_tests;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    @Given("{string} is only working on the activity {string} in the project {string}")
    public void isOnlyWorkingOnTheActivityInTheProject(String developerInitials, String activityName, String projectNumber) throws Exception {
        this.app.addDeveloperToActivity(projectNumber, activityName, developerInitials); 
    }

    @When("the developer {string} get an overview of all activities")
    public void theDeveloperGetAnOverviewOfAllActivitiesForTheProject(String developerInitials) throws Exception {
       this.activityList = this.app.getActivitesInProject(developerInitials);
    }

    @Then("he should see the following activities: {string}")
    public void heShouldSeeTheFollowingActivities(String string) {
        // Write code here that turns the phrase above into concrete actions

        assertEquals(1, activityList.size());
        assertEquals(string, activityList.get(0).getName());
    }



}