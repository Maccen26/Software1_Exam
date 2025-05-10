package dtu.acceptance_tests;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import dtu.app.*;
import dtu.domain.*;

//Freja
public class activityFinished {
    App app;
    ArrayList<Activity> activityList;
    Developer developer;
    Project project;

    @Given("a project {string}")
    public void aProject(String string){
        app.createProject();
        project = app.getProject(string);
    }

    @Given("{string} with status {string}, start and end year: {int}, startweek: {int}, endweek: {int}")
    public void withStatus(String string, String string2, int year, int startweek, int endweek) {
        try{
            app.addActivity(project.getProjectNumber(), string, new int[] {year,year}, new int[] {startweek, endweek});
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try{
            project.getActivity(string).setStatus(string2);
        } catch (ErrorMessage e){
            System.out.println(e.getMessage());
        }
    }

    @Given("developer with name {string} is in app")
    public void developerWithNameIsInApp(String string) {
        developer = new Developer(string);
        app.addDeveloper(developer);
    }

    @Given("{string} is logged in") 
    public void isLoggedIn(String string) {
        if (app.hasDeveloper(string)) {
            developer = new Developer(string);
            try{
                app.login(developer);
            } catch (ErrorMessage e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Given("{string} is assigned to {string}")
    public void isAssignedTo(String string, String string2) {
        try{
            app.addDeveloperToActivity(project.getProjectNumber(), string2, string, string);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @When("{string} change status for {string} to finished")
    public void changeStatusForToFinished(String string, String string2) {

    }

    @Then("status change succed")
    public void statusChangeSucceed() {
    
    }

    @When("{string} change status for {string}")
    public void changeStatusFor(String string, String string2) {

    }

    @Then("gets errormessage {string}")
    public String GetsErrormessage(String string) throws ErrorMessage{
        throw new ErrorMessage(string);
    }

    @Then("status for {string} not changed")
    public void statusForNotChanged(String string) {

    }

    @When("{string} change status for {string} in week {int} to finished")
    public void changeStatusForInWeekToFinishedThenGetsErrormessage(String string, String string2, String string3, String string4) {
   
    }
}