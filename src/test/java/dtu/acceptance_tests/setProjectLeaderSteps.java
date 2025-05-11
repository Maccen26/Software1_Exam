package dtu.acceptance_tests;

import static org.junit.Assert.assertEquals;

import dtu.app.App;
import dtu.domain.Developer;
import dtu.domain.ErrorMessage;
import dtu.domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class setProjectLeaderSteps { //Mads


    private App app; 
    private String errorMessage;
    
    public setProjectLeaderSteps(App app){
        this.app = app; 
    }

    @Given("no projectleader is assigned to project {string}") //mads
    public void noProjectleaderIsAssignedToProject(String projectNumber) {
        Project project = this.app.getProject(projectNumber); 
        assertEquals(null, project.getProjectLeader());
    }
    @When("the developer {string} tries to set the projectleader of project {string} to {string}") // Mads
    public void theDeveloperTriesToSetTheProjectleaderOfProjectTo(String developerInitials, String projectNumber, String leaderInitials){
        try {
            Project project = app.getProject(projectNumber);
            project.assignProjectLeader(this.app.getDeveloper(developerInitials), this.app.getDeveloper(leaderInitials));
        } catch (Exception e){
            this.errorMessage = e.getMessage();
        }
    }
    @Then("{string} is projectleader of project {string}") //Mads
    public void isProjectleaderOfProject(String developerInitials, String projectNumber) {
        Project project = app.getProject(projectNumber); 
        String leaderInitials = project.getProjectLeader();

        assertEquals(this.errorMessage, developerInitials, leaderInitials);
    }
    @Then("the error message {string} is thrown") //Mads
    public void theErrorMessageIsThrown(String error) {
        assertEquals("Error message", error, this.errorMessage);
    }




    
}
