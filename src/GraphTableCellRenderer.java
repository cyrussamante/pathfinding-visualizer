
import Graph.NodeType;

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
        NodeType cellValue = (NodeType) table.getValueAt(row, column);
        cellComponent.setBackground(cellValue.getColor());
        cellComponent.setForeground(cellValue.getColor());

        return cellComponent;
    }
}
