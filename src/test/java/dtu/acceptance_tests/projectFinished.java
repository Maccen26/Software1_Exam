package dtu.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import dtu.app.*;
import dtu.domain.*;

//Freja 
public class projectFinished {
    App app;
    
    public projectFinished(App app){
        this.app = app;
    }

    Project project;
    Developer developer;
    Activity activity;

    @Given("status for project {string} is {string}")
    public void StatusForProjectIs(String projectNumber, String status) throws Exception{
        app.getProject(projectNumber).setStatus(status, developer);
    }

    @Given("{string} has status project leader for project {string}")
    public void HasStatusProjectLeaderForProject(String developer, String ProjectNumber) throws ErrorMessage, Exception{
        app.getProject(ProjectNumber).assignProjectLeader(app.getDeveloper(developer),app.getDeveloper(developer));
    }

    @Given("the project {string} has assigned {string}")
    public void TheProjectHasAssigned(String projectNumber, String activity){
      
    }

    @Given("{string} has status {string}")
    public void HasStats(String activity, String status){
        
    }

     @Given("{string} has status developer on project {string}")
     public void HasStatusDeveloperOnProject(String developer, String ProjectNumber){
        
    }

    @When("{string} wants to set project status to finish")
    public void WantsToSetProjectStatusToFinish(){
       
    }


    @Then("status for project not changed")
    public void StatusforProjectNotChanged(){

    }
    
    @When("{string} change project status to finished")
    public void ChangeProjectStatusToFinished(){

    }

    @Given("{string} with status {string} has been added to project {string}")
    public void WithStatusHasBeenAddedToProject(){

    }
}