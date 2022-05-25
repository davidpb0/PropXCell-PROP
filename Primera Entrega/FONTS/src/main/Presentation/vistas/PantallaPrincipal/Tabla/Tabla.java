package main.Presentation.vistas.PantallaPrincipal.Tabla;

import main.Domain.DomainControllers.ControladorDominio;
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
    TableModel model;

    private TablaListener tl;

    private int selectedRowStart;
    private int selectedRowEnd;
    private int selectedColumnStart;
    private int selectedColumnEnd;

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

        table.getTableHeader().setReorderingAllowed(false);

        table.setDefaultEditor(Object.class, CeldaEditor.make(currentContent));
        table.setRowHeight(20);

        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(25);
        column.setResizable(false);
        column.setCellRenderer(new CeldaRenderer(new Color(240, 240, 240), Color.BLACK));
        for (int i = 1; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(100); //third column is bigger
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model = table.getModel();
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

    public void añadirFila() {
    }
}
