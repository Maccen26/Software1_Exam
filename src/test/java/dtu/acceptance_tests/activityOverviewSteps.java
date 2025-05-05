package dtu.acceptance_tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

import dtu.app.App;
import dtu.domain.Developer;
import dtu.domain.Project;



public class activityOverviewSteps { //Mads 

    App app = new App(); 
    
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
    @And("the activity with the name {string} is connected to the project with the number {string}")
    public void activity_with_name_exists(String activityName, String projectNumber) throws Exception {
        Project project = this.app.getProject(projectNumber); 
        if (project.getActivity(activityName) == null){
            assertTrue(false);
            throw new Exception("Activity with the name '" + activityName + "' does not exist in the app.");
        }
    }




    
}
