package dtu.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import dtu.app.App;
import dtu.domain.Activity;
import dtu.domain.Developer;
import dtu.domain.ErrorMessage;
import dtu.domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;

public class TimeRecordingSteps {

    private App app;
    private Developer developer;
    private String errorMessage;

    public TimeRecordingSteps(App app)
    {
        this.app = app;

    }

    @Given("the activity {string} from the project {string}")
    public void theActivityFromTheProject(String activityName, String projectNumber) throws ErrorMessage {
        try {
            Project project = app.getProject(projectNumber); 
            project.getActivity(activityName);
        } catch (ErrorMessage e){
            assertTrue(e.getMessage(), false);
        }
    }
    @When("{string} register {double} hour on the activity {string} from the project {string}")
    public void registerHourOnTheActivityFromTheProject(String developerInitials, Double time, String activityName, String projectNumber) { 
        
        try {
        Project project = app.getProject(projectNumber); 
        Activity activityApp = project.getActivity(activityName); 
        this.developer = app.getDeveloper(developerInitials);
        activityApp.registerTime(time, this.developer); 

        } catch (Exception e){
            this.errorMessage = e.getMessage();
        }
    }
    @Then("{double} hour is registered on {string} in the project {string} for {string}")
    public void hourIsRegisteredOnInTheProject(Double time, String activityName, String projectNumber, String developerInitials) throws Exception {
        Project project = app.getProject(projectNumber); 
        Activity activity = project.getActivity(activityName); 
        Developer developer = app.getDeveloper(developerInitials);
        assertEquals(activity.getRegisteredTime(developer),time);
        

    }


    @When("{string} add {double} hours to {string} in the project {string}")
    public void addHoursInTheProject(String developerInitials, Double time, String activtyName, String projectNumber) {
        try {
            Project project = app.getProject(projectNumber); 
            Activity activity = project.getActivity(activtyName); 
            Developer developer = app.getDeveloper(developerInitials); 
            activity.registerTime(time, developer);
        } catch (Exception e){
            this.errorMessage = e.getMessage();
        }
    }

    @Then("an error message is given to the Developer: {string}")
    public void anErrorMessageIsGivenToTheDeveloper(String errorMessage) {
        assertEquals(this.errorMessage, errorMessage);
    }







    
}
