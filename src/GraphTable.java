import Graph.Node;
import Graph.NodeType;
import Graph.Graph;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

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

        setLayout(new GridBagLayout());
        add(table, new GridBagConstraints());
    }

    private void mouseSelection(int row, int column) {
        Node cellData = (Node) table.getValueAt(row, column);
        NodeType nodeType = cellData.getNodeType();
        Graph tab = ((GraphTableModel)table.getModel()).getGraph();
        System.out.println("Cell clicked: row " + row + " and column " + column);
        System.out.println("Cell Type: " + nodeType);
        System.out.println("Cell Neighbours: " + tab.getNeighbours(tab.getNode(row, column)));
        System.out.println("======================");
        if (nodeType == NodeType.WALL) {
            cellData.setNodeType(NodeType.EMPTY);
        } else if (nodeType == NodeType.EMPTY) {
            cellData.setNodeType(NodeType.WALL);
        }
        // Notify the table model that the cell data has been updated
        ((GraphTableModel) table.getModel()).fireTableCellUpdated(row, column);
    }
}