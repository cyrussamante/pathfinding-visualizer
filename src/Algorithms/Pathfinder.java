package Algorithms;

import Graph.Node;
import Graph.Graph;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public interface Pathfinder {
    ArrayList<LinkedHashSet<Node>> findPath(Graph graph, Node startNode, Node endNode);

}
