package dtu.domain;

import java.util.ArrayList;

public class Activity {
    private String name;
    private String project;
    private ArrayList<Developer> assignedDevelopers;
    private String status;
    private int TimeBudget;
    private int[] weekPlan = new int [2];
    private int[] yearPlan = new int [2];

    public Activity(String name, String assignedProject, ArrayList<Developer> assignedDevelopers, int TimeBudget, int[] weekPlan, int[] yearPlan) { // Lukas
        // Initialise the activity name, project, assigned developers, status, time budget, week plan and year plan
        this.name = name;
        this.project = assignedProject;
        this.assignedDevelopers = assignedDevelopers;
        this.status = "Not started";
        this.TimeBudget = TimeBudget;
        this.weekPlan = weekPlan;
        this.yearPlan = yearPlan;
    }
}
