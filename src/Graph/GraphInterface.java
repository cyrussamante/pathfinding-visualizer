package Graph;

import java.util.Set;

public interface GraphInterface {

    // Adding and removing nodes
    void addNode(Node node);
    void removeNode(Node node);

    // Adding and removing edges
    void addEdge(Node sourceNode, Node targetNode);
    void removeEdge(Node sourceNode, Node targetNode);

    // Node information
    int getNodeCount();
    Set<Node> getNodes();
    Set<Node> getNeighbours(Node node);
}
