package Algorithms;

import Graph.Node;
import Graph.NodeType;
import Graph.Graph;

import java.util.*;

public class Dijkstra implements Pathfinder {

    @Override
    public ArrayList<LinkedHashSet<Node>> findPath(Graph graph, Node startNode, Node endNode) {
        LinkedHashSet<Node> visitedOrder = new LinkedHashSet<>();
        Node[] paths = new Node[graph.getNodes().size()];
        Integer[] cost = new Integer[graph.getNodes().size()];
        paths[startNode.getId()] = startNode;
        cost[startNode.getId()] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> cost[node.getId()]));
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current == endNode) {
                break; // Early termination if endNode is reached
            }

            for (Node node : graph.getNeighbours(current)) {
                int possibleWeight = cost[current.getId()] + 1;
                if (cost[node.getId()] == null || possibleWeight < cost[node.getId()]) {
                    paths[node.getId()] = current;
                    cost[node.getId()] = possibleWeight;
                    node.setWeight(possibleWeight);
                    queue.offer(node);
                }

                if (node.getNodeType() != NodeType.START && node.getNodeType() != NodeType.END) {
                    visitedOrder.add(node);
                }
            }
        }

        ArrayList<LinkedHashSet<Node>> data = new ArrayList<>();
        data.add(getShortestPath(startNode, endNode, paths));
        data.add(visitedOrder);
        return data;
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
