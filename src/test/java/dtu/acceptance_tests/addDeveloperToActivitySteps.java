package dtu.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import dtu.app.App;
import dtu.domain.Activity;
import dtu.domain.Developer;
import dtu.domain.ErrorMessage;
import dtu.domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addDeveloperToActivitySteps {

    private App app;
    private String errorMessage;

    public addDeveloperToActivitySteps(App app) {
        this.app = app;
    }

    @Given("A project with the number {string} is contained in the app")
    public void aProjectWithTheNumberIsContainedInTheApp(String string) {
        app.createProject();
    }
    
    @Given("the activity with the name {string} is connected to the project with the number {string}")
    public void theActivityWithTheNameIsConnectedToTheProjectWithTheNumber(String string1, String string2) throws Exception{
        int[] weekPlan = {1, 2};
        int[] yearPlan = {2025, 2025};
        app.getProject(string2).addActivity(app.getLoggedInDeveloper(), string1, weekPlan, yearPlan);
    }
    
    @Given("a developer with the name {string} is contained in the app")
    public void aDeveloperWithTheNameIsContainedInTheApp(String string) {
        Developer developer = new Developer(string);
        app.addDeveloper(developer);
    }
    
    @Given("{string} is logged in")
    public void isLoggedIn(String string) throws Exception{
        Developer developer = app.getDeveloper(string);
        app.login(developer);
    }
    
    @Given("{string} has projectleader permission for the project with the number {string}")
    public void hasProjectleaderPermissionForTheProjectWithTheNumber(String string1, String string2) throws Exception{
        Project project = app.getProject(string2);
        Developer developer = app.getDeveloper(string1);
        project.assignProjectLeader(developer, developer);
    }
    
    @When("the developer with the name {string} adds {string} to {string} from project {string}")
    public void theDeveloperWithTheNameAddsTo(String string1, String string2, String string3, String string4) throws Exception{
        Developer projectLeader = app.getDeveloper(string1);
        Developer developer = app.getDeveloper(string2);
        Project project = app.getProject(string4);
        Activity activity = project.getActivity(string3);
        activity.addDeveloper(projectLeader, developer);
    }
    
    @Then("the developer with the name {string} is added to the {string} from project {string}")
    public void theDeveloperWithTheNameIsAddedToTheActivity(String string1, String string2, String string3) throws ErrorMessage, Exception{
        Project project = app.getProject(string3);
        Activity activity = project.getActivity(string2);
        Developer developer = app.getDeveloper(string1);
        assertTrue(activity.hasDeveloper(developer));
    }

    @Given("the developer with the name {string} already has been added by {string} activity with the name {string} from project {string}")
    public void theDeveloperWithTheNameAddsIsOnTheActivityWithTheNameFromProject(String string1, String string2, String string3, String string4) throws ErrorMessage, Exception{
        Developer projectLeader = app.getDeveloper(string2);
        Developer developer = app.getDeveloper(string1);
        Project project = app.getProject(string4);
        Activity activity = project.getActivity(string3);
        activity.addDeveloper(projectLeader, developer);
    }

    @When("the developer with the name {string} tries to add {string} to {string} from project {string}")
    public void theDeveloperWithTheNameAddsAgain(String string1, String string2, String string3, String string4) throws Exception{
        Developer projectLeader = app.getDeveloper(string1);
        Developer developer = app.getDeveloper(string2);
        Project project = app.getProject(string4);
        Activity activity = project.getActivity(string3);
        try {
            activity.addDeveloper(projectLeader, developer);
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the error message {string} is displayed")
    public void theErrorMessageIsDisplayed(String string) {
        assertEquals(errorMessage, string);
    }
}
