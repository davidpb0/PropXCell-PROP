package main.Presentation.vistas.PantallaPrincipal.Tabla;


import main.Domain.DomainControllers.ControladorDominio;
import main.Persistence.PersistenceControllers.ControladorPersistencia;
import main.Presentation.ControladorPresentacion;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/*
 * Tabla - Model
 *
 * v0.0.4
 *
 * Joaquim Torra Garcia
 */

public class TablaModel extends AbstractTableModel {

    List<List<String>> valores = new ArrayList<>();
    private final int rows;
    private final int cols;

    public TablaModel(int rows, int cols, ControladorDominio cd) {
        this.rows = rows;
        this.cols = cols;
        for (int i = 1; i <= rows; i++) {
            List<String> v = new ArrayList<>();
            for (int j = 1; j <= cols; j++) {
                if (cd.getControladorHoja().getHojaRef() != null) {
                    cd.getControladorHoja().asignaCelda(i+"", j+"");
                    v.add(cd.getControladorHoja().getCeldaRef().getValor());
                    continue;
                }
                v.add("");
            }
            valores.add(v);
        }
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return valores.get(0).size() + 1;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return (rowIndex + 1) + "";
        }
        else
            return valores.get(rowIndex).get(columnIndex-1);
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
        valores.get(rowIndex).set(columnIndex-1, aValue.toString());
        fireTableCellUpdated(rowIndex, columnIndex);
    }

}