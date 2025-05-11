package dtu.JUnit_tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import dtu.app.*;
import dtu.domain.*;

public class addActivityJUnit {

    @Test
    public void addActivity_notProjectLeader_throws() throws Exception {
        App app = new App();

        app.createProject();
        Project p = app.getProject("20251");

        Developer thfa = new Developer("thfa");
        Developer mahh = new Developer("mahh");
        app.addDeveloper(thfa);
        app.addDeveloper(mahh);

        p.assignProjectLeader(mahh, mahh);

        AssertionError thrown = assertThrows(
            AssertionError.class,
            () -> p.addActivity(thfa, "Activity1", new int[]{1,2}, new int[]{2025,2025})
        );
        assertEquals("Developer is not projectleader", thrown.getMessage());
    }

    @Test
    public void addActivity_duplicateTitle_throws() throws Exception {
        App app = new App();
        app.createProject();
        Project p = app.getProject("20251");

        Developer thfa = new Developer("thfa");
        app.addDeveloper(thfa);
        p.assignProjectLeader(thfa, thfa);

        p.addActivity(thfa, "Activity1", new int[]{1,2}, new int[]{2025,2025});

        AssertionError thrown = assertThrows(
            AssertionError.class,
            () -> p.addActivity(thfa, "Activity1", new int[]{1,2}, new int[]{2025,2025})
        );
        assertEquals("Activity title already exists", thrown.getMessage());
    }

    @Test
    public void addActivity_weekoryearnotlen2() throws Exception {
        App app = new App();
        app.createProject();
        Project p = app.getProject("20251");

        Developer thfa = new Developer("thfa");
        app.addDeveloper(thfa);
        p.assignProjectLeader(thfa, thfa);

        AssertionError thrown = assertThrows(
            AssertionError.class,
            () -> p.addActivity(thfa, "Activity1", new int[]{1}, new int[]{2025})
        );
        assertEquals("Week and Year plan must have length 2", thrown.getMessage());
    }

    @Test
    public void addActivity_successfullyAdds() throws Exception {
        App app = new App();
        app.createProject();
        Project p = app.getProject("20251");

        Developer thfa = new Developer("thfa");
        app.addDeveloper(thfa);
        p.assignProjectLeader(thfa, thfa);

        p.addActivity(thfa, "Activity1", new int[]{1,2}, new int[]{2025,2025});

        assertTrue(p.containsActivityName("Activity1"),
                   "Activity should have been added");
    }
}
