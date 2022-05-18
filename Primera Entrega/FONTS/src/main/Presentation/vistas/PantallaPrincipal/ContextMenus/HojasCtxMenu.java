package main.Presentation.vistas.PantallaPrincipal.ContextMenus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HojasCtxMenu extends JPopupMenu implements ActionListener {
    JMenuItem nuevaHoja;
    JMenuItem borrarHoja;

    public HojasCtxMenu() {
        nuevaHoja = new JMenuItem("Nueva Hoja");
        borrarHoja = new JMenuItem("Eliminar Hoja");

        nuevaHoja.addActionListener(this);

        add(nuevaHoja);
        add(borrarHoja);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (nuevaHoja.equals(source)) {
            System.out.println("Nueva Hoja " + e.getSource());
        } else if (borrarHoja.equals(source)) {
            System.out.println("Eliminar Hoja");
        }
    }
}
