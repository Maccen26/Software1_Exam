package dtu.JUnit_tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import dtu.app.*;
import dtu.domain.*;

public class getReportJUnit {
    @Test
    public void testA() {
        App app = new App();
        app.createProject();
        Developer mahh = new Developer("mahh");
        app.addDeveloper(mahh);
        Developer thfa = app.getDeveloper("thfa");
        app.addDeveloper(thfa);

        app.createProject();
        Project project = app.getProject("20251");
        try{
            project.assignProjectLeader(thfa, thfa);
        } catch (ErrorMessage e) {
            System.out.println(e.getMessage());
        }

        try{
            String report = project.getReport(mahh);
        } catch (ErrorMessage e) {
            assertEquals("Only the project leader can get the report", e.getMessage());
        }
    }

    @Test
    public void testB() {
        App app = new App();
        app.createProject();
        Developer thfa = new Developer("thfa");
        app.addDeveloper(thfa);

        app.createProject();
        Project project = app.getProject("20251");
        try{
            project.assignProjectLeader(thfa, thfa);
        } catch (ErrorMessage e) {
            System.out.println(e.getMessage());
        }

        try{
            String report = project.getReport(thfa);
            assertEquals("20251, no activities", report);
        } catch (ErrorMessage e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testC() {
        App app = new App();
        app.createProject();
        Developer thfa = new Developer("thfa");
        app.addDeveloper(thfa);

        app.createProject();
        Project project = app.getProject("20251");
        try{
            project.assignProjectLeader(thfa, thfa);
        } catch (ErrorMessage e) {
            System.out.println(e.getMessage());
        }

        try{
            project.addActivity(thfa, "Activity1", new int[]{1, 2}, new int[]{2025, 2025});
        } catch (ErrorMessage e) {
            System.out.println(e.getMessage());
        }

        try{
            String report = project.getReport(thfa);
            assertEquals("20251: Not started\nActivity1: Not started\n", report);
        } catch (ErrorMessage e) {
            System.out.println(e.getMessage());
        }
    }
}
