package dtu.acceptance_tests;

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
    App app = new App();

    @Given("A project with the number {string} is contained in the app")
    public void aProjectWithTheNumberIsContainedInTheApp(String string) {
        app.createProject();
    }
    
    @Given("the activity with the name {string} is connected to the project with the number {string}")
    public void theActivityWithTheNameIsConnectedToTheProjectWithTheNumber(String string1, String string2) throws Exception{
        int[] weekPlan = {1, 2};
        int[] yearPlan = {2025, 2025};
        app.addActivity(string1, string2, weekPlan, yearPlan);
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
        throw new io.cucumber.java.PendingException();
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
    public void theDeveloperWithTheNameIsAddedToTheActivity(String string1, String string2, String string3) throws ErrorMessage{
        Project project = app.getProject(string3);
        Activity activity = project.getActivity(string2);
        Developer developer = new Developer(string1);
        assertTrue(activity.hasDeveloper(developer));
    }

    @Given("the developer with the name {string} is on activity with the name {string} from project {string}")
    public void theDeveloperWithTheNameAddsIsNotOnTheActivityWithTheNameFromProject(String string1, String string2, String string3) throws ErrorMessage{
        //Project project = app.getProject(string3);
        //Activity activity = project.getActivity(string2);

        //activity.addDeveloper(requester, developer);
    }
    @When("the developer with the name {string} tries to add {string} to {string} from project {string}")
    public void theDeveloperWithTheNameAddsAgain(String string1, String string2, String string3, String string4) throws Exception{
        //Developer projectLeader = app.getDeveloper(string1);
        //Developer developer = app.getDeveloper(string2);
        //Project project = app.getProject(string4);
        //Activity activity = project.getActivity(string3);
        //activity.addDeveloper(projectLeader, developer);
    }
    
    @Then("the developer with the name {string} is not added to {string} from project {string}")
    public void theDeveloperWithTheNameIsNotAddedToTheActivity(String string1, String string2, String string3) throws ErrorMessage{
        //Project project = app.getProject(string3);
        //Activity activity = project.getActivity(string2);
        //Developer developer = new Developer(string1);
        //assertTrue(!activity.hasDeveloper(developer));
    }

    @Then("the error message {string} is displayed")
    public void theErrorMessageIsDisplayed(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
