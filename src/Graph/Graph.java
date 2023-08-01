package Graph;

import java.util.*;

public class Graph implements GraphInterface{

    Map<Node, Set<Node>> adjacencyList = new HashMap<>();

    @Override
    public void addNode(Node node) {
        Set<Node> edges = new HashSet<>();
        adjacencyList.put(node, edges);
    }

    @Override
    public void removeNode(Node node) {
        for (Node current : adjacencyList.keySet()) {
            Set<Node> connectedNodes = adjacencyList.get(current);
            if (connectedNodes.contains(node))
                removeEdge(node, current);
        }
        adjacencyList.remove(node);
    }

    @Override
    public void addEdge(Node sourceNode, Node targetNode) {
        Set<Node> sourceNodeEdges = adjacencyList.get(sourceNode);
        Set<Node> targetNodeEdges = adjacencyList.get(targetNode);
        sourceNodeEdges.add(targetNode);
        targetNodeEdges.add(sourceNode);
    }

    @Override
    public void removeEdge(Node sourceNode, Node targetNode) {
        Set<Node> sourceNodeEdges = adjacencyList.get(sourceNode);
        Set<Node> targetNodeEdges = adjacencyList.get(targetNode);
        sourceNodeEdges.remove(targetNode);
        targetNodeEdges.remove(sourceNode);
    }

    @Override
    public int getNodeCount() {
        return adjacencyList.size();
    }

    @Override
    public Set<Node> getNodes() {
        return adjacencyList.keySet();
    }

    @Override
    public Set<Node> getNeighbours(Node node) {
        return adjacencyList.get(node);
    }

    public Node getNode(int row, int col) {
        for (Node node : adjacencyList.keySet()) {
            if (node.getRow() == row && node.getColumn() == col) {
                return node;
            }
        }
        return null;
    }
}
