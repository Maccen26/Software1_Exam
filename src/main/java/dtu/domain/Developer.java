package dtu.domain;

import java.util.ArrayList;

public class Developer {
    private String initials;
    private ArrayList <Activity> assignedActivities;

    public Developer(String initials) { // Lukas
        // Initialise the developer initials
        this.initials = initials;
        this.assignedActivities = new ArrayList<Activity>();
    }

    public String getInitials() { // Lukas
        // Get the initials of the developer
        return initials;
    }

    public void addActivity(Activity activity) { // Johan
        // Add an activity to the developer's assigned activities
        this.assignedActivities.add(activity);
    }
}
