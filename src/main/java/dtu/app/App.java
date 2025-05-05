package dtu.app;

import java.util.ArrayList;

import dtu.domain.*;

public class App {
    private ArrayList<Project> projects;     //= new ArrayList<Project>();
    private ArrayList<Developer> developers; //= new ArrayList<Developer>();
    private String loggedInDeveloper;
    private int projectNumber; // = 1;
    
    public App() { // Lukas
        // Initialise the projects and developers lists
        this.projects = new ArrayList<Project>();
        this.developers = new ArrayList<Developer>();

        // Add huba 
        Developer huba = new Developer("huba");
        developers.add(huba);

        //Add projectnumber for test
        this.projectNumber = 1;
    }

    public void addDeveloper(Developer developer) { // Lukas
        // Add a developer to the app
        this.developers.add(developer);
    }

    public void login(Developer developer) { // Lukas
        // Log in a developer
        this.loggedInDeveloper = developer.getInitials();
    }

    public void createProject() { // Lukas
        // Add a project to the app
        Project newProject = new Project("2025" + projectNumber);
        this.projects.add(newProject);
        projectNumber++;
    }

    public boolean hasDeveloper(String initials) { // Lukas
        // Check if a developer exists in the app
        for (Developer developer : developers) {
            if (developer.getInitials().equals(initials)) {
                return true;
            }
        }
        return false;
    }

    public Project getProject(String projectNumber) { // Lukas
        // Get a project by its number
        for (Project project : projects) {
            if (project.getProjectNumber().equals(projectNumber)) {
                return project;
            }
        }
        return null;
    }

    public Developer getDeveloper(String developerInitials) //Mads
    {
        for (Developer developer: this.developers) 
        {
            if (developer.getInitials().equals(developerInitials)) 
            {
                return developer;
            }
        }
        return null;
    }

    public Activity getActivity(String activityName, String projectNumber2) //MADS
    {
        Project project = this.getProject(projectNumber2); 
        if (project == null)
        {
            return null;
        }
        Activity activity = project.getActivity(activityName); 

        return activity;
    }
}
