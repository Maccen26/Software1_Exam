package dtu.domain;

import java.util.ArrayList;

public class Activity {
    private String name; 
    private Project project; 
    private ArrayList<Developer> assignedDevelopers; 
    private String status; 
    private int TimeBudget;
    private int[] weekPlan = new int [2];
    private int[] yearPlan = new int [2];

    public Activity(String name, Project assignedProject, int[] weekPlan, int[] yearPlan) { // Lukas
        // Initialise the activity name, project, assigned developers, status, time budget, week plan and year plan
        this.name = name;
        this.project = assignedProject;
        this.assignedDevelopers = new ArrayList<Developer>();
        this.status = "Not started";
        this.TimeBudget = 0;
        this.weekPlan = weekPlan;
        this.yearPlan = yearPlan;
    }

    public void setName(Developer dev, String name) throws ErrorMessage{ // Lukas
        // Check if the developer is the project leader
        if (!dev.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Does not have permission to edit");
        }

        // Set the name of the activity
        this.name = name;
    }
    public String getName() { // Lukas
        // Get the name of the activity
        return name;
    }


    public void setTimeBudget(Developer dev, int TimeBudget) throws ErrorMessage { // Lukas
        // Check if the developer is the project leader
        if (!dev.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Does not have permission to edit");
        }

        // Set the time budget for the activity
        this.TimeBudget = TimeBudget;
    }
    public int getTimeBudget() { // Lukas
        // Get the time budget for the activity
        return TimeBudget;
    }

    public void setWeekPlan(Developer dev, int[] weekPlan) throws ErrorMessage { // Lukas
        // Check if the developer is the project leader
        if (!dev.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Does not have permission to edit");
        }

        // Set the week plan for the activity
        this.weekPlan = weekPlan;
    }
    public int[] getWeekPlan() { // Lukas
        // Get the week plan for the activity
        return weekPlan;
    }

    public void setYearPlan(Developer dev, int[] yearPlan) throws ErrorMessage { // Lukas
        // Check if the developer is the project leader
        if (!dev.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Does not have permission to edit");
        }

        // Set the year plan for the activity
        this.yearPlan = yearPlan;
    }
    public int[] getYearPlan() { // Lukas
        // Get the year plan for the activity
        return yearPlan;
    }

    public String getStatus(){ // Lukas
        // Get the status of the activity
        return status;
    }

    public Project getProject() { // Lukas
        // Get the project of the activity
        return project;
    }

    public ArrayList<Developer> getAssignedDevelopers() { // Lukas
        // Get the assigned developers for the activity
        return assignedDevelopers;
    }

    public void addDeveloper(Developer requester, Developer developer) // Mads + Johan + Thomas
            throws ErrorMessage {
    {   
    
        if (!requester.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Developer is not projectleader");
        }
        if (!this.assignedDevelopers.contains(developer)) {
            assignedDevelopers.add(developer);
            developer.addActivity(this);
        }

    }
    }


    public ArrayList<Developer> getAssignedDeveloper() { //MAds
        return this.assignedDevelopers; 
    }

    public void setStatus(String status2) {
        this.status = status2;
    }
    public boolean hasDeveloper(Developer developer){ //Thomas
        return this.getAssignedDevelopers().contains(developer);
    }
}
