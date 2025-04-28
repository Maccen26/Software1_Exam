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
    ArrayList<Project> projects = new ArrayList<Project>();
    ArrayList<Developer> developers = new ArrayList<Developer>();
    App app = new App(projects, developers);
    Project project;
    Developer dev;


    @Given("the App contains a developer with the initials {string}")
    public void theAppContainsADeveloperWithTheInitials(String string) {
        // Add the developer to the app
        dev = new Developer(string);
        app.addDeveloper(dev);
    }

    @Given("the developer {string} is logged into the App")
    public void theDeveloperIsLoggedIntoTheApp(String string) {
        // Log in the developer
        app.login(dev);
    }

    @When("the developer {string} creates a project with the name {string}")
    public void theDeveloperCreatesAProjectWithTheName(String string, String string2) {
        // Create a project
        project = new Project(string2);
        app.addProject(project);
    }

    @Then("the project with the name {string} exists in the app")
    public void theProjectWithTheNameExistsInTheApp(String string) {
        // Check if the project exists in the app
        assertTrue(app.getProject(string) != null);
    }

    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
