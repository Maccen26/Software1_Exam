package dtu.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Developer {
    private String initials;
    private ArrayList <Activity> assignedActivities;

    public Developer(String initials) { // Lukas
        this.initials = initials;
        this.assignedActivities = new ArrayList<Activity>();
    }

    public void addActivity(Activity activity) { // Johan
        if (!assignedActivities.contains(activity)) {
            assignedActivities.add(activity);
        }
    }

    //Implicit methods
    //getters
    public ArrayList <Activity>  getAssignedActivities() {
        return this.assignedActivities;
    }

    public String getInitials() {
        return initials;
    }
}
