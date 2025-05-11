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


public class removeActivitySteps { //JOHAN - EVERY METHOD IN THIS CLASS

    private App app;
    private String errorMessage;

    public removeActivitySteps(App app) {
        this.app = app;
    }

    @Given("the App contains a project with the project number {string}")
    public void theAppContainsAProjectWithTheProjectNumber(String string) {
        app.createProject();
    }
    @Given("{string} is the projectleader of project {string}")
    public void IsTheProjectleaderOfProject(String string, String string2) throws Exception, ErrorMessage{
        Project project = app.getProject(string2);
        Developer developer = app.getDeveloper(string);
        project.assignProjectLeader(developer, developer);
    }
    @Given("the developer {string} has added the activity with the title {string} to the project {string}")
    public void theDeveloperHasAddedTheActivityWithTheTitleToTheProject(String string, String string2, String string3) throws Exception, ErrorMessage{
        int[] weekPlan = {1, 2};
        int[] yearPlan = {2025, 2025};
        Developer developer = app.getDeveloper(string);
        app.getProject(string3).addActivity(developer, string2, weekPlan, yearPlan);
        
    }
    @When("the developer {string} deletes the activity with the title {string} from project {string}")
    public void theDeveloperDeletesTheActivityWithTheTitleFromProject(String string, String string2, String string3) throws Exception, ErrorMessage{
        Developer developer = app.getDeveloper(string);
        app.getProject(string3).removeActivity(developer, string2);
    }
    @Then("the activity with the title {string} no longer exists in project {string}")
    public void theActivityWithTheTitleNoLongerExistsInProject(String string, String string2) throws Exception{
        Project project = app.getProject(string2);
        assertFalse(project.containsActivityName(string));
    }

    @Given("the developer {string} is the projectleader of project {string}")
    public void theDeveloperIsTheProjectleaderOfProject(String string, String string2) throws Exception, ErrorMessage{
        Project project = app.getProject(string2);
        Developer developer = app.getDeveloper(string);
        Developer projectleader = app.getDeveloper(project.getProjectLeader());
        project.assignProjectLeader(projectleader, developer);
    }

    @Given("developer {string} has added the activity with the title {string} to project {string}")
    public void developerHasAddedTheActivityWithTheTitleToProject(String string, String string2, String string3) throws Exception, ErrorMessage{
        int[] weekPlan = {1, 2};
        int[] yearPlan = {2025, 2025};
        Developer developer = app.getDeveloper(string);
        app.getProject(string3).addActivity(developer, string2, weekPlan, yearPlan);
    }

    @When("the developer {string} tries to delete the activity with the title {string} from project {string}")
    public void theDeveloperTriesToDeleteTheActivityWithTheTitleFromProject(String string, String string2, String string3) throws Exception, ErrorMessage{
        Developer developer = app.getDeveloper(string);
        try {
            app.getProject(string3).removeActivity(developer, string2);
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String string) {
        assertEquals(errorMessage, string);
    }
    


}
