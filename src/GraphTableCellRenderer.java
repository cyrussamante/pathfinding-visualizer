import Graph.Node;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class GraphTableCellRenderer extends DefaultTableCellRenderer {

    public GraphTableCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Node cellValue = (Node) table.getValueAt(row, column);
        cellComponent.setBackground(cellValue.getNodeType().getColor());
        cellComponent.setForeground(cellValue.getNodeType().getColor());

        return cellComponent;
    }
}
