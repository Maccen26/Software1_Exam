package dtu.JUnit_tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import dtu.app.App;
import dtu.domain.*;
import io.cucumber.java.bs.A;

public class registerTimeJUnit {


private App app;
private Developer developer;
private Project project; 


public registerTimeJUnit() throws Exception {
    this.app = new App(); 
    this.developer = new Developer("mahh");
    this.app.addDeveloper(developer);
    this.app.login(developer);

    this.app.createProject();
    
    //this.app.addActivity("20251", "Activity1", new int[] {10, 14}, new int[] {2025, 2025});
    this.project = app.getProject("20251");

    Activity activity = new Activity("Activity1", this.project, new int[] {10, 14}, new int[] {2025, 2025});
    this.project.addActivity(developer, "Activity1", new int[] {10, 14}, new int[] {2025, 2025});

    activity.addDeveloper(developer, developer);
   //this.app.addDeveloperToActivity("20251", "Activity1", "mahh");
}   

@Test public void setUp(){
    try{
        this.app.getDeveloper("mahh"); 
        Project project = app.getProject("20251"); 
        project.getActivity("Activity1");
    } catch (Exception e){
        assertTrue(e.getMessage(), false);;
    }

}
    @Test
    public void testA(){

        

    }

}
