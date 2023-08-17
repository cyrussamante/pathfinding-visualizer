package Algorithms;

import Graph.Node;
import Graph.Graph;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public interface Pathfinder {
    ArrayList<LinkedHashSet<Node>> findPath(Graph graph, Node startNode, Node endNode);

}
