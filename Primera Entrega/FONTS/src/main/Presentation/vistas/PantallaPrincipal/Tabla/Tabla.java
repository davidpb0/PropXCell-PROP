package main.Presentation.vistas.PantallaPrincipal.Tabla;

import main.Domain.DomainModel.Celda;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

/*
 * Tabla - Vista
 *
 * v0.0.2
 *
 * Joaquim Torra Garcia
 */

public class Tabla extends JPanel implements TableModelListener {
    public Tabla() {
        super(new GridLayout(1,0));
        init(50, 50);
    }

    public Tabla(int rows, int cols) {
        super(new GridLayout(1,0));
        System.out.println("here");
        init(rows, cols);
    }

    public void init(int rows, int cols) {
        final JTable table = new JTable(new TablaModel(rows, cols));

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

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        System.out.println();
        scrollPane.setSize(getSize());

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int col = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(col);
        Object data = model.getValueAt(row, col);

        // Aqui pasaria el nuevo valor en "data" a dominio
        System.out.println(col + row + " : " + data);
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
        Tabla newContentPane = new Tabla(25, 25);
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
