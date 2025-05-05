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

    public Activity(String name, String assignedProject, int[] weekPlan, int[] yearPlan) { // Lukas
        // Initialise the activity name, project, assigned developers, status, time budget, week plan and year plan
        this.name = name;
        this.project = assignedProject;
        this.assignedDevelopers = new ArrayList<Developer>();
        this.status = "Not started";
        this.TimeBudget = 0;
        this.weekPlan = weekPlan;
        this.yearPlan = yearPlan;
    }

    public void setName(String name) { // Lukas
        // Set the name of the activity
        this.name = name;
    }
    public String getName() { // Lukas
        // Get the name of the activity
        return name;
    }

    public void setTimeBudget(int TimeBudget) { // Lukas
        // Set the time budget for the activity
        this.TimeBudget = TimeBudget;
    }
    public int getTimeBudget() { // Lukas
        // Get the time budget for the activity
        return TimeBudget;
    }

    public void setWeekPlan(int[] weekPlan) { // Lukas
        // Set the week plan for the activity
        this.weekPlan = weekPlan;
    }
    public int[] getWeekPlan() { // Lukas
        // Get the week plan for the activity
        return weekPlan;
    }

    public void setYearPlan(int[] yearPlan) { // Lukas
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

    public void addDeveloper(Developer developer) throws Exception // Mads
    {   
        if (this.assignedDevelopers.contains(developer)) 
        {
            throw new Exception("Developer is already added to the activity");
        }
        assignedDevelopers.add(developer);
        developer.addActivity(this);
    }
}
