package dtu.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity {
    private String name; 
    private Project project; 
    private ArrayList<Developer> assignedDevelopers; 
    private String status; 
    private int TimeBudget;
    private int[] weekPlan = new int [2];
    private int[] yearPlan = new int [2];
    private HashMap<Developer, Double> timeTracker = new HashMap<>();

    public Activity(String name, Project assignedProject, int[] weekPlan, int[] yearPlan) { // Lukas
        this.name = name;
        this.project = assignedProject;
        this.assignedDevelopers = new ArrayList<Developer>();
        this.status = "Not started";
        this.TimeBudget = 0;
        this.weekPlan = weekPlan;
        this.yearPlan = yearPlan;
        this.timeTracker = new HashMap<Developer, Double>();
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

    public void removeDeveloper(Developer requester, Developer developer) throws ErrorMessage { //Thomas
        if (!requester.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Developer is not projectleader");
        }
        if (!this.assignedDevelopers.contains(developer)) {
            throw new ErrorMessage("Developer is not working on activity");
        }
        assignedDevelopers.remove(developer);
        developer.getAssignedActivities().remove(this);
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
    
    public String getName() {
        return name;
    }

    //setters
    public void setName(Developer dev, String name) throws ErrorMessage{
        if (!dev.getInitials().equals(this.project.getProjectLeader()) && this.project.getProjectLeader() != null) {
            throw new ErrorMessage("Does not have permission to edit");
        }
        this.name = name;
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

    public void registerTime(Double time, Developer developer) throws Exception { //Mads
        checkTime(time);

        Double registeredTime = timeTracker.get(developer);
        if (registeredTime == null) {
            registeredTime = 0.0;
        }
        timeTracker.put(developer, registeredTime + time);
    }

    public void checkTime(Double time) throws Exception{ //Mads
        if (time % 0.5 != 0){
            throw new Exception("Time can only be logged in 0.5 hours increments");
        }
    }

    public Double getRegisteredTime(Developer developer) { //Mads
        Double registeredTime = timeTracker.get(developer);

        if (registeredTime == null)
        {
            registeredTime = 0.0;
        }
        return registeredTime;
    }

    public Double getTimeSpent(){ // Mads
        Double totalTime = 0.0;

        for (Double time: timeTracker.values() ) {
            totalTime += time;
        }

        return totalTime;
    }
}
