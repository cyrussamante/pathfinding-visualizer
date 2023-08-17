package GUI;

import javax.swing.table.AbstractTableModel;
import Graph.Node;
import Graph.NodeType;
import Graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphTableModel extends AbstractTableModel {

    private final List<List<Node>> graphTable;
    private final Graph graph;

    public GraphTableModel() {
        graph = new Graph();
        graphTable = new ArrayList<>();
        for (int row = 0; row < 30; row++) {
            List<Node> rowData = new ArrayList<>();
            for (int col = 0; col < 60; col++) {
                Node current;
                if (row == 13 && col == 17) {
                    current = new Node(row, col, NodeType.START);
                } else if (row == 13 && col == 42) {
                    current = new Node(row, col, NodeType.END);
                } else {
                    current = new Node(row, col);
                }
                rowData.add(current);
                graph.addNode(current);
            }
            graphTable.add(rowData);
        }
        initializeNeighbours();
    }
    @Override
    public int getRowCount() {
        return 30;
    }

    @Override
    public int getColumnCount() {
        return 60;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return graphTable.get(rowIndex).get(columnIndex);
    }

    private void initializeNeighbours() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 60; j++) {
                Node node = graph.getNode(i, j);
//                GraphTable.calculateNeighbours(graph, node, i, j);
            }
        }
 }

    public Graph getGraph() {
        return graph;
    }
}
