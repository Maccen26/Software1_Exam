package dtu.app;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import dtu.domain.*;
import io.cucumber.java.PendingException;

public class App {
    private ArrayList<Project> projects;     //= new ArrayList<Project>();
    private ArrayList<Developer> developers; //= new ArrayList<Developer>();
    private Developer loggedInDeveloper;
    private int projectNumber; // = 1;
    
    public App() { // Lukas
        // Initialise the projects and developers lists
        this.projects = new ArrayList<Project>();
        this.developers = new ArrayList<Developer>();

        // Add test developers 
        Developer huba = new Developer("huba");
        developers.add(huba);

        // Set current project number
        this.projectNumber = 1;
    }

    public void addDeveloper(Developer developer) { // Lukas
        // Add a developer to the app
        this.developers.add(developer);
    }

    public void addDeveloperToActivity(String projectNumber, String activityName, String requesterInitails, String developerInitials) throws Exception { //Johan
        Project project = getProject(projectNumber); 
        Developer developer = getDeveloper(developerInitials);
        Developer requester = getDeveloper(requesterInitails);
        project.addDeveloperToActivity(activityName, requester, developer); 
    }

    public void login(Developer developer) throws Exception, ErrorMessage{ // Lukas
        // Log in a developer
        if (!hasDeveloper(developer.getInitials())) {
            throw new ErrorMessage("Developer does not exist");
        }
        this.loggedInDeveloper = developer;
    }

    public void createProject() { // Lukas
        Project newProject = new Project("2025" + projectNumber);
        this.projects.add(newProject);
        projectNumber++;
    }
    //Overload
    public void createProject(Developer projectLeader) { // Lukas
        Project newProject = new Project("2025" + projectNumber);
        try {
            newProject.assignProjectLeader(projectLeader, projectLeader);
        } catch (ErrorMessage e) {
            System.out.println(e.getMessage());
        }
        this.projects.add(newProject);
        projectNumber++;
    }

    public void addActivity(String projectNumber, String activityName, int[] yearplan, int[] weekplan) throws Exception { //Thomas
        getProject(projectNumber).addActivity(this.loggedInDeveloper, activityName, weekplan, yearplan);
    }

    public Project getProject(String projectNumber){ // Lukas
        for (Project project : projects) {
            if (project.getProjectNumber().equals(projectNumber)) {
                return project;
            }
        }
        return null;
    }

    public ArrayList<Project> getProjects() { // Lukas
        return this.projects;
    }

    public Developer getDeveloper(String developerInitials) throws Exception{ //Mads
        for (Developer developer: this.developers){
            if (developer.getInitials().equals(developerInitials)){
                return developer;
            }
        }
        throw new Exception("Developer not contained in app");
    }

    public ArrayList<Activity> getActivitesForDeveloper(String developerInitials) throws Exception { //Mads
        Developer developer = getDeveloper(developerInitials);
        return developer.getAssignedActivities();
    }

    public Developer getLoggedInDeveloper() { // Lukas
        return this.loggedInDeveloper;
    }

    public boolean hasProject(String projectNumber) { // Lukas
        for (Project project : projects) {
            if (project.getProjectNumber().equals(projectNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDeveloper(String initials) throws Exception{ // Lukas
        for (Developer developer : developers) {
            if (developer.getInitials().equals(initials)) {
                return true;
            }
        }
        throw new Exception("Developer not contained in app");
    }
}