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
        this.name = name;
        this.project = assignedProject;
        this.assignedDevelopers = new ArrayList<Developer>();
        this.status = "Not started";
        this.TimeBudget = 0;
        this.weekPlan = weekPlan;
        this.yearPlan = yearPlan;
    }

    public void addDeveloper(Developer requester, Developer developer) throws ErrorMessage { //Thomas
        if (!requester.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Developer is not projectleader");
        }
        if (this.assignedDevelopers.contains(developer)) {
            throw new ErrorMessage("Developer already working on activity");
        }
        assignedDevelopers.add(developer);
        developer.addActivity(this);
    }

    //Implicit methods implemented by Lukas
    //getters
    public ArrayList<Developer> getAssignedDeveloper() {
        return this.assignedDevelopers; 
    }

    public int getTimeBudget() {
        return TimeBudget;
    }

    public int[] getYearPlan() {
        return yearPlan;
    }

    public String getStatus(){
        return status;
    }

    public Project getProject() {
        return project;
    }

    public ArrayList<Developer> getAssignedDevelopers() {
        return assignedDevelopers;
    }
        public int[] getWeekPlan() {
        return weekPlan;
    }

    //setters
    public void setName(Developer dev, String name) throws ErrorMessage{
        if (!dev.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Does not have permission to edit");
        }
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setTimeBudget(Developer dev, int TimeBudget) throws ErrorMessage {
        if (!dev.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Does not have permission to edit");
        }
        this.TimeBudget = TimeBudget;
    }

    public void setWeekPlan(Developer dev, int[] weekPlan) throws ErrorMessage {
        if (!dev.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Does not have permission to edit");
        }
        this.weekPlan = weekPlan;
    }

    public void setYearPlan(Developer dev, int[] yearPlan) throws ErrorMessage { 
        if (!dev.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Does not have permission to edit");
        }
        this.yearPlan = yearPlan;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }

    //has
    public boolean hasDeveloper(Developer developer){ //Thomas
        return this.getAssignedDevelopers().contains(developer);
    }
}
