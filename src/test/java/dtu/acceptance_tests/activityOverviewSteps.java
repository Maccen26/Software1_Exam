package dtu.acceptance_tests;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import dtu.app.App;
import dtu.domain.Activity;
import dtu.domain.Developer;
import dtu.domain.Project;



public class activityOverviewSteps { //Mads 

    App app = new App(); 
    Developer developer;

    
    public activityOverviewSteps() {
        this.app.createProject();
        
    }


    @Given("A project with the number {string} is contained in the app") //Mads
    public void a_project_with_the_number_is_contained_in_the_app(String projectNumber) throws Exception {
        if (app.getProject(projectNumber) == null) {
            assertTrue(false);
            throw new Exception("Project with the given number"+ projectNumber +"does not exist in the app.");
        }
    }
    @And("the activity with the name {string} is connected to the project with the number {string}") // Mads
    public void activity_with_name_exists(String activityName, String projectNumber) throws Exception {
        Project project = this.app.getProject(projectNumber); 
        int[] weekplan = new int[2];
        weekplan[0] = 10; 
        weekplan[1] = 12; 
        int[] yearplan = new int[2]; 
        yearplan[0] = 2025; 
        yearplan[1] = 2025; 

        //Developer developer = this.app.getDeveloper("defaultDeveloper"); // Replace "defaultDeveloper" with an appropriate developer initials
        project.addActivity(this.developer, "Activity1", weekplan, yearplan);  


        if (project.getActivity(activityName) == null){
            assertTrue(false);
            throw new Exception("Activity with the name '" + activityName + "' does not exist in the app.");
        }
    }
    @Given("{string} is working on the activity {string} in the project {string}") //Mads
    public void developer_is_working_on_activity(String developerInitials, String activityName, String projectNumber) throws Exception{
        this.developer = this.app.getDeveloper(developerInitials); 
        Activity activity = this.app.getActivity(activityName, projectNumber); 
        activity.addDeveloper(this.developer); 
    }



    @When("the developer {string} get an overview of all activities for the project {string}")
    public void developer_get_an_overview_of_actvities(String developerInitials, String projectNumber)
    {
        //TODO: Write this class to the end
        throw new PendingException("Finished this method");
    }







    
}
