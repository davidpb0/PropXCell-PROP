package main.Presentation.vistas.PantallaPrincipal;

import javax.swing.*;
import java.awt.*;

/*
 * Tabla - Vista
 *
 * v0.0.1
 *
 * Joaquim Torra Garcia
 */

public class Tabla extends JPanel {
    public Tabla() {
        super(new GridLayout(1,0));

        String[] columnNames = {"┘", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};

        Object[][] data = {
                {
                    "1", 1, 2, 3, 4, 5, 6, 7 , 8, 9, 0, 1, 2, 3, 4
                },
                {
                    "2", 1, 2, 3, 4, 5, 6, 7 , 8, 9, 0, 1, 2, 3, 4
                },
                {
                    "3", 1, 2, 3, 4, 5, 6, 7 , 8, 9, 0, 1, 2, 3, 4
                },
        };

        // Aqui le paso datos Hard-Coded
        // Deberia implementarse una clase Modelo extends AbstractTableModel
        // final JTable table = new JTable(TablaModel);
        final JTable table = new JTable(data, columnNames);

        // table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        // table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
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
        Tabla newContentPane = new Tabla();
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
