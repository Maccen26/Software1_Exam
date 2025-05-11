package dtu.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import org.eclipse.persistence.internal.sessions.DirectCollectionChangeRecord.NULL;

import io.cucumber.java.en_old.Ac;
import io.cucumber.java.hu.De;

public class Project { //Lukas
    private String projectNumber;
    private String projectLeader;
    private ArrayList<Activity> activities;
    private String status;

    public Project(String projectNumber) { // Lukas
        this.projectNumber = projectNumber;
        this.projectLeader = null;
        this.activities = new ArrayList<Activity>();
        this.status = "Not started";
    }

    public void assignProjectLeader(Developer requester, Developer newLeader) throws ErrorMessage { //Johan
        if (this.projectLeader != null && !requester.getInitials().equals(this.projectLeader)) {
            throw new ErrorMessage("Only the current project leader can reassign the project leader");
        }
        this.projectLeader = newLeader.getInitials();
    }

    public void addActivity(Developer requester, String name, int[] weekPlan, int[] yearPlan) throws AssertionError { //Johan
        assert requester.getInitials().equals(this.projectLeader) || this.projectLeader == null : "Developer is not projectleader";
        assert !containsActivityName(name) : "Activity title already exists";
        assert weekPlan.length == 2 && yearPlan.length == 2: "Week and Year plan must have length 2";

        int[] weekPlanCopy = weekPlan.clone();
        int[] yearPlanCopy = yearPlan.clone();

        Activity newActivity = new Activity(name, this, weekPlanCopy, yearPlanCopy);
        int oldSize = activities.size();
        activities.add(newActivity);

        assert activities.size() == oldSize + 1 : "Activity list size incorrect";
        assert containsActivityName(name) : "New activity not present";
    }

    public void removeActivity(Developer requester, String name) throws ErrorMessage {//Johan
        if (!requester.getInitials().equals(this.projectLeader) && this.projectLeader != null) {
            throw new ErrorMessage("Developer is not projectleader");
        }
        else if (!containsActivityName(name)) {
            throw new ErrorMessage("Activity not found");
        }

        Activity activityToRemove = getActivity(name);
        activities.remove(activityToRemove);
    }

    public boolean containsActivityName(String name) { //Johan
        for (Activity activity : this.activities) {
            if (activity.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void addDeveloperToActivity(String activityName, Developer requester, Developer developer) throws ErrorMessage { //Mads
        Activity activity = getActivity(activityName); 
        activity.addDeveloper(developer, requester);
    }

    public ArrayList<String> getReport(Developer dev) throws AssertionError{ // Lukas
        assert this.projectLeader.equals(dev.getInitials()) : "Only the project leader can get the report";

        if (this.activities.isEmpty()) {
            ArrayList<String> emptyReport = new ArrayList<>();
            emptyReport.add(projectNumber + ": No activities");
            return emptyReport;
        }
        if (this.status.equals("Finished")){
            ArrayList<String> finishedReport = new ArrayList<>();
            finishedReport.add(projectNumber + ": Finished");
            return finishedReport;
        }

        ArrayList<String> report = new ArrayList<>();
        Double totalTime = 0.;
        Double totalBudget = 0.;
        for (Activity activity : activities) {
            totalTime += activity.getTimeSpent();
            totalBudget += activity.getTimeBudget();
        }
        report.add(projectNumber + ": " + status + " - " + totalTime + "/" + totalBudget);
        for (Activity activity : activities) {
            String status = activity.getStatus();
            report.add(activity.getName() + ": " + status + " - " + activity.getTimeSpent() + "/" + activity.getTimeBudget());

        }

        assert report.size() == activities.size() + 1 : "Report size is incorrect";

        return report;
    }

    //Implicit methods
    //getters
    public Activity getActivity(String activityName) throws ErrorMessage{ //Lukas
        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                return activity;
            }
        }
        throw new ErrorMessage("Activity not found");
    }

    public ArrayList<Activity> getActivities() {
        return this.activities;
    }

    public ArrayList<Activity> getAllActivities(){
        return activities;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public String getStatus() {
        return status;
    }

    //setters
    public void setStatus (String status, Developer requester) throws Exception{ //Freja
        if (!this.projectLeader.equals(requester.getInitials()) && this.projectLeader != null) {
            throw new ErrorMessage("not project leader, status change failed");
        }
        if(status.equals("Finished")){
            for(Activity activity: this.getActivities()){
                if(!activity.getStatus().equals(status)){
                    throw new ErrorMessage("Still ongoing activities, status change failed");
                }
            }
        }
        this.status = status;
    }

}
