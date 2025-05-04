package dtu.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import dtu.app.*;
import dtu.domain.*;

// Lukas
public class editActivitySteps {
    App app = new App(new ArrayList<Project>(), new ArrayList<Developer>());
    Project project;
    Developer dev;
    Activity activity;

    @Given("a project with projectnumber {int}")
    public void aProjectWithProjectnumber(Integer int1) {
        // Create a project
        app.createProject();
        project = app.getProject("20251");
    }

    @Given("the project {int} has an activity with name: {string}, start week: {int}, start year: {int}, end week: {int}, end year: {int} and budgetted time: {int} hours")
    public void theProjectHasAnActivityWithNameStartWeekStartYearEndWeekEndYearAndBudgettedTimeHours(Integer int1, String string, Integer int2, Integer int3, Integer int4, Integer int5, Integer int6) {
        ///////// UNFINISHED /////////
        // Create an activity
        // project.addActivity(string, int2, int3, int4, int5, int6);
        // activity = project.getActivity(string);
        activity = new Activity(string, project.getProjectNumber(), new ArrayList<Developer>(), int6, new int[]{int2, int3}, new int[]{int4, int5});
    }

    @Given("a developer with the name {string} is contained in the app")
    public void aDeveloperWithTheNameIsContainedInTheApp(String string) {
        // Add the developer to the app
        dev = new Developer(string);
        app.addDeveloper(dev);
    }

    @Given("{string} is logged in")
    public void isLoggedIn(String string) {
        app.login(dev);
    }

    @Given("{string} is projectleader for project {int}")
    public void isProjectleaderForProject(String string, Integer int1) {
        ////////// UNFINISHED /////////
        // Set the project leader for the project
        // app.getProject("20251").setProjectLeader(string);
    }

    @When("{string} edits the budgetted time of {string} to {int} hours")
    public void editsTheBudgettedTimeOfToHours(String string, String string2, Integer int1) {
        // Edit the budgetted time of the activity
        activity.setTimeBudget(int1);
    }

    @Then("{string} has the budgetted time {int} hours")
    public void hasTheBudgettedTimeHours(String string, Integer int1) {
        // Check if the activity has the budgetted time
        assertTrue(activity.getTimeBudget() == int1);
    }
}
