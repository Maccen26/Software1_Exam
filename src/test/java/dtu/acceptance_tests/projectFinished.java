import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import dtu.app.*;
import dtu.domain.*;

// Freja 
public class ProjectFinished {
    Project project;
    Developer developer;
    Activity activity;

    @Given("a project {string}")
    public void aProject(String string){
 
    }

    @Given("status for project {string} is {string}")
    public boolean StatusForProjectIs(String projectNumber, String status){

    }

    @Given("developer with name {string} is in app")
    public void DeveloperWithNameIsInApp(String string){

    }

    @Given("{string} is logged in")
    public void IsLoggedIn(String string){

    }

    @Given("{string} has status project leader for project {string}")
    public boolean HasStatusProjectLeaderForProject(String developer, String ProjectNumber){
   
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

    @Then("gets errormessage {string}")
    public String GetsErrormessage(String string){
        ErrorMessage.errormessage(string)
    }

    @Then("status for project not changed")
    public boolean StatusforProjectNotChanged(){

    }
     
    @When("{string} change project status to finished")
    public void ChangeProjectStatusToFinished(){

    }

    @Then("status change succeed")
    public void StatusChangeSucceed(){

    }

    @Given("{string} with status {string} has been added to project {string}")
    public void WithStatusHasBeenAddedToProject(){
        
    }
}
