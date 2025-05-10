package dtu.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import dtu.app.App;
import dtu.domain.Developer;
import dtu.domain.Project;
import dtu.domain.Activity;

// Lukas
public class createProjectSteps {
    App app;
    public createProjectSteps(App app)
    {
        this.app = app; 
    }

    @Given("the App contains a developer with the initials {string}")
    public void theAppContainsADeveloperWithTheInitials(String string) {
        app.addDeveloper(new Developer(string));
    }

    @Given("the developer {string} is logged into the App")
    public void theDeveloperIsLoggedIntoTheApp(String string) throws Exception {
        app.login(app.getDeveloper(string));
    }

    @When("the developer {string} creates a project")
    public void theDeveloperCreatesAProjectWithTheName(String string) {
        app.createProject();
    }

    @Then("the project with the number {string} exists in the app")
    public void theProjectWithTheNameExistsInTheApp(String string) throws Exception {
        assertTrue(app.getProject(string) != null);
    }

    @Then("the project with the number {string} and {string} exists in the app")
    public void theErrorMessageIsGiven(String string, String string2) throws Exception {
        assertTrue(app.getProject(string) != null && app.getProject(string2) != null);
    }
}
