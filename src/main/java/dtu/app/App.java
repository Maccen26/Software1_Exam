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

        // Add test developers 
        Developer huba = new Developer("huba");
        developers.add(huba);
        Developer thfa = new Developer("thfa");
        developers.add(thfa);
        Developer mahh = new Developer("mahh");
        developers.add(mahh);

        // Add test projects
        Project project0 = new Project("20250");
        projects.add(project0);
        
        // Add test activities
        try{
            project0.assignProjectLeader(thfa, thfa);
            project0.addActivity(thfa, "Activity1", new int[]{1, 2}, new int[]{2025, 2025});
        }
        catch (ErrorMessage e){
            System.out.println(e.getMessage());
        }

        // Set current project number
        this.projectNumber = 1;
    }

    public void addDeveloper(Developer developer) { // Lukas
        // Add a developer to the app
        this.developers.add(developer);
    }

    public void login(Developer developer) throws ErrorMessage{ // Lukas
        // Log in a developer
        if (!hasDeveloper(developer.getInitials())) {
            throw new ErrorMessage("Developer does not exist");
        }
        // if (this.loggedInDeveloper != null) {
        //     throw new ErrorMessage("Another developer is already logged in");
        // }
        this.loggedInDeveloper = developer.getInitials();
    }

    public void createProject() { // Lukas
        // Add a project to the app
        Project newProject = new Project("2025" + projectNumber);
        this.projects.add(newProject);
        projectNumber++;
    }
    public void createProject(Developer projectLeader) { // Lukas
        // Add a project to the app
        Project newProject = new Project("2025" + projectNumber);
        try {
            newProject.assignProjectLeader(projectLeader, projectLeader);
        } catch (ErrorMessage e) {
            System.out.println(e.getMessage());
        }
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

        Activity activity;
        try{
            activity = project.getActivity(activityName);
            return activity;
        } catch (ErrorMessage e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
