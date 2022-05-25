package main.Presentation.vistas.PantallaPrincipal.Tabla;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
 * Tabla - Listener
 *
 * v0.0.4
 *
 * Joaquim Torra Garcia
 */

public class TablaListener implements PropertyChangeListener, Runnable {
    private final JTable table;
    private Action action;

    private int fila;
    private int columna;
    private Object valorAntiguo;
    private Object valorNuevo;

    public TablaListener(JTable table, Action action) {
        this.table = table;
        this.action = action;
        this.table.addPropertyChangeListener(this);
    }

    private TablaListener(JTable tabla, int fila, int columna, Object valorAntiguo, Object valorNuevo)
    {
        this.table = tabla;
        this.fila = fila;
        this.columna = columna;
        this.valorAntiguo = valorAntiguo;
        this.valorNuevo = valorNuevo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Object getValorAntiguo() {
        return valorAntiguo;
    }

    public Object getValorNuevo() {
        return valorNuevo;
    }

    public JTable getTable() {
        return table;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       if("tableCellEditor".equals(evt.getPropertyName())) {
           if(table.isEditing())
               empezarEdicion();
           else
               finalizarEdicion();
       }
    }

    private void empezarEdicion() {
        SwingUtilities.invokeLater(this);
        TablaListener tl = new TablaListener(
                getTable(), getFila(), getColumna(), getValorAntiguo(), getValorNuevo()
        );
        action.actionPerformed(new ActionEvent(
                tl,
                ActionEvent.ACTION_PERFORMED,
                "startEditing"
        ));
    }

    private void finalizarEdicion() {
        valorNuevo = table.getModel().getValueAt(fila, columna);
        if (! valorNuevo.equals(valorAntiguo)){
            TablaListener tl = new TablaListener(
                    getTable(), getFila(), getColumna(), getValorAntiguo(), getValorNuevo()
            );

            ActionEvent event = new ActionEvent(
                    tl,
                    ActionEvent.ACTION_PERFORMED,
                    "");
            action.actionPerformed(event);
        }
    }

    public void externalEditting(String val) {
        valorAntiguo = table.getModel().getValueAt(fila, columna);
        valorNuevo = val;
        if (! valorNuevo.equals(valorAntiguo)){
            TablaListener tl = new TablaListener(
                    getTable(), getFila(), getColumna(), getValorAntiguo(), getValorNuevo()
            );

            ActionEvent event = new ActionEvent(
                    tl,
                    ActionEvent.ACTION_PERFORMED,
                    "");
            action.actionPerformed(event);
        }
    }

    @Override
    public void run() {
        fila = table.convertRowIndexToModel(table.getEditingRow());
        columna = table.convertColumnIndexToModel(table.getEditingColumn());
        valorAntiguo = table.getModel().getValueAt(fila, columna);
        valorNuevo = null;
    }
}
