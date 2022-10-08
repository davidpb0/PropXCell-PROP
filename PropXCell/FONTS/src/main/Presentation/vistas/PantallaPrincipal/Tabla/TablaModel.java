package main.Presentation.vistas.PantallaPrincipal.Tabla;


import main.Domain.DomainControllers.ControladorDominio;
import main.Persistence.PersistenceControllers.ControladorPersistencia;
import main.Presentation.ControladorPresentacion;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*
 * Tabla - Model
 *
 * v0.0.4
 *
 * Joaquim Torra Garcia
 */

/**
 * Clase TablaModel, el modelo de datos de la tabla
 * @author Joaquim Torra Garcia
 */
public class TablaModel extends DefaultTableModel {

    private int rows;
    private int cols;
    private final ControladorDominio cd;

    public TablaModel(int rows, int cols, ControladorDominio cd) {
        this.rows = rows;
        this.cols = cols;
        this.cd = cd;
        init();
    }

    private void init () {
        dataVector = new Vector<>();
        for (int i = 1; i <= rows; i++) {
            Vector<String> v = new Vector<>();
            for (int j = 1; j <= cols; j++) {
                if (cd.getControladorHoja().getHojaRef() != null) {
                    cd.getControladorHoja().asignaCelda(i+"", j+"");
                    v.add(cd.getControladorHoja().getCeldaRef().getValor());
                    continue;
                }
                v.add("");
            }
            dataVector.add(v);
        }
    }

    @Override
    public int getRowCount() {
        if (dataVector == null) return 0;
        return dataVector.size();
    }

    @Override
    public int getColumnCount() {
        return dataVector.get(0).size() + 1;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return (rowIndex + 1) + "";
        }
        else
            return (String) dataVector.get(rowIndex).get(columnIndex-1);
    }

    @Override
    public String getColumnName(int col) {
        if (col == 0)
            return "█";

        String result = "";
        col--;
        for (; col >= 0; col = col / 26 - 1) {
            result = (char)((char)(col%26)+'A') + result;
        }
        return result;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        dataVector.get(rowIndex).set(columnIndex-1, aValue.toString());
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public void addRow(Object[] rowData) {
        this.rows++;
        super.addRow(rowData);
    }

    @Override
    public void insertRow(int row, Vector<?> rowData) {
        this.rows++;
        super.insertRow(row, rowData);
    }

    @Override
    public void addColumn(Object columnName) {
        this.cols++;
        super.addColumn(columnName);
    }
}