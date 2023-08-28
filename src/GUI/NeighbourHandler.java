package GUI;

import Graph.Graph;
import Graph.Node;

public class NeighbourHandler {

    static void calculateNeighbours(Graph graph, Node node, int row, int column) {
        if (row > 0) {
            graph.addEdge(node, graph.getNode(row - 1, column)); // Up
        }
        if (row < 29) {
            graph.addEdge(node, graph.getNode(row + 1, column)); // Down
        }
        if (column > 0) {
            graph.addEdge(node, graph.getNode(row, column-1)); // Left
        }
        if (column < 59) {
            graph.addEdge(node, graph.getNode(row, column+1)); // Right
        }
    }

    static void resetNeighbours(Graph graph) {
        for (Node node : graph.getNodes()) {
            for (Node node2 : graph.getNodes()) {
                if (graph.getNeighbours(node).contains(node2)) {
                    graph.removeEdge(node, node2);
                }
            }
        }
        for (Node node : graph.getNodes()) {
            calculateNeighbours(graph, node, node.getRow(), node.getColumn());
        }
    }
}
