package Visualization;

import Algorithms.Pathfinder;
import GUI.GraphTableCellRenderer;
import GUI.GraphTableModel;
import Graph.Node;
import Graph.NodeType;
import Graph.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;

public class PathfindingVisualizer {

    public void visualizePathfinder(Graph graph, JTable table, Pathfinder pathfinder, Runnable callback) {
        Node startNode = findNode(graph, NodeType.START);
        Node endNode = findNode(graph, NodeType.END);

        GraphTableCellRenderer.clearPath(graph);

        LinkedHashSet<Node> shortestPath = pathfinder.findPath(graph, startNode, endNode).get(0);
        LinkedHashSet<Node> visitedOrder = pathfinder.findPath(graph, startNode, endNode).get(1);
        shortestPath.remove(startNode);
        shortestPath.remove(endNode);


        Node[] pathArray = shortestPath.toArray(new Node[0]);
        Node[] visitedArray = visitedOrder.toArray(new Node[0]);

        Timer timer = new Timer(25, new ActionListener() {
            private int visitedIndex = 0;
            private int pathIndex = 0;


            @Override
            public void actionPerformed(ActionEvent e) {
                if (visitedIndex!= 0 && visitedArray[visitedIndex-1].getNodeType() == NodeType.NEW) {
                    Node previous = visitedArray[visitedIndex - 1];
                    previous.setNodeType(NodeType.VISITED);
                    // Notify the table model that the cell data has been updated
                    ((GraphTableModel) table.getModel()).fireTableCellUpdated(previous.getRow(), previous.getColumn());

                    // Trigger the table to repaint
                    table.repaint();
                }

                if (visitedIndex < visitedArray.length) {
                    Node path = visitedArray[visitedIndex++];

                    if (path.getNodeType() == NodeType.EMPTY) {
                        path.setNodeType(NodeType.NEW);
                    }

                    // Notify the table model that the cell data has been updated
                    ((GraphTableModel) table.getModel()).fireTableCellUpdated(path.getRow(), path.getColumn());

                    // Trigger the table to repaint
                    table.repaint();
                }
                else if (pathIndex < pathArray.length) {
                    Node path = pathArray[pathIndex++];
                    path.setNodeType(NodeType.PATHWAY);
                    // Notify the table model that the cell data has been updated
                    ((GraphTableModel) table.getModel()).fireTableCellUpdated(path.getRow(), path.getColumn());

                    // Trigger the table to repaint
                    table.repaint();

                } else {
                    // Stop the timer when all iterations are completed
                    ((Timer) e.getSource()).stop();
                    callback.run(); // Callback after visualization is done

                }
            }
        });

        // Start the timer
        timer.start();

    }

    private Node findNode(Graph graph, NodeType nodeType) {
        for (Node node : graph.getNodes()) {
            if (node.getNodeType() == nodeType) {
                return node;
            }
        }
        return null;
    }
}
