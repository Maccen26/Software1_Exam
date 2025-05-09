package dtu.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addDeveloperToActivitySteps {
    @Given("A project with the number {int} is contained in the app")
    public void aProjectWithTheNumberIsContainedInTheApp(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Given("the activity with the name {string} is connected to the project with the number {int}")
    public void theActivityWithTheNameIsConnectedToTheProjectWithTheNumber(String string, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Given("a developer with the name {string} is contained in the app")
    public void aDeveloperWithTheNameIsContainedInTheApp(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Given("{string} is logged in")
    public void isLoggedIn(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Given("{string} has projectleader permission for the project with the number {int}")
    public void hasProjectleaderPermissionForTheProjectWithTheNumber(String string, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @When("the developer with the name {string} adds {string} to the activity")
    public void theDeveloperWithTheNameAddsToTheActivity(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("the developer with the name {string} is not added to the acticity")
    public void theDeveloperWithTheNameIsNotAddedToTheActicity(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("the error message {string} is displayed")
    public void theErrorMessageIsDisplayed(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
