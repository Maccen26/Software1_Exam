package dtu.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import dtu.domain.Project;
import dtu.domain.Developer;
import dtu.domain.ErrorMessage;

import java.util.Map;
import java.util.HashMap;

public class addActivitySteps {

    private Map<String, Project> projects = new HashMap<>();
    private Map<String, Developer> developers = new HashMap<>();
    private ErrorMessage lastError;

    @Given("the App contains a project with the project number {int}")
    public void theAppContainsAProjectWithTheProjectNumber(Integer projectNumber) {
        String key = projectNumber.toString();
        projects.put(key, new Project(key));
    }

    @Given("the developer {string} is the projectleader of project {int}")
    public void theDeveloperIsTheProjectleaderOfProject(String devInit, Integer projectNumber) {
        String key = projectNumber.toString();
        Project project = projects.get(key);
        assertNotNull("Project must exist", project);

        Developer leader = developers.computeIfAbsent(devInit, Developer::new);
        try {
            project.assignProjectLeader(leader, leader);
        } catch (ErrorMessage e) {
            fail("Could not assign project leader: " + e.getMessage());
        }
    }

    @Given("the project {int} has an activity with the title {string}")
    public void theProjectHasAnActivityWithTheTitle(Integer projectNumber, String title) throws ErrorMessage {
        String key = projectNumber.toString();
        Project project = projects.get(key);
        assertNotNull("Project must exist", project);

        // Ensure a leader is already assigned
        String leaderInit = project.getProjectLeader();
        assertNotNull("Project leader must already be assigned", leaderInit);

        // Fetch or create the leader developer
        Developer leader = developers.computeIfAbsent(leaderInit, Developer::new);

        // Dummy plan values for setup
        int[] weekPlan = {20, 20};
        int[] yearPlan = {2025, 2025};

        // Add the existing activity
        project.addActivity(leader, title, weekPlan, yearPlan);
    }

    @Given("the project {int} has no assigned projectleader")
    public void theProjectHasNoAssignedProjectleader(Integer projectNumber) {
        String key = projectNumber.toString();
        Project project = projects.get(key);
        assertNotNull("Project must exist", project);
        // default constructor leaves projectLeader == null, so nothing more to do
    }

    @When("the developer {string} adds an activity with the title {string}, start week {int}, start year {int}, end week {int}, end year {int} to project {int}")
    public void theDeveloperAddsAnActivityWithTheTitleStartWeekStartYearEndWeekEndYearToProject(
        String devInit,
        String title,
        Integer startWeek,
        Integer startYear,
        Integer endWeek,
        Integer endYear,
        Integer projectNumber
    ) {
        String key = projectNumber.toString();
        Project project = projects.get(key);
        assertNotNull("Project must exist", project);

        Developer dev = developers.computeIfAbsent(devInit, Developer::new);
        int[] weekPlan = { startWeek, endWeek };
        int[] yearPlan = { startYear, endYear };

        try {
            project.addActivity(dev, title, weekPlan, yearPlan);
            lastError = null;
        } catch (ErrorMessage e) {
            lastError = e;
        }
    }

    @When("the developer {string} tries to add an activity with the title {string}, start week {int}, start year {int}, end week {int}, end year {int} to project {int}")
    public void theDeveloperTriesToAddAnActivityWithTheTitleStartWeekStartYearEndWeekEndYearToProject(
        String devInit,
        String title,
        Integer startWeek,
        Integer startYear,
        Integer endWeek,
        Integer endYear,
        Integer projectNumber
    ) {
        // same body as above, capturing errors
        String key = projectNumber.toString();
        Project project = projects.get(key);
        assertNotNull("Project must exist", project);

        Developer dev = developers.computeIfAbsent(devInit, Developer::new);
        int[] weekPlan = { startWeek, endWeek };
        int[] yearPlan = { startYear, endYear };

        try {
            project.addActivity(dev, title, weekPlan, yearPlan);
            lastError = null;
        } catch (ErrorMessage e) {
            lastError = e;
        }
    }

    @Then("the activity with the title {string} exists in project {int}")
    public void theActivityWithTheTitleExistsInProject(String title, Integer projectNumber) {
        String key = projectNumber.toString();
        Project project = projects.get(key);
        assertNotNull("Project must exist", project);

        try {
            assertNotNull("Expected activity “" + title + "”", project.getActivity(title));
        } catch (ErrorMessage e) {
            fail("Lookup failed: " + e.getMessage());
        }
    }

    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String expectedMessage) {
        assertNotNull("Expected an error but none was thrown", lastError);
        assertEquals(expectedMessage, lastError.getMessage());
    }
}
