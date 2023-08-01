package Algorithms;

import Graph.Node;
import Graph.Graph;

import java.util.LinkedHashSet;
import java.util.Set;

public interface Pathfinder {
    LinkedHashSet<Node> findPath(Graph graph, Node startNode, Node endNode);

}
