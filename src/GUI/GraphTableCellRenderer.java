package GUI;

import Graph.Graph;
import Graph.Node;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import Graph.NodeType;

public class GraphTableCellRenderer extends DefaultTableCellRenderer {

    static JTable table;

    public GraphTableCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        GraphTableCellRenderer.table = table;
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
        NeighbourHandler.calculateNeighbours(graph, wallNode, row, column);
    }

    public static void clearPath(Graph graph) {
        for (Node node : graph.getNodes()) {
            if (node.getNodeType() == NodeType.PATHWAY || node.getNodeType() == NodeType.VISITED) {
                node.setNodeType(NodeType.EMPTY);
                ((GraphTableModel) table.getModel()).fireTableCellUpdated(node.getRow(), node.getColumn());
                table.repaint();
            }
        }
    }

    static void clearBoard(Graph graph) {
        NeighbourHandler.resetNeighbours(graph);
        for (Node node : graph.getNodes()) {
            if (node.getNodeType() != NodeType.START && node.getNodeType() != NodeType.END) {
                node.setNodeType(NodeType.EMPTY);
                ((GraphTableModel) table.getModel()).fireTableCellUpdated(node.getRow(), node.getColumn());
                table.repaint();
            }
        }
    }
}
