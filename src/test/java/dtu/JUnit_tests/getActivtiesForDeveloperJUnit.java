package dtu.JUnit_tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dtu.app.App;
import dtu.domain.*;
import io.cucumber.java.bs.A; 


public class getActivtiesForDeveloperJUnit { 
private App app;
private Developer developer;
private ArrayList<Activity> activityList;

public getActivtiesForDeveloperJUnit() throws Exception {
    this.activityList = new ArrayList<Activity>();

    this.app = new App(); 
    this.developer = new Developer("mahh");
    this.app.addDeveloper(developer);
    this.app.login(developer);

    this.app.createProject();
    
    this.app.addActivity("20251", "Activity1", new int[] {10, 14}, new int[] {2025, 2025});
    this.app.addDeveloperToActivity("20251", "Activity1", "mahh");
}

    @Test 
    public void setup() throws ErrorMessage
    {
        assertTrue(this.app.hasDeveloper("mahh"));
        assertTrue(this.app.getProject("20251").getProjectNumber().equals("20251"));
        assertTrue(this.app.getProject("20251").getAllActivities().size() == 1);
        assertTrue(this.app.getProject("20251").getActivity("Activity1") != null);

    }

    @Test 
    public void testA() throws ErrorMessage
    {
        try{
            this.app.getActivitesForDeveloper("pete"); 
            assertTrue(false);
        } catch (Exception e) {
            assertEquals("Developer not contained in app", e.getMessage());;
        }
    }
    
    @Test
    public void testB() throws Exception 
    {  
        this.activityList = app.getActivitesForDeveloper("mahh"); 
        assertTrue(activityList.size() == 1); 
        assertEquals(activityList.get(0).getName(), "Activity1");
        
    }

    @Test
    public void testC() throws Exception
    {
        this.app.setActivtyStatus("20251", "Activity1", "Done");
        this.activityList = app.getActivitesForDeveloper("mahh"); 
        assertTrue(activityList.size() == 1); 
        assertEquals(activityList.get(0).getName(), "Activity1");
    }
}
