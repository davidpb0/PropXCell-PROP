package main.Presentation.vistas.PantallaPrincipal.Tabla;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Tabla - Model
 *
 * v0.0.2
 *
 * Joaquim Torra Garcia
 */

public class TablaModel extends AbstractTableModel {

    List<List<String>> data = new ArrayList<>();

    public TablaModel(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            List<String> l = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                l.add("");
            }
            data.add(l);
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return data.get(0).size() + 1;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return (rowIndex + 1) + "";
        }
        else
            return data.get(rowIndex).get(columnIndex-1).toString();
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
        data.get(rowIndex).set(columnIndex-1, aValue.toString());
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}