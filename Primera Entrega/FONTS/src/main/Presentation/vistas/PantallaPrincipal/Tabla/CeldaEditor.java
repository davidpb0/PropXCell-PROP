package main.Presentation.vistas.PantallaPrincipal.Tabla;

import javax.swing.*;
import java.awt.*;

/*
 * Tabla - CeldaEditor
 *
 * v0.0.4
 *
 * Joaquim Torra Garcia
 */

public class CeldaEditor extends DefaultCellEditor {
    public static CeldaEditor make(String content) {
        JTextField field = new JTextField();
        field.setBorder(null);
        System.out.println("make");
        field.setText(content);
        return new CeldaEditor(field);
    }

    public CeldaEditor(JTextField textField) {
        super(textField);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}
