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

        JPanel mainPanel = new JPanel();
        mainPanel.add(titleText);
        mainPanel.add(subtitleText);

        frame.add(mainPanel, BorderLayout.NORTH);

    }
}
