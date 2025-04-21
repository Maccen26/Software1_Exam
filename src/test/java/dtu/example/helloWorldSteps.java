package dtu.example;


import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class helloWorldSteps {

    Example example = new Example(); 


    

    @Given("a text {string}")
public void aText(String string) {
    this.example.setText(string);
}
@Then("the text {string} is returned")
public void theTextIsReturned(String text) {
    assertEquals(this.example.getText(), text);
}

    
}
