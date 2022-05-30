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
    boolean isBold, isItalic;

    public CeldaRenderer(Color bg, Color fg, boolean b, boolean i) {
        super();
        this.bg = bg;
        this.fg = fg;
        this.isBold = b;
        this.isItalic = i;
    }

    public Component getTableCellRendererComponent(JTable table, Object
            value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cell = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        cell.setBackground(bg);
        cell.setForeground(fg);
        Font f = new Font("Arial", Font.PLAIN, 12);
        if (isBold && isItalic) f = f.deriveFont(Font.BOLD | Font.ITALIC);
        else if (isBold) f = f.deriveFont(Font.BOLD);
        else if (isItalic) f = f.deriveFont(Font.ITALIC);
        cell.setFont(f);
        if (isSelected) {
            cell.setBackground(new Color(211, 220, 253));
            cell.setForeground(Color.BLACK);
        }
        return cell;
    }
}
