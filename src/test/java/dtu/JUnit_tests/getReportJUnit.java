package dtu.JUnit_tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import dtu.app.*;
import dtu.domain.*;

public class getReportJUnit {
    @Test
    public void testA() throws Exception {
        App app = new App();
        Developer mahh = new Developer("mahh");
        app.addDeveloper(mahh);
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
            ArrayList<String> report = project.getReport(mahh);
        } catch (Exception e) {
            assertEquals("Only the project leader can get the report", e.getMessage());
        }
    }

    @Test
    public void testB() {
        App app = new App();
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
            ArrayList<String> report = project.getReport(thfa);
            assertEquals("20251: No activities", report.get(0));
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    @Test 
    public void testC() {
        App app = new App();
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
            project.getActivity("Activity1").addDeveloper(thfa, thfa);
            project.getActivity("Activity1").setStatus("Ongoing", thfa);
            project.getActivity("Activity1").setStatus("Finished", thfa);
        } catch (ErrorMessage e) {
            System.out.println(e.getMessage());
        }
        try{
            project.setStatus("Finished", thfa);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            ArrayList<String> report = project.getReport(thfa);
            ArrayList<String> answers = new ArrayList<String>();
            answers.add("20251: Finished");
            assertEquals(answers, report);
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testD() {
        App app = new App();
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
            ArrayList<String> report = project.getReport(thfa);
            ArrayList<String> answers = new ArrayList<String>();
            answers.add("20251: Not started - 0.0/0.0");
            answers.add("Activity1: Not started - 0.0/0.0");
            assertEquals(answers, report);
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }
}
