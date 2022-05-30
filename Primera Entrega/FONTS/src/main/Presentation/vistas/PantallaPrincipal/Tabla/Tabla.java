package main.Presentation.vistas.PantallaPrincipal.Tabla;

import main.Domain.DomainControllers.ControladorDominio;
import main.Domain.DomainControllers.ControladorHoja;
import main.Domain.DomainModel.Celda;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;

/*
 * Tabla - Vista
 *
 * v0.0.5
 *
 * Joaquim Torra Garcia
 */

public class Tabla extends JPanel implements TableModelListener {
    private final int idH;
    JTable table;
    JTextField fxField;
    ControladorDominio cd;
    DefaultTableModel model;

    private TablaListener tl;

    private int selectedRowStart;
    private int selectedRowEnd;
    private int selectedColumnStart;
    private int selectedColumnEnd;

    private int copiedRowStart;
    private int copiedColumnStart;

    private String currentContent;

    private int rows;
    private int cols;

    public Tabla(JTextField fxField, ControladorDominio _cd, int _idH) {
        super(new GridLayout(1,0));
        this.fxField = fxField;
        this.cd = _cd;
        this.idH = _idH;
        init(50, 50);
    }

    public Tabla(int rows, int cols, JTextField fxField, ControladorDominio _cd, int _idH) {
        super(new GridLayout(1,0));
        this.fxField = fxField;
        this.cd = _cd;
        this.idH = _idH;
        this.rows = rows;
        this.cols = cols;
        init(rows, cols);
    }

    public void init(int rows, int cols) {
        try {
            cd.getControladorHoja().asignaHoja(idH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        table = new JTable(new TablaModel(rows, cols, cd));

        model = (DefaultTableModel) table.getModel();
        table.getTableHeader().setReorderingAllowed(false);

        table.setDefaultEditor(Object.class, CeldaEditor.make(currentContent));
        DefaultCellEditor editor = (DefaultCellEditor) table.getDefaultEditor(String.class);
        // Disable Ctrl+C, Ctrl+V, Ctrl+X
        ((JTextField) editor.getComponent()).getInputMap().put(KeyStroke.getKeyStroke('C', ActionEvent.CTRL_MASK), "none");
        ((JTextField) editor.getComponent()).getInputMap().put(KeyStroke.getKeyStroke('V', ActionEvent.CTRL_MASK), "none");
        ((JTextField) editor.getComponent()).getInputMap().put(KeyStroke.getKeyStroke('X', ActionEvent.CTRL_MASK), "none");
        ((JTextField) editor.getComponent()).getInputMap().put(KeyStroke.getKeyStroke('B', ActionEvent.CTRL_MASK), "none");
        table.setRowHeight(20);

        resetColumnVista();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getModel().addTableModelListener(this);

        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setColumnSelectionAllowed(true);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selection();
            }
        });
        table.getColumnModel().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selection();
            }
        });

        tl = new TablaListener(table, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == "startEditing") return;
                Object data = model.getValueAt(selectedRowEnd, selectedColumnEnd);
                enviarContenido(e, data.toString(), selectedRowEnd+1, selectedColumnEnd);
            }
        });

        InputMap inputMap = table.getInputMap(WHEN_FOCUSED);
        ActionMap actionMap = table.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
        actionMap.put("delete", new AbstractAction() {
            public void actionPerformed(ActionEvent evt) {
                for (int i = selectedRowStart+1; i <= selectedRowEnd+1; i++) {
                   for (int j = selectedColumnStart; j <= selectedColumnEnd; j++) {
                       enviarContenido(evt, "", i, j);
                   }
                }
            }
        });

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(getSize());
        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    public void enviarContenido (ActionEvent e, String text, int row, int col) {
        if (row < 0 || col <= 0) {
            return;
        }

        if (e.getActionCommand().equals("startEditting")) {
            //System.out.println(currentContent);
            return;
        }

        currentContent = text;
        fxField.setText(currentContent);
        try {
            cd.getControladorHoja().asignaCelda(row+"", col+"");
            cd.getControladorHoja().escribirContenido(text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        reloadValue(cd.getControladorHoja().getCeldaRef());
    }

    private void selection() {
        selectedRowStart = table.getSelectedRow();
        selectedRowEnd = table.getSelectionModel().getMaxSelectionIndex();
        selectedColumnStart = table.getSelectedColumn();
        selectedColumnEnd = table.getColumnModel().getSelectionModel().getMaxSelectionIndex();
        cd.getControladorHoja().asignaCelda((selectedRowEnd+1)+"", selectedColumnEnd+"");
        fxFieldDisplay();
    }

    private void reloadValue(Celda c) {
        String val = cd.getControladorHoja().getCeldaRef().getValor();
        table.getModel().setValueAt(val, c.getPosicion().getFila()-1, c.getPosicion().getColumna());

        for (Celda ref : c.getReferenciantes()) {
            reloadValue(ref);
        }
    }

    private void fxFieldDisplay() {
        if (selectedRowEnd < 0 || selectedColumnEnd <= 0) return;
        currentContent = cd.getControladorHoja().getCeldaRef().getContenido();
        fxField.setText(currentContent);
    }

    public void tableChanged(TableModelEvent e) {
    }

    public void changeContent(String cont) {
        table.getModel().setValueAt(cont, selectedRowEnd, selectedColumnEnd);
    }

    public TablaListener getTl() {
        return tl;
    }

    public int getSelectedRowStart() {
        return selectedRowStart;
    }

    public int getSelectedColumnStart() {
        return selectedColumnStart;
    }

    public int getSelectedRowEnd() {
        return selectedRowEnd;
    }

    public int getSelectedColumnEnd() {
        return selectedColumnEnd;
    }

    public int getIdH() {
        return idH;
    }

    public void insertRow(int row) {
        rows++;
        Vector<Object> rowData = new Vector<>();
        for (int i = 0; i < cols; i++) {
            rowData.add("");
        }
        model.insertRow(row, rowData);
    }


    public void insertColumn(int col) {
        model.addColumn(model.getColumnName(cols+1));
        cols++;
        model.fireTableStructureChanged();
        for (int i = col; i <= cols; i++) {
            for (int j = 1; j <= rows; j++) {
                cd.getControladorHoja().asignaCelda((j)+"", (i)+"");
                reloadValue(cd.getControladorHoja().getCeldaRef());
            }
        }
        resetColumnVista();
    }

    private void resetColumnVista () {
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(25);
        column.setResizable(false);
        column.setCellRenderer(new CeldaRenderer(new Color(240, 240, 240), Color.BLACK));
        for (int i = 1; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(100);
            column.setHeaderValue(model.getColumnName(i));
        }
    }

    public void copiar () {
        if (selectedRowEnd < 0 || selectedColumnEnd <= 0) return;
        cd.getControladorBloque().setBloqueSeleccionado(selectedRowStart+1, selectedColumnStart, selectedRowEnd+1, selectedColumnEnd);
        copiedRowStart = selectedRowStart;
        copiedColumnStart = selectedColumnStart;
        cd.getControladorBloque().copiar();
    }

    public void pegar () {
        if (selectedRowEnd < 0 || selectedColumnEnd <= 0 ||cd.getControladorBloque().getBloqueCopiado() == null) return;
        int r = cd.getControladorBloque().getBloqueCopiado().getTamanoFilas();
        int c = cd.getControladorBloque().getBloqueCopiado().getTamanoColumnas();
        boolean cortar = cd.getControladorBloque().getBloqueCopiado().getCortar();
        cd.getControladorBloque().pegar(selectedRowStart+1, selectedColumnStart);
        // Actualizar tabla
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cd.getControladorHoja().asignaCelda((selectedRowStart+1+i)+"", (selectedColumnStart+j)+"");
                reloadValue(cd.getControladorHoja().getCeldaRef());

                if (cortar) {
                    cd.getControladorHoja().asignaCelda((copiedRowStart+i+1)+"", (copiedColumnStart+j)+"");
                    try {
                        cd.getControladorHoja().escribirContenido("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    reloadValue(cd.getControladorHoja().getCeldaRef());
                }
            }
        }
    }

    public void cortar () {
        if (selectedRowEnd < 0 || selectedColumnEnd <= 0) return;
        cd.getControladorBloque().setBloqueSeleccionado(selectedRowStart+1, selectedColumnStart, selectedRowEnd+1, selectedColumnEnd);
        copiedRowStart = selectedRowStart;
        copiedColumnStart = selectedColumnStart;
        cd.getControladorBloque().cortar();
    }

    public void ordenar (boolean descendente) {
       if (selectedRowEnd < 0 || selectedColumnEnd <= 0) return;
         cd.getControladorBloque().setBloqueSeleccionado(selectedRowStart+1, selectedColumnStart, selectedRowEnd+1, selectedColumnEnd);
         cd.getControladorHoja().ordenar(descendente);
         // Actualizar tabla
         for (int i = 0; i < selectedRowEnd-selectedRowStart+1; i++) {
            for (int j = 0; j < selectedColumnEnd-selectedColumnStart+1; j++) {
                cd.getControladorHoja().asignaCelda((selectedRowStart+1+i)+"", (selectedColumnStart+j)+"");
                reloadValue(cd.getControladorHoja().getCeldaRef());
            }
         }
    }

    public void removeRow(int r) {
        rows--;
        model.removeRow(r);
    }

    public void removeColumn(int c) {
        cols--;
        table.removeColumn(table.getColumnModel().getColumn(c));
        resetColumnVista();
    }
}
