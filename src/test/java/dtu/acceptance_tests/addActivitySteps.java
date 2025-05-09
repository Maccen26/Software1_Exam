package dtu.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import dtu.domain.Project;
import dtu.domain.Developer;
import dtu.domain.Activity;
import dtu.domain.ErrorMessage;

import java.util.Map;
import java.util.HashMap;

public class addActivitySteps {
//
//    private Map<String, Project> projects = new HashMap<>();
//    private Map<String, Developer> developers = new HashMap<>();
//    private Map<String, String> projectLeaders = new HashMap<>();
//    private ErrorMessage lastError;
//
//   // @Given("the App contains a project with the project number {string}")
//   // public void theAppContainsAProjectWithTheProjectNumber(String projectNumber) {
//   //     projects.put(projectNumber, new Project(projectNumber));
//   // }
//
//    @Given("the App contains a developer with the initials {string}")
//    public void theAppContainsADeveloperWithTheInitials(String initials) {
//        developers.put(initials, new Developer(initials));
//    }
//
//    @Given("the developer {string} is logged into the App")
//    public void theDeveloperIsLoggedIntoTheApp(String initials) {
//        assertTrue("Developer must exist", developers.containsKey(initials));
//    }
//
//    @Given("the developer {string} is the projectleader of project {string}")
//    public void theDeveloperIsTheProjectleaderOfProject(String devInitials, String projectNumber) throws ErrorMessage {
//        Developer dev = developers.get(devInitials);
//        Project project = projects.get(projectNumber);
//        project.assignProjectLeader(dev, dev);
//        projectLeaders.put(projectNumber, devInitials);
//    }
//
//    @Given("the project {string} has an activity with the title {string}")
//    public void theProjectHasAnActivityWithTheTitle(String projectNumber, String title) throws ErrorMessage {
//        String leaderInitials = projectLeaders.get(projectNumber);
//        Developer leader = developers.get(leaderInitials);
//        Project project = projects.get(projectNumber);
//        // use a simple placeholder plan (length == 2)
//        project.addActivity(leader, title, new int[]{1, 1}, new int[]{2025, 2025});
//    }
//
//    @Given("the project {string} has no assigned projectleader")
//    public void theProjectHasNoAssignedProjectleader(String projectNumber) {
//        projectLeaders.remove(projectNumber);
//        // default projectLeader field remains null
//    }
//
//    @When("the developer {string} adds an activity with the title {string}, start week {int}, start year {int}, end week {int}, end year {int} to project {string}")
//    public void theDeveloperAddsAnActivityWithTheTitleStartWeekStartYearEndWeekEndYearToProject(
//        String devInitials,
//        String title,
//        int startWeek,
//        int startYear,
//        int endWeek,
//        int endYear,
//        String projectNumber) {
//        Developer dev = developers.get(devInitials);
//        Project project = projects.get(projectNumber);
//        try {
//            project.addActivity(dev, title, new int[]{startWeek, endWeek}, new int[]{startYear, endYear});
//            lastError = null;
//        } catch (ErrorMessage e) {
//            lastError = e;
//        }
//    }
//
//    @When("the developer {string} tries to add an activity with the title {string}, start week {int}, start year {int}, end week {int}, end year {int} to project {string}")
//    public void theDeveloperTriesToAddAnActivityWithTheTitleStartWeekStartYearEndWeekEndYearToProject(
//        String devInitials,
//        String title,
//        int startWeek,
//        int startYear,
//        int endWeek,
//        int endYear,
//        String projectNumber) {
//        theDeveloperAddsAnActivityWithTheTitleStartWeekStartYearEndWeekEndYearToProject(
//            devInitials, title, startWeek, startYear, endWeek, endYear, projectNumber);
//    }
//
//    //@Then("the activity with the title {string} exists in project {string}")
//    //public void theActivityWithTheTitleExistsInProject(String title, String projectNumber) {
//    //    Project project = projects.get(projectNumber);
//    //    Activity activity = project.getActivity(title);
//    //    assertNotNull("Activity should exist in project " + projectNumber, activity);
//    //}
//
//    @Then("the error message {string} is given")
//    public void theErrorMessageIsGiven(String expectedMessage) {
//        assertNotNull("Expected an error message but none was thrown", lastError);
//        assertEquals(expectedMessage, lastError.getMessage());
//    }
}
//