package Graph;

public class Node {

    int row;
    int column;
    NodeType nodeType;

    public Node(int row, int column, NodeType nodeType) {
        this.row = row;
        this.column = column;
        this.nodeType = nodeType;
    }

    public Node(int row, int column) {
        this(row, column, NodeType.EMPTY);
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
