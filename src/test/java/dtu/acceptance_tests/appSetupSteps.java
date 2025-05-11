package dtu.acceptance_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import dtu.app.App;
import dtu.domain.Activity;
import dtu.domain.Developer;
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
    public void projectAndExists(String projectNumber1, String projectNumber2) throws Exception //MAds
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
    public void projectAndIsContainedWithinTheApp(String projectNumber1, String projectNumber2) throws Exception //MAds
    {
        assertFalse(this.app.getProject(projectNumber1) == null);
        assertFalse(this.app.getProject(projectNumber2) == null);
    }


//Developer app setup
    @Given("the developers {string} {string} and {string} is in the app") //Mads
    public void theDevelopersAnd(String huba, String mahh, String thfa) throws Exception {//MAds
        // Write code here that turns the phrase above into concrete actions
        try{
            this.app.getDeveloper(huba);
        } catch (Exception e) 
        {   
            this.app.addDeveloper(new Developer(huba));
        }
        try{
            this.app.getDeveloper(mahh);
        } catch (Exception e){
            this.app.addDeveloper(new Developer(mahh));
        }
        try {
            this.app.getDeveloper(thfa);
        }catch (Exception e){
            this.app.addDeveloper(new Developer(thfa));
        }

    }
    @Then("the developers {string} {string} and {string} are contained in the app") //Mads 
    public void theDevelopersAndAreContainedInTheApp(String string, String string2, String string3) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(this.app.getDeveloper(string) == null);
        assertFalse(this.app.getDeveloper(string2) == null);
        assertFalse(this.app.getDeveloper(string3) == null);
    }

    @Given("{string} is project leader on project {string}")
    public void thfaIsProjectLeaderOnProject(String developerName, String projectNumber) throws Exception { //Mads
        Project project = this.app.getProject(projectNumber); 
        Developer developer = this.app.getDeveloper(developerName);

        project.assignProjectLeader(developer, developer);

        assertTrue(project.getProjectLeader().equals(developerName));
    }

   @Given("the activity with name {string}, under project {string} with startweek {int}, endweek {int} and startyear {int}, endyear {int}")  //MAds
   public void theActivityWithNameUnderProjectWithStartweekEndweekAndStartyearEndyear(String activityName, String projectNumber, Integer startweek, Integer endweeek, Integer startyear, Integer endyear) throws Exception {//MAds
       // Write code here that turns the phrase above into concrete actions
       int[] weekplan = {startweek, endweeek}; 
       int[] yearplan = {startyear, endyear};

       this.app.addActivity(projectNumber, activityName, yearplan, weekplan);
   }
   @Given("{string} is logged into the app") //MADs
    public void isLoggedIntoTheApp(String developerInitials) throws Exception {//MAds
        // Write code here that turns the phrase above into concrete actions
        this.app.login(this.app.getDeveloper(developerInitials));
    }


     @Then("the activity {string} exist in the project {string}") //Mads
     public void theActivityExistInTheProject(String activityName, String projectNumber) throws Exception {//MAds
        Project project = app.getProject(projectNumber); 
        
        assertFalse(project.getActivity(activityName) == null);

     }     

    
}
