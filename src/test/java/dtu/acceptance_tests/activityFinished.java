package dtu.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor;
import dtu.app.*;
import dtu.domain.*;

//Freja
public class activityFinished {
    private App app;
    private String errorMessage;

    public activityFinished(App app){
        this.app = app;
    }

    ArrayList<Activity> activityList;
    Project project;

    @Given("a project {string}")
    public void aProject(String string){
        app.createProject();
        project = app.getProject(string);
    }

    @Given("developer with name {string} is in app")
    public void developerWithNameIsInApp(String string) {
        Developer developer = new Developer(string);
        app.addDeveloper(developer);
    }

    @Given("the developer {string} is logged in")
    public void IsLoggedIntoTheApp(String string) throws Exception, ErrorMessage{
        app.login(app.getDeveloper(string));
    }

    @Given("{string} is projectleader for the project {string}")
    public void IsProjectleaderForTheProject(String string, String string2) throws Exception, ErrorMessage{
        project.assignProjectLeader(app.getDeveloper(string), app.getDeveloper(string));
    }

    @Given("project {string} has an activity with name: {string}, start week: {int}, start and year: {int}, end week: {int}")
    public void ProjectHasAnActivityWithNameStartweekStartAndYearEndweek(String string1, String string2, int int1, int int2, int int3) throws Exception, ErrorMessage{
        int[] yearPlan = {int2, int2};
        int[] weekPlan = {int1, int3};
        project.addActivity(app.getDeveloper(project.getProjectLeader()), string2, weekPlan, yearPlan );
    }

    @Given("{string} is assigned to {string}")
    public void isAssignedTo(String dev, String activity) throws Exception {
        Developer developer = app.getDeveloper(dev);
        project.getActivity(activity).addDeveloper(developer, developer);
    }

    @Given("{string} changes {string} with status: {string}")
    public void withStatusStartAndEndYearStartweekEndweek(String dev, String activity, String status) throws Exception, ErrorMessage {
        Developer developer = app.getDeveloper(dev);
        project.getActivity(activity).setStatus(status, developer);
    }

    @When("{string} sets the status as {string} for the activity {string}")
    public void changeStatusForToFinished(String dev, String status, String activity) throws Exception, ErrorMessage{
        Developer developer = app.getDeveloper(dev);
        project.getActivity(activity).setStatus(status, developer);
    }

    @Then("the activity {string} has status {string}")
    public void statusToFinishedForSucceed(String activity, String status) throws Exception, ErrorMessage {
        assertEquals(project.getActivity(activity).getStatus(), status);
    }

    @When("{string} tries to set the status as {string} for the activity {string}")
    public void TriesToSetTheStatusForToFinished(String dev, String status, String activity) throws Exception, ErrorMessage{
        Developer developer = app.getDeveloper(dev);
        try{
            project.getActivity(activity).setStatus(status, developer);
        } catch (AssertionError e) {
            errorMessage = (e.getMessage());
        }
    }

    @Then("gets errormessage {string}")
    public void GetsErrormessage(String string) throws Exception, ErrorMessage{
        assertEquals(string, errorMessage);
    }

    @Then("status for {string} not changed from {string}")
    public void statusForNotChangedFrom(String string, String string2) throws ErrorMessage{
        try{
            assertTrue(project.getActivity(string).getStatus().equals(string2));
        } catch (AssertionError e){
            errorMessage = (e.getMessage());
        }
    }

    @When("{string} tries to change status to {string} for {string}")
    public void changeStatusForInWeekToFinishedThenGetsErrormessage(String dev, String statusTo, String activity) throws Exception{
        Developer developer = app.getDeveloper(dev);
        try{
            project.getActivity(activity).setStatus(statusTo, developer);
        }  catch (AssertionError e){
            errorMessage = (e.getMessage());
        }
    }
}