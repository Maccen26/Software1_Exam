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
    App app = new App(new ArrayList<Project>(), new ArrayList<Developer>());
    String report;

    @Given("a project with projectnumber {string}")
    public void aProjectWithProjectnumber(String string) {
        app.createProject();
    }
    
    @Given("{string} is projectleader for project {string}")
    public void isProjectleaderForProject(String string, String string2) {
        ////////// UNFINISHED /////////
        // Set the project leader for the project
        // app.getProject("20251").setProjectLeader(string);
    }

    @When("{string} tries to get the report for project {string}")
    public void triesToGetTheReportForProject(String string, String string2) {
        // Get the report for the project
        report = app.getProject(string2).getReport();
    }
    @Then("get the report {string}")
    public void getTheReport(String string) {
        // Check if the report is correct
        assertTrue(report.equals(string));
    }
}
