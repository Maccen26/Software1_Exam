package dtu.acceptance_tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;


import dtu.app.App;
import dtu.domain.*;
import io.cucumber.java.en.*;
import io.cucumber.java.hu.De;

public class addActivitySteps { //JOHAN - EVERY METHOD IN THIS CLASS
    private App app;
    private String errorMessage;

    public addActivitySteps(App app) {
        this.app = app;
    }

    @Given("developer {string} is the projectleader of project {string}")
    public void developerIsTheProjectleaderOfProject(String string, String string2) throws Exception, ErrorMessage{
        Project project = app.getProject(string2);
        Developer developer = app.getDeveloper(string);
        project.assignProjectLeader(developer, developer);
    }

    @When("the developer {string} adds an activity with the title {string}, start week {int}, start year {int}, end week {int}, end year {int} to project {string}")
    public void theDeveloperAddsAnActivityWithTheTitleStartWeekStartYearEndWeekEndYearToProject(String string, String string2, Integer int1, Integer int2, Integer int3, Integer int4, String string3) throws Exception, ErrorMessage{
        int[] weekPlan = {int1, int3};
        int[] yearPlan = {int2, int4};
        Developer developer = app.getDeveloper(string);
        app.getProject(string3).addActivity(developer, string2, weekPlan, yearPlan);
    }

    @Then("the activity with the title {string} exists in project {string}")
    public void theActivityWithTheTitleExistsInProject(String string, String string2) {
        Project project = app.getProject(string2);
        assertTrue(project.containsActivityName(string));
    }
    
    
    @When("the developer {string} adds the activity with the title {string}, start week {int}, start year {int}, end week {int}, end year {int} to project {string}")
    public void theDeveloperAddsTheActivityWithTheTitleStartWeekStartYearEndWeekEndYearToProject(String string, String string2, Integer int1, Integer int2, Integer int3, Integer int4, String string3) throws Exception, ErrorMessage{
        int[] weekPlan = {int1, int3};
        int[] yearPlan = {int2, int4};
        Developer developer = app.getDeveloper(string);
        try {
            app.getProject(string3).addActivity(developer, string2, weekPlan, yearPlan);
        } catch (AssertionError e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("the developer {string} has added the activity with the title {string}, start week {int}, start year {int}, end week {int}, end year {int} to project {string}")
    public void theDeveloperHasAddedTheActivityWithTheTitleStartWeekStartYearEndWeekEndYearToProject(String string, String string2, Integer int1, Integer int2, Integer int3, Integer int4, String string3) throws Exception, ErrorMessage{
        int[] weekPlan = {int1, int3};
        int[] yearPlan = {int2, int4};
        Developer developer = app.getDeveloper(string);
        app.getProject(string3).addActivity(developer, string2, weekPlan, yearPlan);
    }

    @When("{string} adds the activity with the title {string}, start week {int}, start year {int}, end week {int}, end year {int} to project {string}")
    public void addsTheActivityWithTheTitleStartWeekStartYearEndWeekEndYearToProject(String string, String string2, Integer int1, Integer int2, Integer int3, Integer int4, String string3) throws Exception, ErrorMessage{
        int[] weekPlan = {int1, int3};
        int[] yearPlan = {int2, int4};
        Developer developer = app.getDeveloper(string);
        try {
            app.getProject(string3).addActivity(developer, string2, weekPlan, yearPlan);
        } catch (AssertionError e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the error message {string} is then given")
    public void theErrorMessageIsThenGiven(String string) {
        assertEquals(string, errorMessage);
    }

}
