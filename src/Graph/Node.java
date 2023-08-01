package Graph;

public class Node implements Comparable<Node> {

    Integer row;
    Integer column;

    Integer weight = 0;
    Integer id;
    private static int nodeCount = 0;
    NodeType nodeType;

    public Node(int row, int column, NodeType nodeType) {
        this.row = row;
        this.column = column;
        this.nodeType = nodeType;
        this.id = nodeCount;
        nodeCount++;
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

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Integer getWeight() { return weight; }

    public void setWeight(Integer weight) { this.weight = weight; }

    @Override
    public int compareTo(Node o) {
        return this.getWeight().compareTo(o.getWeight());
    }
}
