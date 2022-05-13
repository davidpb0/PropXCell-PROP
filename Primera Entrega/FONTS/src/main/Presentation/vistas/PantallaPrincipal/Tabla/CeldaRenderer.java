package main.Presentation.vistas.PantallaPrincipal.Tabla;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/*
 * Tabla - CeldaRenderer
 *
 * v0.0.2
 *
 * Joaquim Torra Garcia
 */

public class CeldaRenderer extends DefaultTableCellRenderer {
    Color bg, fg;
    boolean isBold;

    public CeldaRenderer(Color bg, Color fg) {
        super();
        this.bg = bg;
        this.fg = fg;
    }

    public Component getTableCellRendererComponent(JTable table, Object
            value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cell = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        cell.setBackground(bg);
        cell.setForeground(fg);
        return cell;
    }
}
