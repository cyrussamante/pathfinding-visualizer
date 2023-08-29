package Visualization;

import GUI.ControlElementHandler;
import GUI.GraphGrid;
import GUI.Header;

import javax.swing.*;
import java.awt.*;

public class Visualizer extends JFrame {
    public Visualizer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pathfinding Visualizer");

        setSize(1400, 800);
        setMinimumSize(new Dimension(1200,800));
        setMaximumSize(new Dimension(1200,800));
        Header header = new Header();
        header.display(this);

        GraphGrid graphGrid = new GraphGrid();
        graphGrid.display(this);

        ControlElementHandler controlElementHandler = new ControlElementHandler();
        controlElementHandler.display(this);
    }
}
