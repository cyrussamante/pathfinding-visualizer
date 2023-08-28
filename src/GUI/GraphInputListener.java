package GUI;

import Graph.Graph;
import Graph.Node;
import Graph.NodeType;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GraphInputListener {


    private JTable grid;
    private int lastSelectedRow = -1;
    private int lastSelectedColumn = -1;
    private boolean startSelected;
    private boolean endSelected;
    private int prevStartRow = -1;
    private int prevStartCol = -1;
    private int prevEndRow = -1;
    private int prevEndCol = -1;
    private static boolean visualizationInProgress;

    public void startListener(JTable grid) {
        this.grid = grid;
        if (visualizationInProgress) return;

        grid.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row = grid.getSelectedRow();
                int column = grid.getSelectedColumn();
                mouseSelection(row, column);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                startSelected = false;
                endSelected = false;
            }
        });

        grid.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int row = grid.rowAtPoint(e.getPoint());
                int column = grid.columnAtPoint(e.getPoint());
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
    }

    private void mouseSelection(int row, int column) {
        if (visualizationInProgress) return;
        Node cellData = (Node) grid.getValueAt(row, column);
        NodeType nodeType = cellData.getNodeType();
        Graph graph = ((GraphTableModel)grid.getModel()).getGraph();
        if (nodeType == NodeType.WALL) {
            GraphTableCellRenderer.destroyWall(graph, cellData);
            cellData.setNodeType(NodeType.EMPTY);
        } else if (nodeType != NodeType.START && nodeType != NodeType.END) {
            GraphTableCellRenderer.createWall(graph, cellData);
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
        ((GraphTableModel) grid.getModel()).fireTableCellUpdated(row, column);
    }

    private void moveCheckpoint(int row, int column, NodeType checkpoint) {
        if (visualizationInProgress) return;

        Node cellData = (Node) grid.getValueAt(row, column);
        NodeType nodeType = cellData.getNodeType();
        Node oldCheckpoint;
        if (checkpoint == NodeType.START) {
            oldCheckpoint = (Node) grid.getValueAt(prevStartRow, prevStartCol);
            if (nodeType != NodeType.END) {
                ((GraphTableModel) grid.getModel()).fireTableCellUpdated(prevStartRow, prevStartCol);

                oldCheckpoint.setNodeType(NodeType.EMPTY);
                cellData.setNodeType(NodeType.START);
                prevStartRow = row;
                prevStartCol = column;
            }
        } else if (checkpoint == NodeType.END){
            oldCheckpoint = (Node) grid.getValueAt(prevEndRow, prevEndCol);
            if (nodeType != NodeType.START) {
                ((GraphTableModel) grid.getModel()).fireTableCellUpdated(prevEndRow, prevEndCol);
                oldCheckpoint.setNodeType(NodeType.EMPTY);
                cellData.setNodeType(NodeType.END);
                prevEndRow = row;
                prevEndCol = column;
            }

        }
        ((GraphTableModel) grid.getModel()).fireTableCellUpdated(row, column);

    }

    public static void disable() {
        visualizationInProgress = true;
    }

    public static void enable() {
        visualizationInProgress = false;
    }
}
