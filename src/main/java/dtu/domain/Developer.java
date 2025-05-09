package dtu.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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
        if (!assignedActivities.contains(activity)) {
            assignedActivities.add(activity);
        }
    }

    public ArrayList <Activity>  getAssignedActivities() { // Johan
        // Get the list of assigned activities for the developer
        return this.assignedActivities;
    }
}
