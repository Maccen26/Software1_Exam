package dtu.JUnit_tests;

import static org.junit.jupiter.api.Assertions.*;

import dtu.app.App;
import dtu.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class setStatusJUnit { //FREJA - - EVERY METHOD IN THIS CLASS

    private Activity  activity;
    private Developer thfa;   // for test with developer on activity
    private Developer mahh;   //       --//--            not on activity

    @BeforeEach
    void setup() throws Exception {
        App app = new App();

        //project 20251 with one activity 
        app.createProject();                        
        Project project = app.getProject("20251");

        thfa = new Developer("thfa");
        mahh = new Developer("mahh");
        app.addDeveloper(thfa);
        app.addDeveloper(mahh);

        project.assignProjectLeader(thfa, thfa);

        int[] w = {1, 2};
        int[] y = {2025, 2025};
        project.addActivity(thfa, "A1", w, y);
        activity = project.getActivity("A1");

        // assign thfa to the activity
        activity.addDeveloper(thfa, thfa);
    }

    // Test A: requester NOT assigned
    @Test
    void requesterNotAssigned_throws() {
        AssertionError ex = assertThrows(
            AssertionError.class,
            () -> activity.setStatus("Finished", mahh)
        );
        assertEquals("not assigned to activity, status change failed", ex.getMessage());
    }

    // Test B: normal change to “ongoing” 
    @Test
    void notStartedToOngoing_ok() throws Exception {
        activity.setStatus("ongoing", thfa);
        assertEquals("ongoing", activity.getStatus());
    }

    // Test C: direct Not-started → Finished
    @Test
    void notStartedToFinishedDirectly_throws() {
        AssertionError ex = assertThrows(
            AssertionError.class,
            () -> activity.setStatus("Finished", thfa)
        );
        assertEquals("activity pending, status change failed", ex.getMessage());
        assertEquals("Not started", activity.getStatus());   // still unchanged
    }
}
