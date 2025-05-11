package dtu.JUnit_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import dtu.app.App;
import dtu.domain.*;
import io.cucumber.java.bs.A;



public class registerTimeJUnit { //MADS - - EVERY METHOD IN THIS CLASS


private App app;
private Developer developer;
private Project project; 
private Activity activity;

public registerTimeJUnit() throws Exception, ErrorMessage {
    this.app = new App(); 
    this.developer = new Developer("mahh");
    this.app.addDeveloper(developer);
    this.app.login(developer);

    this.app.createProject();
    
    //this.app.addActivity("20251", "Activity1", new int[] {10, 14}, new int[] {2025, 2025});
    this.project = app.getProject("20251");

    //Activity activity = new Activity("Activity1", this.project, new int[] {10, 14}, new int[] {2025, 2025});
    this.project.addActivity(developer, "Activity1", new int[] {10, 14}, new int[] {2025, 2025});
    this.activity = project.getActivity("Activity1");
    //activity.addDeveloper(developer, developer);
   //this.app.addDeveloperToActivity("20251", "Activity1", "mahh");
}   

@Test public void setUp(){
    try{
        this.app.getDeveloper("mahh"); 
        Project project = app.getProject("20251"); 
        this.activity = project.getActivity("Activity1");

    } catch (Exception e){
        assertTrue(e.getMessage(), false);;
    }

}
    @Test
    public void testA() throws AssertionError, Exception{
        try {
            Double time = 0.1;

            this.activity.registerTime(time, this.developer);

        } catch (AssertionError e){
            assertEquals("Time can only be logged in 0.5 hours increments", e.getMessage());
        }
    }
    @Test 
    public void testB() throws Exception{
        try{
            Double time = -1.0;
            assertTrue(time%0.5 == 0);
            this.activity.registerTime(time,this.developer);
        } catch (AssertionError e){
            assertEquals("Time cannot be negative", e.getMessage());
        }
    }

    @Test
    public void testC() throws Exception {
        Project project = app.getProject("20251");
        Activity activityApp = project.getActivity("Activity1"); 
        assertEquals(activityApp.getRegisteredTime(this.developer), (Double)0.0);


        Double time = 1.0;
        this.activity.registerTime(time, this.developer);

        Project project2 = app.getProject("20251");
        Activity activityApp2 = project2.getActivity("Activity1"); 


        assertEquals(activityApp2.getRegisteredTime(this.developer), (Double)1.0);
    }



    @Test
    public void testD() throws Exception{

        Project project = app.getProject("20251");
        Activity activity = project.getActivity("Activity1"); 

        //Chech that no time is registered
        assertEquals(activity.getRegisteredTime(this.developer), (Double)0.0);

        //Check that 1 hour is registered
        activity.registerTime((Double)1.0, this.developer);
        assertEquals(activity.getRegisteredTime(this.developer), (Double)1.0);

        //Check that 1 hour is added
        activity.registerTime((Double)1.0, this.developer);
        assertEquals(activity.getRegisteredTime(this.developer), (Double)2.0);
    }
}
