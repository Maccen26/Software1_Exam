package dtu.ui.frameInFrames;

import java.util.ArrayList;

import java.awt.*;
import javax.swing.*;

import dtu.app.*;
import dtu.domain.*;

public class GetReportFrame extends JFrame{
    public GetReportFrame (App app, Project project) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setLayout(null);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        JLabel title = new JLabel("Report");
        title.setBounds(0, 20, 200, 30);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        add(title);

        JLabel reportLabel = new JLabel();
        ArrayList<String> report = new ArrayList<>();
        try {
            report = project.getReport(app.getLoggedInDeveloper());
        } catch (ErrorMessage e) {
            reportLabel.setText(e.getMessage());
        }
        // Build the HTML content for the report
        StringBuilder reportContent = new StringBuilder("<html>");
        for (String line : report) {
            reportContent.append(line).append("<br>");
        }
        reportContent.append("</html>");
        reportLabel.setText(reportContent.toString());

        reportLabel.setBounds(10, 10, 200, 200);
        reportLabel.setHorizontalAlignment(SwingConstants.CENTER);
        reportLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(reportLabel);

        setVisible(true);
    }
}
