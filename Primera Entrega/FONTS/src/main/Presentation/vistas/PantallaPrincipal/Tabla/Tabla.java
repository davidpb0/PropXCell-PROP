package main.Presentation.vistas.PantallaPrincipal.Tabla;

import main.Domain.DomainControllers.ControladorDominio;
import main.Domain.DomainModel.Celda;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/*
 * Tabla - Vista
 *
 * v0.0.4
 *
 * Joaquim Torra Garcia
 */

public class Tabla extends JPanel implements TableModelListener {
    int idH;
    JTable table;
    JTextField fxField;
    ControladorDominio cd;
    TableModel model;

    private TablaListener tl;

    int selectedRowStart;
    int selectedRowEnd;
    int selectedColumnStart;
    int selectedColumnEnd;

    private String currentContent;

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
        init(rows, cols);
    }

    public void init(int rows, int cols) {
        table = new JTable(new TablaModel(rows, cols));

        table.getTableHeader().setReorderingAllowed(false);

        table.setDefaultEditor(Object.class, CeldaEditor.make(currentContent));

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
                selectedRowStart = table.getSelectedRow();
                selectedRowEnd = table.getSelectionModel().getMaxSelectionIndex();
                selectedColumnStart = table.getSelectedColumn();
                selectedColumnEnd = table.getColumnModel().getSelectionModel().getMaxSelectionIndex();
                cd.getControladorHoja().asignaCelda((selectedRowEnd+1)+"", selectedColumnEnd+"");
                fxFieldDisplay();
            }
        });
        table.getColumnModel().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedRowStart = table.getSelectedRow();
                selectedRowEnd = table.getSelectionModel().getMaxSelectionIndex();
                selectedColumnStart = table.getSelectedColumn();
                selectedColumnEnd = table.getColumnModel().getSelectionModel().getMaxSelectionIndex();
                cd.getControladorHoja().asignaCelda((selectedRowEnd+1)+"", selectedColumnEnd+"");
                fxFieldDisplay();
            }
        });

        tl = new TablaListener(table, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = selectedRowEnd;
                int col = selectedColumnEnd;
                String columnName = model.getColumnName(col);

                if (e.getActionCommand().equals("startEditting")) {
                    //System.out.println(currentContent);
                    return;
                }

                Object data = model.getValueAt(row, col);
                currentContent = data.toString();
                fxField.setText(currentContent);
                try {
                    cd.getControladorHoja().escribirContenido(data.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                reloadValue(cd.getControladorHoja().getCeldaRef());
            }
        });

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(getSize());
        //Add the scroll pane to this panel.
        add(scrollPane);
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

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Tabla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Tabla newContentPane = new Tabla(25, 25, new JTextField(), ControladorDominio.getControladorDominio(), 0);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public TablaListener getTl() {
        return tl;
    }
}
