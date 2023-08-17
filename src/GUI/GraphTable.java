package GUI;

import Algorithms.BreadthFirst;
import Algorithms.DepthFirst;
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
import java.util.Objects;

public class GraphTable extends JFrame {

    private JTable table;
    private int lastSelectedRow = -1;
    private int lastSelectedColumn = -1;

    private boolean startSelected;
    private boolean endSelected;


    private int prevStartRow = -1;
    private int prevStartCol = -1;

    private int prevEndRow = -1;
    private int prevEndCol = -1;
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

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                // Add your code here to handle mouse button release
                startSelected = false;
                endSelected = false;
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
                    if (startSelected) {
                        moveCheckpoint(row, column, NodeType.START);
                    } else if (endSelected){
                        moveCheckpoint(row, column, NodeType.END);
                    } else {
                        mouseSelection(row, column);
                    }

                }
            }
        });

        JPanel buttonPanel = new JPanel();
        String[] algorithms = {"Choose an algorithm", "Dijkstra", "Depth-first Search", "Breadth-first Search"};
        JComboBox<String> jComboBox = new JComboBox<>(algorithms);

        JButton visualizeButton = new JButton("Visualize!");
        JButton clearBoardButton = new JButton("Clear Board");
        JButton clearPathButton = new JButton("Clear Path");

        Graph graph = ((GraphTableModel)table.getModel()).getGraph();

        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selection = jComboBox.getItemAt(jComboBox.getSelectedIndex());
                if (!Objects.equals(selection, jComboBox.getItemAt(0))) {
                    visualizeButton.setText("Visualize " + selection + "!");
                } else {
                    visualizeButton.setText("Visualize!");
                }
            }
        });
        visualizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selection = jComboBox.getItemAt(jComboBox.getSelectedIndex());
                if (Objects.equals(selection, "Dijkstra")) {
                    visualizePathfinder(graph, new Dijkstra());
                } else if ((Objects.equals(selection, "Depth-first Search"))){
                    visualizePathfinder(graph, new DepthFirst());
                } else if ((Objects.equals(selection, "Breadth-first Search"))) {
                    visualizePathfinder(graph, new BreadthFirst());
                } else {
                    mustChoose();
                }

            }
        });

        clearBoardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearBoard(graph);
            }
        });

        clearPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearPath(graph);
            }
        });

        JLabel titleLabel = new JLabel("Pathfinding Visualizer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel gridPanel = new JPanel();
        gridPanel.add(titleLabel);
        gridPanel.add(table);

        setLayout(new BorderLayout());
        add(gridPanel, BorderLayout.CENTER);



        buttonPanel.add(clearBoardButton);
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(jComboBox);
        buttonPanel.add(visualizeButton);
        buttonPanel.add(clearPathButton);






    }

    private void mustChoose() {
        JOptionPane.showMessageDialog(this, "You must choose an algorithm to visualize!");
    }

    private void moveCheckpoint(int row, int column, NodeType checkpoint) {
        Node cellData = (Node) table.getValueAt(row, column);
        NodeType nodeType = cellData.getNodeType();
            Node oldCheckpoint;
            if (checkpoint == NodeType.START) {
                oldCheckpoint = (Node) table.getValueAt(prevStartRow, prevStartCol);
                if (nodeType != NodeType.END) {
                    ((GraphTableModel) table.getModel()).fireTableCellUpdated(prevStartRow, prevStartCol);

                    oldCheckpoint.setNodeType(NodeType.EMPTY);
                    cellData.setNodeType(NodeType.START);
                    prevStartRow = row;
                    prevStartCol = column;
                }
            } else if (checkpoint == NodeType.END){
                oldCheckpoint = (Node) table.getValueAt(prevEndRow, prevEndCol);
                if (nodeType != NodeType.START) {
                    ((GraphTableModel) table.getModel()).fireTableCellUpdated(prevEndRow, prevEndCol);
                    oldCheckpoint.setNodeType(NodeType.EMPTY);
                    cellData.setNodeType(NodeType.END);
                    prevEndRow = row;
                    prevEndCol = column;
                }

            }
        ((GraphTableModel) table.getModel()).fireTableCellUpdated(row, column);

    }

    private void mouseSelection(int row, int column) {
        Node cellData = (Node) table.getValueAt(row, column);
        NodeType nodeType = cellData.getNodeType();
        Graph graph = ((GraphTableModel)table.getModel()).getGraph();
        if (nodeType == NodeType.WALL) {
            destroyWall(graph, cellData);
            cellData.setNodeType(NodeType.EMPTY);
        } else if (nodeType != NodeType.START && nodeType != NodeType.END) {
            createWall(graph, cellData);
            cellData.setNodeType(NodeType.WALL);
        }
        else if (nodeType == NodeType.START) {
            prevStartRow = row;
            prevStartCol = column;
            startSelected = true;
        } else {
            prevEndRow = row;
            prevEndCol = column;
            endSelected = true;
        }
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

    static void calculateNeighbours(Graph graph, Node node, int row, int column) {
        if (row > 0) {
            graph.addEdge(node, graph.getNode(row - 1, column)); // Up
        }
        if (row < 29) {
            graph.addEdge(node, graph.getNode(row + 1, column)); // Down
        }
        if (column > 0) {
            graph.addEdge(node, graph.getNode(row, column-1)); // Left
        }
        if (column < 59) {
            graph.addEdge(node, graph.getNode(row, column+1)); // Right
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

    private void clearPath(Graph graph) {
        for (Node node : graph.getNodes()) {
            if (node.getNodeType() == NodeType.PATHWAY || node.getNodeType() == NodeType.VISITED) {
                node.setNodeType(NodeType.EMPTY);
                ((GraphTableModel) table.getModel()).fireTableCellUpdated(node.getRow(), node.getColumn());
                table.repaint();
            }
        }
    }

    private void resetNeighbours(Graph graph) {
        for (Node node : graph.getNodes()) {
            for (Node node2 : graph.getNodes()) {
                if (graph.getNeighbours(node).contains(node2)) {
                    graph.removeEdge(node, node2);
                }
            }
        }
        for (Node node : graph.getNodes()) {
            calculateNeighbours(graph, node, node.getRow(), node.getColumn());
        }
    }

    private void clearBoard(Graph graph) {
        resetNeighbours(graph);
        for (Node node : graph.getNodes()) {
            if (node.getNodeType() != NodeType.START && node.getNodeType() != NodeType.END) {
                node.setNodeType(NodeType.EMPTY);
                ((GraphTableModel) table.getModel()).fireTableCellUpdated(node.getRow(), node.getColumn());
                table.repaint();
            }
        }
    }

    private void visualizePathfinder(Graph graph, Pathfinder pathfinder) {
        Node startNode = findNode(graph, NodeType.START);
        Node endNode = findNode(graph, NodeType.END);

        clearPath(graph);

        LinkedHashSet<Node> shortestPath = pathfinder.findPath(graph, startNode, endNode).get(0);
        LinkedHashSet<Node> visitedOrder = pathfinder.findPath(graph, startNode, endNode).get(1);
        shortestPath.remove(startNode);
        shortestPath.remove(endNode);


        Node[] pathArray = shortestPath.toArray(new Node[0]);
        Node[] visitedArray = visitedOrder.toArray(new Node[0]);

        Timer timer = new Timer(25, new ActionListener() {
            private int visitedIndex = 0;
            private int pathIndex = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (visitedIndex < visitedArray.length) {
                    Node path = visitedArray[visitedIndex++];
                    if (path.getNodeType() == NodeType.EMPTY) {
                        path.setNodeType(NodeType.VISITED);
                    }
                    // Notify the table model that the cell data has been updated
                    ((GraphTableModel) table.getModel()).fireTableCellUpdated(path.getRow(), path.getColumn());

                    // Trigger the table to repaint
                    table.repaint();
                }
                else if (pathIndex < pathArray.length) {
                    Node path = pathArray[pathIndex++];
                    path.setNodeType(NodeType.PATHWAY);
                    // Notify the table model that the cell data has been updated
                    ((GraphTableModel) table.getModel()).fireTableCellUpdated(path.getRow(), path.getColumn());

                    // Trigger the table to repaint
                    table.repaint();

                } else {
                    // Stop the timer when all iterations are completed
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        // Start the timer
        timer.start();
    }
}
