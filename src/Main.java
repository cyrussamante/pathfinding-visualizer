import GUI.Visualizer;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Visualizer().setVisible(true));

    }
}