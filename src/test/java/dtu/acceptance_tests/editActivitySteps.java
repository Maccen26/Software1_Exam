package dtu.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import dtu.app.*;
import dtu.domain.*;

// Lukas
public class editActivitySteps {
    App app = new App();
    Project project = app.getProject("20250");
    Activity activity;
    Developer dev;
    String errorMessage;

    @Given("a developer with the name {string} is contained in the app")
    public void aDeveloperWithTheNameIsContainedInTheApp(String string) {
        // Add the developer to the app
        dev = new Developer(string);
        app.addDeveloper(dev);
    }

    @Given("{string} is logged in")
    public void isLoggedIn(String string) {
        try {
            // Log in the developer
            app.login(dev);
        } catch (Exception e) {
            errorMessage = e.getMessage();
            System.out.println(e.getMessage());
        }
    }

    @When("{string} edits the budgetted time of {string} to {int} hours")
    public void editsTheBudgettedTimeOfToHours(String string, String string2, Integer int1) {
        try{
            activity = app.getProject("20250").getActivity(string2);
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            System.out.println(errorMessage);
            System.out.println(string);
            return;
        }
        
        try{
            activity.setTimeBudget(app.getDeveloper(string), int1);
        }
        catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

    @Then("{string} has the budgetted time {int} hours")
    public void hasTheBudgettedTimeHours(String string, Integer int1) {
        // Check if the activity has the budgetted time
        assertTrue(activity.getTimeBudget() == int1);
    }

    @When("{string} edits the start week of {string} to {int} in {int}")
    public void editsTheStartWeekOfToIn(String string, String string2, Integer int1, Integer int2) {
        try{
            activity = app.getProject("20250").getActivity(string2);
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }

        // Edit the start week of the activity
        int[] weekPlan = activity.getWeekPlan();
        weekPlan[0] = int1;
        try{
            activity.setWeekPlan(app.getDeveloper(string), weekPlan);
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }
    @When("{string} edits the end week of {string} to {int} in {int}")
    public void editsTheEndWeekOfToIn(String string, String string2, Integer int1, Integer int2) {
        try{
            activity = app.getProject("20250").getActivity(string2);
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }

        // Edit the end week of the activity
        int[] weekPlan = activity.getWeekPlan();
        weekPlan[1] = int1;
        try{
            activity.setWeekPlan(app.getDeveloper(string), weekPlan);
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }
    @Then("{string} has the start week {int} and end week {int} in {int}")
    public void hasTheStartWeekAndEndWeekIn(String string, Integer int1, Integer int2, Integer int3) {
        // Check if the activity has the start week and end week
        int[] weekPlan = activity.getWeekPlan();
        assertTrue(weekPlan[0] == int1 && weekPlan[1] == int2);
    }

    @Then("recieve the error message {string}")
    public void recieveTheErrorMessage(String string) {
        // Check if the error message is correct
        System.out.println("\nRecieved: " + errorMessage + " " + string + "\n");
        System.out.println(errorMessage.equals(string));
        assertTrue(errorMessage.equals(string));
    }
}
