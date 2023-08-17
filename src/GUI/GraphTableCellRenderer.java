package GUI;

import Graph.Graph;
import Graph.Node;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class GraphTableCellRenderer extends DefaultTableCellRenderer {

    public GraphTableCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Node cellValue = (Node) table.getValueAt(row, column);
        cellComponent.setBackground(cellValue.getNodeType().getColor());
        cellComponent.setForeground(cellValue.getNodeType().getColor());

        return cellComponent;
    }

    static void createWall(Graph graph, Node wallNode) {
        for (Node currentNode : graph.getNodes()) {
            if (currentNode != wallNode) {
                graph.removeEdge(currentNode, wallNode);
            }
        }
    }

    static void destroyWall(Graph graph, Node wallNode) {
        int row = wallNode.getRow();
        int column = wallNode.getColumn();
//        calculateNeighbours(graph, wallNode, row, column);
    }
}
