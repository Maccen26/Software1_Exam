package dtu.acceptance_tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtu.app.App;
import dtu.domain.Developer;
import dtu.domain.ErrorMessage;
import dtu.domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class removeDeveloperFromActivitySteps {
    private App app;
    private String errorMessage;
    public removeDeveloperFromActivitySteps(App app) {
        this.app = app;
    }

    @Given("the developer with the name {string} has been added by {string} to {string} from {string}")
    public void theDeveloperWithTheNameIsOnTheActivityWithTheName(String dev, String pl, String activity, String project) throws Exception, ErrorMessage {
        Developer developer = app.getDeveloper(dev);
        Developer projectLeader = app.getDeveloper(pl);
        app.getProject(project).getActivity(activity).addDeveloper(projectLeader, developer);
    }
    
    @When("the developer with the name {string} removes {string} from {string} in project {string}")
    public void theDeveloperWithTheNameRemovedFromTheActivity(String pl, String dev, String activity, String project) throws Exception, ErrorMessage  {
        Developer developer = app.getDeveloper(dev);
        Developer projectLeader = app.getDeveloper(pl);
        app.getProject(project).getActivity(activity).removeDeveloper(projectLeader, developer);
    }
    
    @Then("the developer with the name {string} is no longer working on {string} in project {string}")
    public void theDeveloperWithTheNameIsNoLongerOnTheActivity(String dev, String activity, String project) throws Exception, ErrorMessage {
        Developer developer = app.getDeveloper(dev);
        assertTrue(!app.getProject(project).getActivity(activity).hasDeveloper(developer));
    }

    @Given("the developer with the name {string} has not added to {string} from project {string}")
    public void theDeveloperWithTheNameIsNotOnTheActivityWithTheName(String dev, String activity, String project) throws Exception, ErrorMessage{
        Developer developer = app.getDeveloper(dev);
        assertTrue(!app.getProject(project).getActivity(activity).hasDeveloper(developer));
    }

    @When("the developer with the name {string} removes {string} from the activity {string} in project {string}")
    public void theDeveloperWithTheNameRemovedFromTheActivityInProject(String pl, String dev, String activity, String project) throws Exception, ErrorMessage {
        Developer developer = app.getDeveloper(dev);
        Developer projectLeader = app.getDeveloper(pl);
        try {
            app.getProject(project).getActivity(activity).removeDeveloper(projectLeader, developer);
        } catch (ErrorMessage e) {
            errorMessage = e.getMessage();
        }
    }
    
    @Then("the following error message {string} is displayed")
    public void theFollowingErrorMessageIsDisplayed(String string) {
        assertEquals(errorMessage, string);
    }
}
