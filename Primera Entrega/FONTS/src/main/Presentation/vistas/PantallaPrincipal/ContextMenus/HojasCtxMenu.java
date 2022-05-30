package main.Presentation.vistas.PantallaPrincipal.ContextMenus;

import main.Domain.DomainControllers.ControladorDominio;
import main.Domain.DomainModel.Hoja;
import main.Presentation.vistas.PantallaPrincipal.Tabla.Tabla;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Clase que implementa el menú contextual de las hojas.
 * @author Joaquim Torra Garcia
 */

public class HojasCtxMenu extends JPopupMenu implements ActionListener {
    JMenuItem nuevaHoja;
    JMenuItem borrarHoja;
    JMenuItem renombrarHoja;

    JTabbedPane tabbedPane1;
    JPanel Activity;
    JTextField fxField;
    private final ArrayList<Tabla> tablas;

    ControladorDominio cd = ControladorDominio.getControladorDominio();

    public HojasCtxMenu(JTabbedPane tabbedPane, JPanel act, ArrayList<Tabla> tablas, JTextField fxField) {
        this.tabbedPane1 = tabbedPane;
        this.Activity = act;
        this.tablas = tablas;
        this.fxField = fxField;

        nuevaHoja = new JMenuItem("Nueva Hoja");
        borrarHoja = new JMenuItem("Eliminar Hoja");
        renombrarHoja = new JMenuItem("Renombrar Hoja");

        nuevaHoja.addActionListener(this);
        borrarHoja.addActionListener(this);
        renombrarHoja.addActionListener(this);

        add(nuevaHoja);
        add(borrarHoja);
        add(renombrarHoja);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand().toLowerCase();
        int idx;
        switch (cmd) {
            case "nueva hoja":
                cd.getControladorDocumento().anadirHoja();
                idx = cd.getControladorDocumento().getNumHojas();
                try {
                    cd.getControladorHoja().asignaHoja(idx);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Hoja h = cd.getControladorHoja().getHojaRef();
                Tabla t = new Tabla(h.getFilas(), h.getColumnas(), fxField, cd, idx);
                tablas.add(t);
                tabbedPane1.addTab("Hoja " + idx, null, t, "Hoja " + idx);
                tabbedPane1.setSelectedIndex(idx-1);
                break;
            case "renombrar hoja":
                String antiguoNombre = tabbedPane1.getTitleAt(tabbedPane1.getSelectedIndex());
                String nuevoNombre = (String) JOptionPane.showInputDialog(
                        Activity,
                        "Cambiar Nombre:",
                        "Renombrar Hoja",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        antiguoNombre
                );
                try {
                    cd.getControladorDocumento().asignaNombreHoja(nuevoNombre);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                tabbedPane1.setTitleAt(tabbedPane1.getSelectedIndex(), cd.getControladorHoja().getNombreHoja());
                break;
            case "eliminar hoja":
                idx = tabbedPane1.getSelectedIndex();
                int idH = ((Tabla) tabbedPane1.getComponentAt(tabbedPane1.getSelectedIndex())).getIdH();
                if (cd.getControladorDocumento().getNumHojas() <= 1) {
                    JOptionPane.showMessageDialog(
                            Activity,
                            "No puedes eliminar la última hoja",
                            "Eliminar Hoja",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                cd.getControladorDocumento().eliminarHoja(idH+1);
                tabbedPane1.remove(idx);
                idH = ((Tabla) tabbedPane1.getComponentAt(tabbedPane1.getSelectedIndex())).getIdH();
                try {
                    cd.getControladorHoja().asignaHoja(idH);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }
}
