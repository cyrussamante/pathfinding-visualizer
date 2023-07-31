package Algorithms;

import Graph.Node;
import Graph.Graph;

import java.util.Set;

public interface Pathfinder {
    Set<Node> findPath(Graph graph, Node startNode, Node endNode);

}
