package dtu.acceptance_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.nullable;

import javax.naming.OperationNotSupportedException;

import dtu.app.App;
import dtu.domain.Activity;
import dtu.domain.Developer;
import dtu.domain.ErrorMessage;
import dtu.domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class appSetupSteps { //Mads 

    App app; 

    public appSetupSteps(App app)
    {
        this.app = app;
    }
    
// Setting Up the proejct
    @Given("project {string} and {string} exists") //Mads 
    public void projectAndExists(String projectNumber1, String projectNumber2) 
    {
        if (this.app.getProject(projectNumber1) == null)
        {
            this.app.createProject(); //Creating the very first project
        }
        if (this.app.getProject(projectNumber2) == null)
        {
            this.app.createProject();
        }
    }
    @Then("project {string} and {string} is contained within the app") //Mads
    public void projectAndIsContainedWithinTheApp(String projectNumber1, String projectNumber2) 
    {
        assertFalse(this.app.getProject(projectNumber1) == null);
        assertFalse(this.app.getProject(projectNumber2) == null);
    }


//Developer app setup
    @Given("the developers {string} {string} and {string}") //Mads
    public void theDevelopersAnd(String huba, String mahh, String thfa) {
        // Write code here that turns the phrase above into concrete actions
        if (this.app.getDeveloper(huba) == null)
        {   
            this.app.addDeveloper(new Developer(huba));
        }

        if (this.app.getDeveloper(mahh) == null)
        {   
            this.app.addDeveloper(new Developer(mahh));
        }

        if (this.app.getDeveloper(thfa) == null)
        {   
            this.app.addDeveloper(new Developer(thfa));
        }
    }
    @Then("the developers {string} {string} and {string} are contained in the app") //Mads 
    public void theDevelopersAndAreContainedInTheApp(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(this.app.getDeveloper(string) == null);
        assertFalse(this.app.getDeveloper(string2) == null);
        assertFalse(this.app.getDeveloper(string3) == null);
    }

    @Given("{string} is project leader on project {string}")
    public void thfaIsProjectLeaderOnProject(String developerName, String projectNumber) throws ErrorMessage {
        Project project = this.app.getProject(projectNumber); 
        Developer developer = this.app.getDeveloper(developerName);

        project.assignProjectLeader(developer, developer);

        assertTrue(project.getProjectLeader().equals(developerName));
    }

   @Given("the activity with name {string}, under project {string} with startweek {int}, endweek {int} and startyear {int}, endyear {int}")
   public void theActivityWithNameUnderProjectWithStartweekEndweekAndStartyearEndyear(String activityName, String projectNumber, Integer startweek, Integer endweeek, Integer startyear, Integer endyear) throws ErrorMessage {
       // Write code here that turns the phrase above into concrete actions
       int[] weekplan = {startweek, endweeek}; 
       int[] yearplan = {startyear, endyear};

       this.app.addActivity(projectNumber, activityName, yearplan, weekplan);
   }
   @Given("{string} is logged into the app")
    public void isLoggedIntoTheApp(String developerInitials) throws ErrorMessage {
        // Write code here that turns the phrase above into concrete actions
        this.app.login(this.app.getDeveloper(developerInitials));
    }


     @Then("the activity {string} exist in the project {string}")
     public void theActivityExistInTheProject(String activityName, String projectNumber) throws ErrorMessage {
        Project project = app.getProject(projectNumber); 
        
        assertFalse(project.getActivity(activityName) == null);

     }     

    
}
