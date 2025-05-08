package dtu.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
    String errorMessage;

    @Given("a project with projectnumber {string}")
    public void aProjectWithProjectnumber(String string) {
        app.createProject();
        project = app.getProject(string);
    }

    @Given("{string} is projectleader for project {string}")
    public void isProjectleaderForProject(String string, String string2) throws Exception {
        // Set the project leader for the project
        try {
            app.getProject(string2).assignProjectLeader(app.getDeveloper(string), app.getDeveloper(string));
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

    @When("{string} gets the report for project {string}")
    public void triesToGetTheReportForProject(String string, String string2) throws Exception {
        // Get the report for the project
        try {
            report = app.getProject(string2).getReport(app.getDeveloper(string));
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

    @Then("get the report {string}")
    public void getTheReport(String string) {
        System.out.println(report);
        // Check if the report is correct
        assertTrue(report.equals(string));
    }

    @Then("get the report")
    public void getTheReport(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> strings = dataTable.asLists();
        // Convert the DataTable to a string
        String string = "";
        for (List<String> list : strings) {
            for (String string2 : list) {
                string += string2 + "\n";
            }
        }
        // Remove the last newline character
        string = string.substring(0, string.length() - 1);


        System.out.println(report);
        
        // Check if the report is correct
        assertTrue(report.equals(string));
    }

    @Given("project {string} has an activity with name: {string}, start week: {int}, start year: {int}, end week: {int}, end year: {int} and budgetted time: {int} hours")
    public void projectHasAnActivityWithNameStartWeekStartYearEndWeekEndYearAndBudgettedTimeHours(String string, String string2, Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) throws Exception {
        // Create an activity
        try {
            app.getProject(string).addActivity(app.getDeveloper("thfa"), string2, new int[] {int1, int2}, new int[] {int3, int4});
            activity = project.getActivity(string);    
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
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
