package dtu.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import dtu.domain.*;
import dtu.app.App;

public class addActivitySteps {

    private App app = new App();
    private ErrorMessage lastError;

    private Developer getOrAddDeveloper(String initials) {
        try {
            return app.getDeveloper(initials);
        } catch (Exception e) {
            Developer dev = new Developer(initials);
            app.addDeveloper(dev);
            return dev;
        }
    }

    @Given("the App contains a project with the project number {int}")
    public void theAppContainsAProjectWithTheProjectNumber(Integer projectNumber) {
        // Check if project already exists
        if (app.getProject(projectNumber.toString()) == null) {
            Project project = new Project(projectNumber.toString());
            app.getProjects().add(project);  // Directly inject it
        }
    }

    @Given("the developer {string} is the projectleader of project {int}")
    public void theDeveloperIsTheProjectleaderOfProject(String devInit, Integer projectNumber) {
        String projectNum = projectNumber.toString();
        Project project = app.getProject(projectNum);
        assertNotNull("Project must exist", project);

        Developer dev = getOrAddDeveloper(devInit);
        try {
            project.assignProjectLeader(dev, dev);
        } catch (ErrorMessage e) {
            fail("Could not assign project leader: " + e.getMessage());
        }
    }

    @Given("the project {int} has an activity with the title {string}")
    public void theProjectHasAnActivityWithTheTitle(Integer projectNumber, String title) {
        String projectNum = projectNumber.toString();
        Project project = app.getProject(projectNum);
        assertNotNull("Project must exist", project);

        String leaderInit = project.getProjectLeader();
        assertNotNull("Project leader must be assigned", leaderInit);

        Developer leader = getOrAddDeveloper(leaderInit);
        int[] weekPlan = {20, 20};
        int[] yearPlan = {2025, 2025};

        try {
            project.addActivity(leader, title, weekPlan, yearPlan);
        } catch (ErrorMessage e) {
            fail("Failed to add activity: " + e.getMessage());
        }
    }

    @Given("the project {int} has no assigned projectleader")
    public void theProjectHasNoAssignedProjectleader(Integer projectNumber) {
        String projectNum = projectNumber.toString();
        Project project = app.getProject(projectNum);
        assertNotNull("Project must exist", project);
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
        Developer dev = getOrAddDeveloper(devInit);
        String projectNum = projectNumber.toString();
        Project project = app.getProject(projectNum);
        assertNotNull("Project must exist", project);

        int[] weekPlan = {startWeek, endWeek};
        int[] yearPlan = {startYear, endYear};

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
        Developer dev = getOrAddDeveloper(devInit);
        String projectNum = projectNumber.toString();
        Project project = app.getProject(projectNum);
        assertNotNull("Project must exist", project);

        int[] weekPlan = {startWeek, endWeek};
        int[] yearPlan = {startYear, endYear};

        try {
            project.addActivity(dev, title, weekPlan, yearPlan);
            lastError = null;
        } catch (ErrorMessage e) {
            lastError = e;
        }
    }

    @Then("the activity with the title {string} exists in project {int}")
    public void theActivityWithTheTitleExistsInProject(String title, Integer projectNumber) {
        String projectNum = projectNumber.toString();
        Project project = app.getProject(projectNum);
        assertNotNull("Project must exist", project);

        try {
            assertNotNull("Expected activity \"" + title + "\"", project.getActivity(title));
        } catch (ErrorMessage e) {
            fail("Activity lookup failed: " + e.getMessage());
        }
    }

    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String expectedMessage) {
        assertNotNull("Expected an error but none was thrown", lastError);
        assertEquals(expectedMessage, lastError.getMessage());
    }
}
