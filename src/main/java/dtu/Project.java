package dtu;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en_old.Ac;

import java.util.Arrays;

public class Project {
    private String projectNumber;
    private String projectLeader;
    private ArrayList<Activity> activities;

    public Project(String projectNumber, String projectLeader) {
        // Initialise the project number and project leader
        this.projectNumber = projectNumber;
        this.projectLeader = projectLeader;
        this.activities = new ArrayList<Activity>();
    }

    public String addActivity(String name, int[] weekplan, int[] yearplan, ArrayList<Developer> assignedDevelopers, int timeBudget) {
        // Create a new activity and add it to the project
        Activity newActivity = new Activity(name, this.projectNumber, assignedDevelopers, timeBudget, weekplan, yearplan);
        this.activities.add(newActivity);
        return newActivity.getName();

    }
}
