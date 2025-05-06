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
    App app = new App();
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
        try {
            app.login(dev);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @When("the developer {string} creates a project")
    public void theDeveloperCreatesAProjectWithTheName(String string) {
        // Create a project
        app.createProject();
    }

    @Then("the project with the number {string} exists in the app")
    public void theProjectWithTheNameExistsInTheApp(String string) {
        // Check if the project exists in the app
        assertTrue(app.getProject(string) != null);
    }

    @Then("the project with the number {string} and {string} exists in the app")
    public void theErrorMessageIsGiven(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(app.getProject(string) != null && app.getProject(string2) != null);
    }
}
