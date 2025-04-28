package dtu.domain;

import java.util.ArrayList;

public class Developer {
    private String initials;
    private ArrayList <Activity> assignedActivities;

    public Developer(String initials) {
        // Initialise the developer initials
        this.initials = initials;
        this.assignedActivities = new ArrayList<Activity>();
    }
}
