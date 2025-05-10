package dtu.acceptance_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dtu.app.App;
import dtu.domain.*;
import io.cucumber.java.en.*;

public class removeActivitySteps {

    private App app;
    private ErrorMessage lastError;

    public removeActivitySteps(App app) {
        this.app = app;
    }


}
