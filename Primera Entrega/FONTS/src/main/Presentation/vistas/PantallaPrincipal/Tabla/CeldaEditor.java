package main.Presentation.vistas.PantallaPrincipal.Tabla;

import main.Domain.DomainControllers.ControladorDominio;

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
    public static CeldaEditor make(String currentContent) {
        JTextField field = new JTextField();
        field.setBorder(null);

        return new CeldaEditor(field);
    }

    public CeldaEditor(JTextField textField) {
        super(textField);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return super.getTableCellEditorComponent(table, ControladorDominio.getControladorDominio().getControladorHoja().getCeldaRef().getContenido(), isSelected, row, column);
    }
}
