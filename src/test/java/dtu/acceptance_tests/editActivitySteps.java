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
    App app;

    public editActivitySteps(App app) {
        this.app = app;
    } 

    String errorMessage;

    @When("{string} edits the budgetted time of {string} to {int} hours")
    public void editsTheBudgettedTimeOfToHours(String string, String string2, Integer int1) throws Exception {
        try{
            app.getProject("20251").getActivity(string2).setTimeBudget(app.getDeveloper(string), int1);
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            return;
        }
    }

    @Then("{string} has the budgetted time {int} hours")
    public void hasTheBudgettedTimeHours(String string, Integer int1) throws ErrorMessage{
        assertTrue(app.getProject("20251").getActivity(string).getTimeBudget() == int1);
    }

    @When("{string} edits the start week of {string} to {int} in {int}")
    public void editsTheStartWeekOfToIn(String string, String string2, Integer int1, Integer int2) throws Exception {
        try{
            app.getProject("20251").getActivity(string2).setWeekPlan(app.getDeveloper(string), new int[]{int1, app.getProject("20251").getActivity(string2).getWeekPlan()[1]});
            app.getProject("20251").getActivity(string2).setYearPlan(app.getDeveloper(string), new int[]{int1, app.getProject("20251").getActivity(string2).getYearPlan()[1]});
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }
    @When("{string} edits the end week of {string} to {int} in {int}")
    public void editsTheEndWeekOfToIn(String string, String string2, Integer int1, Integer int2) throws Exception {
        try{
            app.getProject("20251").getActivity(string2).setWeekPlan(app.getDeveloper(string), new int[]{app.getProject("20251").getActivity(string2).getWeekPlan()[0], int1});
            app.getProject("20251").getActivity(string2).setYearPlan(app.getDeveloper(string), new int[]{app.getProject("20251").getActivity(string2).getYearPlan()[0], int1});
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }
    
    @Then("{string} has the start week {int} and end week {int} in {int}")
    public void hasTheStartWeekAndEndWeekIn(String string, Integer int1, Integer int2, Integer int3) throws ErrorMessage{
        // Check if the activity has the start week and end week
        int[] weekPlan = app.getProject("20251").getActivity(string).getWeekPlan();
        assertTrue(weekPlan[0] == int1 && weekPlan[1] == int2);
    }

    @Then("recieve the error message {string}")
    public void recieveTheErrorMessage(String string) {
        assertTrue(errorMessage.equals(string));
    }
}
