package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ControlElementHandler implements Handler {
    private JComboBox<String> dropdownMenu;
    private JFrame frame;
    private JPanel dropdownPanel;
    private JPanel buttonRowPanel;
    private final JButton visualizeButton = new JButton();
    private final JButton clearBoardButton = new JButton();
    private final JButton clearPathButton = new JButton();

    @Override
    public void display(JFrame frame) {
        this.frame = frame;

        createButtons();
        createDropdownMenu();

        // Create a main panel with a vertical BoxLayout to stack the two rows
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(dropdownPanel);
        mainPanel.add(buttonRowPanel);

        frame.add(mainPanel, BorderLayout.SOUTH);
    }


    private void createDropdownMenu() {
        configureDropdownMenu();

        // Use a FlowLayout with CENTER alignment for the dropdown menu
        dropdownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dropdownPanel.add(dropdownMenu);
    }
    private void createButtons() {
        // Use a FlowLayout with CENTER alignment for the buttons
        buttonRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        configureClearBoardButton();
        configureVisualizeButton();
        configureClearPathButton();
    }

    private void configureDropdownMenu() {
        String[] algorithms = {"Algorithms", "Dijkstra", "Depth-first Search", "Breadth-first Search"};
        dropdownMenu = new JComboBox<>(algorithms);
        dropdownMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selection = dropdownMenu.getItemAt(dropdownMenu.getSelectedIndex());
                if (!Objects.equals(selection, dropdownMenu.getItemAt(0))) {
                    visualizeButton.setText("Visualize " + selection + "!");
                } else {
                    visualizeButton.setText("Visualize!");
                }
            }
        });
    }

    private void configureVisualizeButton() {
        visualizeButton.setText("Visualize!");
        buttonRowPanel.add(visualizeButton);
        visualizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selection = dropdownMenu.getItemAt(dropdownMenu.getSelectedIndex());
                if (Objects.equals(selection, "Dijkstra")) {
//                    visualizePathfinder(graph, new Dijkstra());
                } else if ((Objects.equals(selection, "Depth-first Search"))){
//                    visualizePathfinder(graph, new DepthFirst());
                } else if ((Objects.equals(selection, "Breadth-first Search"))) {
//                    visualizePathfinder(graph, new BreadthFirst());
                } else {
                    JOptionPane.showMessageDialog(frame, "You must choose an algorithm to visualize!");
                }

            }
        });
    }


    private void configureClearBoardButton() {
        clearBoardButton.setText("Clear Board");
        buttonRowPanel.add(clearBoardButton);
        clearBoardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                clearBoard(graph);
            }
        });
    }

    private void configureClearPathButton() {
        clearPathButton.setText("Clear Path");
        buttonRowPanel.add(clearPathButton);

        clearPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                clearPath(graph);
            }
        });
    }
}
