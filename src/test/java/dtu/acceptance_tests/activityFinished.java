package dtu.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import dtu.app.*;
import dtu.domain.*;

//Freja
public class activityFinished {
    App app;

    public activityFinished(App app){
        this.app = app;
    }

    ArrayList<Activity> activityList;
    Developer developer;
    Project project;

    @Given("a project {string}")
    public void aProject(String string){
        app.createProject();
        project = app.getProject(string);
    }

    @Given("{string} changes {string} with status: {string}")
    public void withStatusStartAndEndYearStartweekEndweek(String dev, String string, String string2) throws Exception {
        try{
            project.getActivity(string).setStatus(string2,app.getDeveloper(dev));
            System.out.println("Changed status to " + project.getActivity(string).getStatus());
        } catch (ErrorMessage e){
            System.out.println(e.getMessage());
        }
    }

    @Given("developer with name {string} is in app")
    public void developerWithNameIsInApp(String string) {
        developer = new Developer(string);
        app.addDeveloper(developer);
    }

    @Given("{string} is assigned to {string}")
    public void isAssignedTo(String string, String string2) {
        try{
            project.getActivity(string).addDeveloper(developer, developer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @When("{string} change status for {string} to finished")
    public void changeStatusForToFinished(String string, String string2) throws Exception{
        try{
            project.getActivity(string2).setStatus("Finished", app.getDeveloper(string));
        }  catch (ErrorMessage e){
            System.err.println(e.getMessage());
        }
    }

    @Then("status to finished for {string} succeed")
    public void statusToFinishedForSucceed(String string) {
        try{
            assertTrue(project.getActivity(string).getStatus().equals("Finished"));
        } catch (ErrorMessage e){
            System.err.println(e.getMessage());
        }
    }

    @When("{string} change status for {string}")
    public void changeStatusFor(String string, String string2) throws Exception{
        try{
            project.getActivity(string2).setStatus("Ongoing", app.getDeveloper(string));
        }  catch (ErrorMessage e){
            System.err.println(e.getMessage());
        }
    }

    @Then("gets errormessage {string}")
    public void GetsErrormessage(String string) throws ErrorMessage{
        // løser til sidste
    }

    @Then("status for {string} not changed from {string}")
    public void statusForNotChangedFrom(String string, String string2) {
        try{
            assertTrue(project.getActivity(string).getStatus().equals(string2));
        } catch (ErrorMessage e){
            System.err.println(e.getMessage());
        }
    }

    @When("{string} change status for {string} in week {int} to finished")
    public void changeStatusForInWeekToFinishedThenGetsErrormessage(String string, String string2, int int1) throws Exception{
        try{
            project.getActivity(string2).setStatus("Finished", app.getDeveloper(string));
        }  catch (ErrorMessage e){
            System.err.println(e.getMessage());
        }
    }
}