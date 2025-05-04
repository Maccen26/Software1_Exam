package dtu.app;

import java.util.ArrayList;
import java.util.List;

import dtu.domain.*;

public class App {
    private ArrayList<Project> projects = new ArrayList<Project>();
    private ArrayList<Developer> developers = new ArrayList<Developer>();
    private String loggedInDeveloper;
    
    public App(ArrayList<Project> projects, ArrayList<Developer> developers) {
        // Initialise the projects and developers lists
        this.projects = projects;
        this.developers = developers;

        // Add huba
        Developer huba = new Developer("huba");
        developers.add(huba);
    }

    public void addDeveloper(Developer developer) {
        // Add a developer to the app
        this.developers.add(developer);
    }

    public void login(Developer developer) {
        // Log in a developer
        this.loggedInDeveloper = developer.getInitials();
    }

    public void addProject(Project project) {
        // Add a project to the app
        this.projects.add(project);
    }

    public boolean hasDeveloper(String initials) {
        // Check if a developer exists in the app
        for (Developer developer : developers) {
            if (developer.getInitials().equals(initials)) {
                return true;
            }
        }
        return false;
    }

    public Project getProject(String projectNumber) {
        // Get a project by its number
        for (Project project : projects) {
            if (project.getProjectNumber().equals(projectNumber)) {
                return project;
            }
        }
        return null;
    }
}
