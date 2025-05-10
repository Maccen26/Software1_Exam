package dtu.ui.buttons;

import javax.swing.*;

import dtu.app.App;
import dtu.domain.Activity;
import dtu.domain.Developer;
import dtu.domain.Project;

import java.awt.*;

public class activtyOverviewButton { //Mads

    App app; 
    public activtyOverviewButton( App app ) //Mads
    {
        this.app = app; 


        JFrame frame = new JFrame("Activity Overview");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Add components to the frame if needed
        JLabel label = new JLabel("Activity Overview", SwingConstants.CENTER);
        frame.add(label, BorderLayout.CENTER);

        frame.setVisible(true);
    }

        public static void main(String[] args) throws Exception //MADS
        {
        App app = new App(); 
        app.createProject();

        //Setting up the app with a single activty for the developer huba
        Developer developer = new Developer("huba");
        app.login(developer);

        app.addActivity("20251", "Activty1", new int[]{10, 20}, new int[]{2025, 2025});
        //app.addDeveloperToActivity("20251", "Activty1", "huba");


        SwingUtilities.invokeLater(() -> new activtyOverviewButton(app));

        }
}