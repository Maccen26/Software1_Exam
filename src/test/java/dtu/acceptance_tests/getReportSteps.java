package dtu.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import dtu.app.*;
import dtu.domain.*;

// Lukas
public class getReportSteps {
    App app = new App();
    String report;
    Project project;
    Activity activity;

    @Given("a project with projectnumber {string}")
    public void aProjectWithProjectnumber(String string) {
        app.createProject();
        project = app.getProject(string);
    }

    @Given("{string} is projectleader for project {string}")
    public void isProjectleaderForProject(String string, String string2) {
        ////////// UNFINISHED /////////
        // Set the project leader for the project
        // app.getProject("20251").setProjectLeader(string);
    }

    @When("{string} gets the report for project {string}")
    public void triesToGetTheReportForProject(String string, String string2) {
        // Get the report for the project
        report = app.getProject(string2).getReport();
    }

    @Then("get the report {string}")
    public void getTheReport(String string) {
        // Check if the report is correct
        assertTrue(report.equals(string));
    }

    @Given("project {string} has an activity with name: {string}, start week: {int}, start year: {int}, end week: {int}, end year: {int} and budgetted time: {int} hours")
    public void projectHasAnActivityWithNameStartWeekStartYearEndWeekEndYearAndBudgettedTimeHours(String string, String string2, Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) {
        ///////// UNFINISHED /////////
        // Create an activity
        // project.addActivity(string, int2, int3, int4, int5, int6);
        // activity = project.getActivity(string);
        activity = new Activity(string, project.getProjectNumber(), new ArrayList<Developer>(), int5, new int[]{int1, int2}, new int[]{int3, int4});
    
    }

    @Given("{string} has registered {int} hours on {string}")
    public void hasRegisteredHoursOn(String string, Integer int1, String string2) {
        ////////// UNFINISHED /////////
        // Register the hours for the activity
        // dev.registerHours(activity, int1);
    }

    @Given("the activity is finished")
    public void theActivityIsFinished() {
        ////////// UNFINISHED /////////
        // Set the status of the activity to finished
        // activity.finish();
    }
}
