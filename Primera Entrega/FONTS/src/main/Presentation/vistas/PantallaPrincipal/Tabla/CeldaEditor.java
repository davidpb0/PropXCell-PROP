package main.Presentation.vistas.PantallaPrincipal.Tabla;

import main.Domain.DomainControllers.ControladorDominio;
import main.Domain.DomainControllers.ControladorHoja;

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

        return new CeldaEditor(field);
    }

    public CeldaEditor(JTextField textField) {
        super(textField);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 0) return null;
        return super.getTableCellEditorComponent(table, ControladorDominio.getControladorDominio().getControladorHoja().getCeldaRef().getContenido(), isSelected, row, column);
    }
}
