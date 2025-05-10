package dtu.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
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
    public getReportSteps(App app) {
        this.app = app;
    }
    String errorMessage;
    ArrayList<String> report = new ArrayList<>();

    @Given("a project with projectnumber {string}")
    public void aProjectWithProjectnumber(String string) {
        app.createProject();
    }

    @Given("{string} is projectleader for project {string}")
    public void isProjectleaderForProject(String string, String string2) throws Exception {
        app.getProject(string2).assignProjectLeader(app.getLoggedInDeveloper(), app.getDeveloper(string));
    }

    @When("{string} gets the report for project {string}")
    public void triesToGetTheReportForProject(String string, String string2) throws Exception {
        try {
            report = app.getProject(string2).getReport(app.getDeveloper(string));
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("get the report")
    public void getTheReport(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> strings = dataTable.asLists();

        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < strings.get(i).size(); j++) {
                System.out.println(i + " " + j);
                System.out.println(report.get(i));
                System.out.println(strings.get(i).get(j));
                assertTrue(report.get(i).equals(strings.get(i).get(j)));
            }
        }
    }

    @Given("project {string} has an activity with name: {string}, start week: {int}, start year: {int}, end week: {int}, end year: {int} and budgetted time: {int} hours")
    public void projectHasAnActivityWithNameStartWeekStartYearEndWeekEndYearAndBudgettedTimeHours(String string, String string2, Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) throws Exception {
        app.getProject(string).addActivity(app.getLoggedInDeveloper(), string2, new int[]{int1, int3}, new int[]{int2, int4});
        app.getProject(string).getActivity(string2).setTimeBudget(app.getLoggedInDeveloper(), int5);
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
