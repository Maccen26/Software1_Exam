package dtu.acceptance_tests;


import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.When;

public class testTestSteps {
    @When("this runs everything works")
    public void thisRunsEverythingWorks(){
        assertTrue(true);
    }
    
}
