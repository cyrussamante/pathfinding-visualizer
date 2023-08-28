package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class GraphGrid extends JTable {
    private JFrame frame;
    static JTable table;


    public void display(JFrame frame) {
        this.frame = frame;
        createTable();
    }

    private void createTable() {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

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

        (new GraphInputListener()).startListener(table);

        JPanel gridPanel = new JPanel();
        gridPanel.add(table);

        setLayout(new BorderLayout());
        add(gridPanel, BorderLayout.CENTER);
        frame.add(gridPanel);
    }


}
