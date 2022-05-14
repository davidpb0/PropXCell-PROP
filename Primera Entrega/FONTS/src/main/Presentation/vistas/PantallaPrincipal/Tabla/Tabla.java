package main.Presentation.vistas.PantallaPrincipal.Tabla;

import main.Domain.DomainControllers.ControladorDominio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

/*
 * Tabla - Vista
 *
 * v0.0.3
 *
 * Joaquim Torra Garcia
 */

public class Tabla extends JPanel implements TableModelListener {
    int idH;
    JTable table;
    JTextField fxField;
    ControladorDominio cd;

    int selectedRow;
    int selectedColumn;

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
        table.setColumnSelectionAllowed(true);

        TableColumn column = null;
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(25);
        column.setResizable(false);
        column.setCellRenderer(new CeldaRenderer(new Color(240, 240, 240), Color.BLACK));
        for (int i = 1; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(100); //third column is bigger
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getModel().addTableModelListener(this);

        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedRow = table.getSelectedRow();
                selectedColumn = table.getSelectedColumn();
                // Aqui el setText tendira que setear el contenido de la celda, y no el valor
                fxField.setText(table.getValueAt(selectedRow, selectedColumn).toString());
                //System.out.println(table.getModel().getColumnName(selectedColumn) + selectedRow);
            }
        });

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setSize(getSize());

        //Add the scroll pane to this panel.
        add(scrollPane);


    }

    public void tableChanged(TableModelEvent e) {
        int row = e.getLastRow();
        int col = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(col);
        Object data = model.getValueAt(row, col);

        // Aqui pasaria el nuevo valor en "data" a dominio

        /*cd.getControladorHoja().asignaHoja(idH+1);
        try {
            cd.getControladorHoja().escribirContenido(data.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        // ^ Se entiende el intento :/ ^

        System.out.println(columnName + row + " : " + data);
    }

    public void changeContent(String cont) {
        table.getModel().setValueAt(cont, table.getSelectedRow(), table.getSelectedColumn());
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
        Tabla newContentPane = new Tabla(25, 25, new JTextField(), new ControladorDominio(), 0);
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
}
