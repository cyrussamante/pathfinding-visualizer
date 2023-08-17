package Algorithms;

import Graph.Graph;
import Graph.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class DepthFirst implements Pathfinder {

    LinkedHashSet<Node> visitedOrder = new LinkedHashSet<>();
    Node endNode;
    public void DFSRecursion(Graph graph, Node node, boolean[] marked) {
        LinkedHashSet<Node> neighbours = new LinkedHashSet<Node>(graph.getNeighbours(node));
        LinkedHashSet<Node> sortedNodes = neighbours.stream()
                .sorted(Comparator.comparingInt(Node::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        for (Node n : sortedNodes) {
            if (!marked[n.getId()]) {
                marked[n.getId()] = true;
                visitedOrder.add(n);
                if (n == endNode) {
                    break;
                }
                DFSRecursion(graph, n, marked);
            }
        }
    }

    @Override
    public ArrayList<LinkedHashSet<Node>> findPath(Graph graph, Node startNode, Node endNode) {
        this.endNode = endNode;
        boolean[] marked = new boolean[graph.getNodeCount()];
        DFSRecursion(graph, startNode, marked);
        ArrayList<LinkedHashSet<Node>> data = new ArrayList<>();
        data.add(visitedOrder);
        data.add(visitedOrder);

        return data;
    }
}
