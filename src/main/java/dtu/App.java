package dtu;

import java.util.ArrayList;
import java.util.List;

public class App {
    private ArrayList<Project> projects = new ArrayList<Project>();
    private ArrayList<Developer> developers = new ArrayList<Developer>();
    
    public App(ArrayList<Project> projects, ArrayList<Developer> developers) {
        // Initialise the projects and developers lists
        this.projects = projects;
        this.developers = developers;

        // Add huba
        Developer huba = new Developer("huba");
        developers.add(huba);
    }
}
