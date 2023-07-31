
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBackground(Color.blue);

        JTable table = new JTable(new GraphTableModel());
        table.setTableHeader(null);
        table.setRowSelectionAllowed(false);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new GraphTableCellRenderer());
        }
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);

        JFrame frame = new JFrame("Pathfinding Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(40,40));
        scrollPane.setMinimumSize(new Dimension(40,40));
        frame.add(scrollPane);

        frame.setSize(1200, 800);

        frame.setVisible(true);
    }
}