package dtu.JUnit_tests;

import static org.junit.jupiter.api.Assertions.*;

import dtu.app.App;
import dtu.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddDeveloperJUnit { //THOMAS - EVERY METHOD IN THIS CLASS

    private App     app;
    private Project project;
    private Activity activity;
    private Developer thfa;   // will play leader or requester
    private Developer mahh;   // will be the “assigned developer”

    @BeforeEach
    void setup() throws Exception {
        app = new App();

        /* Project 20251 with one activity ---------------------- */
        app.createProject();                    // => "20251"
        project  = app.getProject("20251");

        thfa = new Developer("thfa");
        mahh = new Developer("mahh");
        app.addDeveloper(thfa);
        app.addDeveloper(mahh);

        // Add an activity the tests can work on
        int[] week = {1, 2};
        int[] year = {2025, 2025};
        project.addActivity(thfa, "activity1", week, year);
        activity = project.getActivity("activity1");
    }

    /* ---------- Data-set A  : requester is NOT project-leader ---------------- */
    @Test
    void requesterNotLeader_throwsMessage() throws Exception {
        // project leader is “mahh”, requester is “thfa”
        project.assignProjectLeader(mahh, mahh);

        ErrorMessage ex = assertThrows(
            ErrorMessage.class,
            () -> activity.addDeveloper(thfa, mahh)      // thfa tries to add mahh
        );
        assertEquals("Developer is not projectleader", ex.getMessage());
    }

    /* ---------- Data-set B  : duplicate developer ---------------------------- */
    @Test
    void duplicateDeveloper_throwsMessage() throws Exception {
        // Make thfa the leader
        project.assignProjectLeader(thfa, thfa);

        // first add succeeds
        activity.addDeveloper(thfa, mahh);

        // second add (duplicate) must throw
        ErrorMessage ex = assertThrows(
            ErrorMessage.class,
            () -> activity.addDeveloper(thfa, mahh)
        );
        assertEquals("Developer already working on activity", ex.getMessage());
    }

    /* ---------- Data-set C  : happy path ------------------------------------- */
    @Test
    void leaderAddsNewDeveloper_success() throws Exception {
        project.assignProjectLeader(thfa, thfa);

        activity.addDeveloper(thfa, mahh);

        assertTrue(activity.getAssignedDeveloper().contains(mahh),
                   "\"mahh\" should now be assigned to activity");
        assertTrue(mahh.getAssignedActivities().contains(activity),
                   "Activity should appear in mahh’s personal list");
    }
}