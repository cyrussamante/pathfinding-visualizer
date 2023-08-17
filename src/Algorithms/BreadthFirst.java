package Algorithms;

import Graph.Graph;
import Graph.Node;

import java.util.*;

public class BreadthFirst implements Pathfinder {
    LinkedHashSet<Node> visitedOrder = new LinkedHashSet<>();

    @Override
    public ArrayList<LinkedHashSet<Node>> findPath(Graph graph, Node startNode, Node endNode) {
        boolean[] marked = new boolean[graph.getNodeCount()];
        Node[] parent = new Node[graph.getNodeCount()];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(startNode);
        while (!queue.isEmpty()) {
            Node n = queue.remove();
            LinkedHashSet<Node> neighbours = new LinkedHashSet<Node>(graph.getNeighbours(n));
            for (Node neighbour : neighbours) {
                if (!marked[neighbour.getId()]) {
                    marked[neighbour.getId()] = true;
                    visitedOrder.add(neighbour);
                    parent[neighbour.getId()] = n;
                    queue.offer(neighbour);
                }
            }
        }

        // Shortest Path calculation
        ArrayList<Node> finalPath = new ArrayList<>();
        Node currentNode = parent[endNode.getId()];
        while (currentNode != startNode) {
            finalPath.add(currentNode);
            currentNode = parent[currentNode.getId()];
        }
        Collections.reverse(finalPath);
        LinkedHashSet<Node> shortestPath = new LinkedHashSet<>(finalPath);

        ArrayList<LinkedHashSet<Node>> data = new ArrayList<>();
        data.add(new LinkedHashSet<Node>(shortestPath));
        data.add(visitedOrder);
        return data;
    }
}
