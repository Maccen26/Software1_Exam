package dtu.domain;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity { //Lukas
    private String name; 
    private Project project; 
    private ArrayList<Developer> assignedDevelopers; 
    private String status; 
    private Double TimeBudget;
    private int[] weekPlan = new int [2];
    private int[] yearPlan = new int [2];
    private HashMap<Developer, Double> timeTracker = new HashMap<>();

    public Activity(String name, Project assignedProject, int[] weekPlan, int[] yearPlan) { // Lukas
        this.name = name;
        this.project = assignedProject;
        this.assignedDevelopers = new ArrayList<Developer>();
        this.status = "Not started";
        this.TimeBudget = 0.;
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

    public void registerTime(Double time, Developer developer) throws Exception { //Mads
        /*
         *    PRE-CONDITIONS: 
         *         
         *                  time > 0  && time mod 0.5 = 0 
         * 
         *    
         *    POST-CONDITIONS: 
         *          
         *                  time.get(d_i) = time_old(d_i) + time
         * 
         *  where d_i is developer i
         */


        //PRE-CONDITIONS
        assert time%0.5 == 0: "Time can only be logged in 0.5 hours increments"; //1
        assert time > 0: "Time cannot be negative";//2


        Double registeredTime = timeTracker.get(developer); //3

        if (registeredTime == null) { //4
            registeredTime = 0.0; //5
        }
        timeTracker.put(developer, registeredTime + time); //6

        //POST-CONDITIONS 
        assert timeTracker.get(developer) == (registeredTime + time) : "Post-condtions violated: Time not updated right"; 
    }


    //Implicit methods
    //getters
    public ArrayList<Developer> getAssignedDeveloper() {
        return this.assignedDevelopers; 
    }

    public Double getTimeBudget() {
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

    public void setTimeBudget(Developer dev, Double TimeBudget) throws ErrorMessage {
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

    public void setStatus(String status, Developer requester) throws AssertionError{ //Freja 
        assert hasDeveloper(requester) : "not assigned to activity, status change failed";
        assert !(getStatus().equals("Not started") && status.equals("Finished")) : "activity pending, status change failed";
        this.status = status;
        assert getStatus().equals(status);
    }

    //has
    public boolean hasDeveloper(Developer developer){ //Lukas
        return this.getAssignedDevelopers().contains(developer);
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
