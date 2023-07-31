
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
//
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//
//        JTable table = new JTable(new GraphTableModel());
//        table.setTableHeader(null);
//        table.setRowSelectionAllowed(false);
//
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(new GraphTableCellRenderer());
//        }
//
//        int cellSize = 20; // Set your desired cell size (both width and height)
//        table.setRowHeight(cellSize);
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setPreferredWidth(cellSize);
//        }
//
//        table.setShowGrid(true);
//        table.setGridColor(new Color(218, 218, 218));
//        table.setAutoResizeMode(0);
//
//        JFrame frame = new JFrame("Pathfinding Visualizer");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JScrollPane scrollPane = new JScrollPane(table);
//        frame.setLocationRelativeTo(null);
//        frame.add(scrollPane);
//
//        frame.setSize(1400, 800);
//        frame.setMinimumSize(new Dimension(1200,800));
//        frame.setLayout(new GridBagLayout());
//        frame.add(table, new GridBagConstraints());


        SwingUtilities.invokeLater(() -> new GraphTable().setVisible(true));

    }
}