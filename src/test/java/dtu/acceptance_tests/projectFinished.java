package dtu.acceptance_tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import dtu.app.*;
import dtu.domain.*;

//Freja 
public class projectFinished {
    App app;
    
    public projectFinished(App app){
        this.app = app;
    }

    private String errorMessage;

    @Given("a project with the name {string}")
    public void aProjectNamed(String string){
        app.createProject();
    }

    @Given("developer named {string} is in app")
    public void developerNamedIsInApp(String string) {
        Developer developer = new Developer(string);
        app.addDeveloper(developer);
    }

    @Given("and the developer {string} is logged in")
    public void IsLoggedIn(String string) throws Exception, ErrorMessage{
        app.login(app.getDeveloper(string));
    }

    @Given("{string} has status project leader for project {string}")
    public void HasStatusProjectLeaderForProject(String dev, String proj) throws ErrorMessage, Exception{
        Developer developer = app.getDeveloper(dev);
        app.getProject(proj).assignProjectLeader(developer,developer);
    }

    @Given("status for project {string} is {string}")
    public void StatusForProjectIs(String proj, String status) throws Exception{
        Developer developer = app.getDeveloper(app.getProject(proj).getProjectLeader());
        app.getProject(proj).setStatus(status, developer);
    }

    @Given("the project {string} has assigned {string}, start week: {int}, start and year: {int}, end week: {int}")
    public void TheProjectHasAssignedStartweekStartAndYearEndweek(String proj, String activity, int int1, int int2, int int3) throws Exception, ErrorMessage{
        Developer developer = app.getDeveloper(app.getProject(proj).getProjectLeader());
        int[] yearPlan = {int2, int2};
        int[] weekPlan = {int1, int3};
        app.getProject(proj).addActivity(developer, activity, weekPlan, yearPlan );
    }

    @Given("{string} has status developer on project {string} for {string}")
     public void HasStatusDeveloperOnProject(String dev, String proj, String activity)throws Exception{
        Developer developer = app.getDeveloper(app.getProject(proj).getProjectLeader());
        Project project = app.getProject(proj);
        project.addDeveloperToActivity(activity, developer, developer);
    }

    @Given("{string} in project {string} has status {string}")
    public void InProjectHasStats(String activity, String proj, String status) throws Exception{
        Project project = app.getProject(proj);
        Developer developer = project.getActivity(activity).getAssignedDevelopers().get(0);
        project.getActivity(activity).setStatus("ongoing", developer); //has to be "ongoing" before Finished
        project.getActivity(activity).setStatus(status, developer);
    }

    @When("{string} wants to set status to {string} for project {string}")
    public void WantsToSetProjectStatusToFinish(String dev, String status, String proj)throws Exception{
        Developer developer = app.getDeveloper(dev);
        Project project = app.getProject(proj);
        try{
            project.setStatus(status, developer);
        } catch (Exception e) {
            errorMessage = (e.getMessage());
        }
    }

    @Then("shows errormessage {string}")
    public void GetsErrormessage(String string) throws Exception, ErrorMessage{
        assertEquals(string, errorMessage);
    }

    @Then("status for project {string} not changed from {string}")
    public void StatusforProjectNotChangedFrom(String proj, String status){
        Project project = app.getProject(proj);
        project.getStatus().equals(status);
    }
    
    @When("{string} change project {string} status to {string}")
    public void ChangeProjectStatusTo(String dev,String proj, String status){
        Project project = app.getProject(proj);
        try{
            Developer developer = app.getDeveloper(dev);
            project.setStatus(status, developer);
        } catch (Exception e) {
            errorMessage = (e.getMessage());
        }
    }

    @Then("status for project {string} changed to {string}")
    public void StatusForProjectChangedTo(String proj, String status) {
        Project project = app.getProject(proj);
        assertEquals(project.getStatus(), status);
    }
}