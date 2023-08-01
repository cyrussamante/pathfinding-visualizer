package GUI;

import Algorithms.Dijkstra;
import Algorithms.Pathfinder;
import Graph.Node;
import Graph.NodeType;
import Graph.Graph;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashSet;

public class GraphTable extends JFrame {

    private JTable table;
    private int lastSelectedRow = -1;
    private int lastSelectedColumn = -1;
    public GraphTable() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pathfinding Visualizer");

        setSize(1400, 800);
        setMinimumSize(new Dimension(1200,800));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        table = new JTable(new GraphTableModel());
        table.setTableHeader(null);
        table.setRowSelectionAllowed(false);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new GraphTableCellRenderer());
        }

        int cellSize = 20; // Set your desired cell size (both width and height)
        table.setRowHeight(cellSize);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(cellSize);
        }

        table.setShowGrid(true);
        table.setGridColor(new Color(218, 218, 218));
        table.setAutoResizeMode(0);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                mouseSelection(row, column);
            }
        });

        table.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                if (row != lastSelectedRow || column != lastSelectedColumn) {
                    lastSelectedRow = row;
                    lastSelectedColumn = column;
                    mouseSelection(row, column);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        setLocationRelativeTo(null);
        add(scrollPane);

        JButton button = new JButton();
        add(button);
        Graph graph = ((GraphTableModel)table.getModel()).getGraph();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateDijkstra(graph);
//                System.out.println("hey");
            }
        });

        setLayout(new GridBagLayout());
        add(table, new GridBagConstraints());
    }

    private void mouseSelection(int row, int column) {
        Node cellData = (Node) table.getValueAt(row, column);
        NodeType nodeType = cellData.getNodeType();
        Graph graph = ((GraphTableModel)table.getModel()).getGraph();
        if (nodeType == NodeType.WALL) {
            destroyWall(graph, cellData);
            cellData.setNodeType(NodeType.EMPTY);
        } else if (nodeType == NodeType.EMPTY) {
            createWall(graph, cellData);
            cellData.setNodeType(NodeType.WALL);
        }
        System.out.println("Cell clicked: row " + row + " and column " + column);
        System.out.println("Cell Type: " + cellData.getNodeType());
        System.out.println("Cell Neighbours: " + graph.getNeighbours(graph.getNode(row, column)));
        System.out.println("======================");
        // Notify the table model that the cell data has been updated
        ((GraphTableModel) table.getModel()).fireTableCellUpdated(row, column);
    }

    private void createWall(Graph graph, Node wallNode) {
        for (Node currentNode : graph.getNodes()) {
            if (currentNode != wallNode) {
                graph.removeEdge(currentNode, wallNode);
            }
        }
    }

    private void destroyWall(Graph graph, Node wallNode) {
        int row = wallNode.getRow();
        int column = wallNode.getColumn();
        calculateNeighbours(graph, wallNode, row, column);
    }

    static void calculateNeighbours(Graph graph, Node wallNode, int row, int column) {
        if (row > 0) {
            graph.addEdge(wallNode, graph.getNode(row - 1, column)); // Up
        }
        if (row < 29) {
            graph.addEdge(wallNode, graph.getNode(row + 1, column)); // Down
        }
        if (column > 0) {
            graph.addEdge(wallNode, graph.getNode(row, column-1)); // Left
        }
        if (column < 59) {
            graph.addEdge(wallNode, graph.getNode(row, column+1)); // Right
        }
    }

    private Node findNode(Graph graph, NodeType nodeType) {
        for (Node node : graph.getNodes()) {
            if (node.getNodeType() == nodeType) {
                return node;
            }
        }
        return null;
    }
    private void calculateDijkstra(Graph graph) {
        Node startNode = findNode(graph, NodeType.START);
        Node endNode = findNode(graph, NodeType.END);

        Pathfinder pathfinder = new Dijkstra();
        LinkedHashSet<Node> shortestPath = pathfinder.findPath(graph, startNode, endNode);
        shortestPath.remove(startNode);
        shortestPath.remove(endNode);
        for (Node path : shortestPath) {
            path.setNodeType(NodeType.PATHWAY);
            ((GraphTableModel) table.getModel()).fireTableCellUpdated(path.getRow(), path.getColumn());
        }
    }
}
