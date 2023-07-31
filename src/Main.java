import GUI.GraphTable;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraphTable().setVisible(true));

    }
}