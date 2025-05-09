package dtu.acceptance_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dtu.app.App;
import dtu.domain.*;
import io.cucumber.java.en.*;

public class removeActivity {

    private final App app;
    private ErrorMessage lastError;

    public removeActivity(AppContext context) {
        this.app = context.app;
    }

    private Developer getOrAddDeveloper(String initials) {
        try {
            return app.getDeveloper(initials);
        } catch (Exception e) {
            Developer dev = new Developer(initials);
            app.addDeveloper(dev);
            return dev;
        }
    }


    @When("the developer {string} deletes the activity with the title {string} from project {int}")
    public void theDeveloperDeletesTheActivityWithTheTitleFromProject(String devInit, String title, Integer projectNumber) {
        Project project = app.getProject(projectNumber.toString());
        assertNotNull(project, "Project does not exist");

        Developer dev = getOrAddDeveloper(devInit);

        try {
            project.removeActivity(dev, title);
            lastError = null;
        } catch (ErrorMessage e) {
            lastError = e;
        }
    }

    @Then("the activity with the title {string} no longer exists in project {int}")
    public void theActivityWithTheTitleNoLongerExistsInProject(String title, Integer projectNumber) {
        Project project = app.getProject(projectNumber.toString());
        assertNotNull(project, "Project does not exist");

        try {
            project.getActivity(title);
            fail("Activity should have been removed but was found");
        } catch (ErrorMessage e) {
            assertEquals("Activity not found", e.getMessage());
        }
    }
}
