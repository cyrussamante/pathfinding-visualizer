package Algorithms;

import Graph.Node;
import Graph.Graph;

import java.util.*;

public class Dijkstra {

    @Override
    public LinkedHashSet<Node> findPath(Graph graph, Node startNode, Node endNode) {
        // Initialize path and cost arrays
        Node[] paths = new Node[graph.getNodes().size()];
        Integer[] cost = new Integer[graph.getNodes().size()];
        paths[startNode.getId()] = startNode;
        cost[startNode.getId()] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(startNode);
        while (!queue.isEmpty()) {
            // Remove the lowest priority node
            Node current = queue.remove();
            for (Node node : graph.getNeighbours(current)) {
                // Current weighting for edge
                Integer possibleWeight = cost[current.getId()] + graph.findEdge(current, node).getWeight();
                if (cost[node.getId()] == null || possibleWeight <= cost[node.getId()]) {
                    paths[node.getId()] = current;
                    cost[node.getId()] = possibleWeight;
                    // Update node to new priority
                    node.setWeight(possibleWeight);
                    queue.offer(node);
                }
            }
        }
        return getShortestPath(startNode, endNode, paths);
    }

    /**
     * Iterates through the array that Dijkstra calculated and returns the one path from two specified nodes
     * @param startNode the node you want to start at
     * @param endNode the node you want to reach
     * @param paths the list of the shortest paths from the start node to all other nodes
     * @return a list of nodes that make up the shortest path
     */
    private static LinkedHashSet<Node> getShortestPath(Node startNode, Node endNode, Node[] paths) {
        Set<Node> shortestPath = new LinkedHashSet<>();
        Node currentNode = paths[endNode.getId()];
        // If a path exists from the start and end node
        if (currentNode != null) {
            shortestPath.add(endNode);
            while (currentNode != null && currentNode != startNode) {
                // Get the node stored in the position
                shortestPath.add(currentNode);
                currentNode = paths[currentNode.getId()];
            }
            shortestPath.add(startNode);
        }
        List<Node> path = new ArrayList<>(shortestPath);
        Collections.reverse(path);
        return new LinkedHashSet<>(path);
    }
}
