package GUI;

import javax.swing.*;
import java.awt.*;

public class Header implements Handler{
    private final JLabel titleText = new JLabel();
    private final JLabel subtitleText = new JLabel();
    @Override
    public void display(JFrame frame) {
        titleText.setText("Pathfinding Visualizer");
        titleText.setFont(new Font("Arial", Font.BOLD, 24));

        subtitleText.setText("Created by Cyruss Allen Amante");
        subtitleText.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleText);

        JPanel subtitlePanel = new JPanel();
        subtitlePanel.add(subtitleText);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(titlePanel);
        mainPanel.add(subtitlePanel);
        frame.add(mainPanel, BorderLayout.NORTH);
    }
}
