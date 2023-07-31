package Graph;

public class Node {

    int row;
    int column;
    NodeType nodeType;

    public Node(int row, int column) {
        this.row = row;
        this.column = column;
        this.nodeType = NodeType.EMPTY;
    }

    public Node(int row, int column, NodeType nodeType) {
        this.row = row;
        this.column = column;
        this.nodeType = nodeType;
    }

    public NodeType getNodeType() {
        return nodeType;
    }
}
