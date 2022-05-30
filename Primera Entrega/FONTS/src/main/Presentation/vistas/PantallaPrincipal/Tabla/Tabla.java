package main.Presentation.vistas.PantallaPrincipal.Tabla;

import main.Domain.DomainControllers.ControladorDominio;
import main.Domain.DomainModel.Celda;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
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

/**
 * Clase Tabla, contiene la información de una hoja (tabla, modelo de datos...)
 * @author Joaquim Torra Garcia
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

    /**
     * Constructor por defecto de la clase Tabla
     * @param fxField JTextField en el que se ve el contenido de la celda seleccionada
     * @param _cd ControladorDominio
     * @param _idH Identificador de la hoja
     */
    public Tabla(JTextField fxField, ControladorDominio _cd, int _idH) {
        super(new GridLayout(1,0));
        this.fxField = fxField;
        this.cd = _cd;
        this.idH = _idH;
        init(50, 50);
    }

    /**
     * Constructor de la clase Tabla, que crea una hoja con una cantidad de filas y columnas dadas
     * @param rows Cantidad de filas de la hoja
     * @param cols Cantidad de columnas de la hoja
     * @param fxField JTextField en el que se ve el contenido de la celda seleccionada
     * @param _cd ControladorDominio
     * @param _idH Identificador de la hoja
     */
    public Tabla(int rows, int cols, JTextField fxField, ControladorDominio _cd, int _idH) {
        super(new GridLayout(1,0));
        this.fxField = fxField;
        this.cd = _cd;
        this.idH = _idH;
        this.rows = rows;
        this.cols = cols;
        init(rows, cols);
    }

    /**
     * @return Devuelve el numero de filas
     */
    public int getRows () {
        return rows;
    }

    /**
     * @return Devuelve el numero de columnas
     */
    public int getCols () {
        return cols;
    }

    /**
     * Inicializa el modelo de datos, la JTable con el CeldaRenderer correspondiente y sus listeners
     * @param rows Cantidad de filas de la tabla
     * @param cols Cantidad de columnas de la tabla
     */
    public void init(int rows, int cols) {
        try {
            cd.getControladorHoja().asignaHoja(idH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        table = new JTable(new TablaModel(rows, cols, cd)) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 0) {
                    return new CeldaRenderer(new Color(240, 240, 240), Color.BLACK, true, false);
                }
                TableCellRenderer myRenderer = getCustomRenderer(row, column);
                if (myRenderer != null) {
                    return myRenderer;
                }
                return super.getCellRenderer(row, column);
            }

            public TableCellRenderer getCustomRenderer(int row, int column) {
                cd.getControladorHoja().asignaCelda((row+1)+"", (column)+"");
                Celda c = cd.getControladorHoja().getCeldaRef();
                if (c == null) return null;
                return new CeldaRenderer(c.getBackground(), c.getForeground(), c.isBold(), c.isItalic());
            }
        };

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

    /**
     * Metodo que se encarga de enviar el contenido introducido por el usuario a la celda correspondiente
     * @param e Evento que se produce al introducir el contenido
     * @param text Contenido introducido por el usuario
     * @param row Fila de la celda
     * @param col Columna de la celda
     */
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

    /**
     * Metdodo que se encarga de actualizar los valores de las celdas seleccionadas y el contenido de la celda actual mostrada en el campo de texto
     */
    private void selection() {
        selectedRowStart = table.getSelectedRow();
        selectedRowEnd = table.getSelectionModel().getMaxSelectionIndex();
        selectedColumnStart = table.getSelectedColumn();
        selectedColumnEnd = table.getColumnModel().getSelectionModel().getMaxSelectionIndex();
        cd.getControladorHoja().asignaCelda((selectedRowEnd+1)+"", selectedColumnEnd+"");
        fxFieldDisplay();
    }

    /**
     * Metodo que se encarga de actualizar el contenido de la celda actual mostrada en el campo de texto
     * @param c Celda actual
     */
    private void reloadValue(Celda c) {
        String val = cd.getControladorHoja().getCeldaRef().getValor();
        table.getModel().setValueAt(val, c.getPosicion().getFila()-1, c.getPosicion().getColumna());

        for (Celda ref : c.getReferenciantes()) {
            reloadValue(ref);
        }
    }

    /**
     * Metodo que se encarga de actualizar el contenido del campo de texto con el contenido de la celda actual
     */
    private void fxFieldDisplay() {
        if (selectedRowEnd < 0 || selectedColumnEnd <= 0) return;
        currentContent = cd.getControladorHoja().getCeldaRef().getContenido();
        fxField.setText(currentContent);
    }

    /** Metodo necesario para que la clase derive de JPanel
     * @param e Evento que se produce al hacer click en el boton de añadir fila
     */
    public void tableChanged(TableModelEvent e) {
    }

    /**
     * Metodo que se encarga de cambiar el valor del modelo de datos de la tabla
     * @param cont Contenido de la tabla
     */
    public void changeContent(String cont) {
        table.getModel().setValueAt(cont, selectedRowEnd, selectedColumnEnd);
    }

    /**
     * @return TablaListener
     */
    public TablaListener getTl() {
        return tl;
    }

    /**
     * @return Fila seleccionada inicial
     */
    public int getSelectedRowStart() {
        return selectedRowStart;
    }

    /**
     * @return Columna seleccionada inicial
     */
    public int getSelectedColumnStart() {
        return selectedColumnStart;
    }

    /**
     * @return Fila seleccionada final
     */
    public int getSelectedRowEnd() {
        return selectedRowEnd;
    }

    /**
     * @return Columna seleccionada final
     */
    public int getSelectedColumnEnd() {
        return selectedColumnEnd;
    }

    /**
     * @return Indice de la hoja que representa la tabla
     */
    public int getIdH() {
        return idH;
    }

    /**
     * Metodo que se encarga de añadir una fila a la tabla en la posicion indicada
     * @param row int posicion de la fila a añadir
     */
    public void insertRow(int row) {
        rows++;
        Vector<Object> rowData = new Vector<>();
        for (int i = 0; i < cols; i++) {
            rowData.add("");
        }
        model.insertRow(row, rowData);
    }

    /**
     * Metodo que se encarga de insertar una columna en la posicion indicada
     * @param col int posicion de la columna a añadir
     */
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

    /**
     * Metodo dedicado a hacer que la primera columna sea siempre la de el numero de fila y sea siempre igual y solo de lectura
     */
    private void resetColumnVista () {
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(25);
        column.setResizable(false);
        for (int i = 1; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(100);
            column.setHeaderValue(model.getColumnName(i));
        }
    }

    /**
     * Se encarga de seleccionar el bloque de celdas y copiarlo
     */
    public void copiar () {
        if (selectedRowEnd < 0 || selectedColumnEnd <= 0) return;
        cd.getControladorBloque().setBloqueSeleccionado(selectedRowStart+1, selectedColumnStart, selectedRowEnd+1, selectedColumnEnd);
        copiedRowStart = selectedRowStart;
        copiedColumnStart = selectedColumnStart;
        cd.getControladorBloque().copiar();
    }

    /**
     * Se encarga de pegar el bloque de celdas copiado en la posicion seleccionada
     */
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

    /**
     * Se encarga de seleccionar el bloque de celdas y cortarlo
     */
    public void cortar () {
        if (selectedRowEnd < 0 || selectedColumnEnd <= 0) return;
        cd.getControladorBloque().setBloqueSeleccionado(selectedRowStart+1, selectedColumnStart, selectedRowEnd+1, selectedColumnEnd);
        copiedRowStart = selectedRowStart;
        copiedColumnStart = selectedColumnStart;
        cd.getControladorBloque().cortar();
    }

    /**
     * Se encarga de seleccionar el bloque de celdas y ordenarlo de forma ascendente o descendente segun el parametro
     * @param descendente boolean indica si se ordena de forma descendente
     */
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

    /**
     * Elimina la fila indicada
     * @param r fila a eliminar
     */
    public void removeRow(int r) {
        rows--;
        model.removeRow(r);
    }

    /**
     * Elimina la columna indicada
     * @param c columna a eliminar
     */
    public void removeColumn(int c) {
        cols--;
        table.removeColumn(table.getColumnModel().getColumn(c));
        resetColumnVista();
    }

    /**
     * Estiliza la celda seleccionada con el estilo de letra en negrita
     */
    public void bold () {
        for (int i = selectedRowStart+1; i <= selectedRowEnd+1; i++) {
            for (int j = selectedColumnStart; j <= selectedColumnEnd; j++) {
                if (j == 0) continue;
                cd.getControladorHoja().asignaCelda((i)+"", (j)+"");
                cd.getControladorHoja().getCeldaRef().setBold(!cd.getControladorHoja().getCeldaRef().isBold());
            }
        }
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
    }

    /**
     * Estiliza la celda seleccionada con el estilo de letra en cursiva
     */
    public void italic () {
        for (int i = selectedRowStart+1; i <= selectedRowEnd+1; i++) {
            for (int j = selectedColumnStart; j <= selectedColumnEnd; j++) {
                if (j == 0) continue;
                cd.getControladorHoja().asignaCelda((i)+"", (j)+"");
                cd.getControladorHoja().getCeldaRef().setItalic(!cd.getControladorHoja().getCeldaRef().isItalic());
            }
        }
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
    }

    /**
     * Estiliza la celda seleccionada con el color de fondo seleccionado por el usuario
     * @param _color color de fondo
     */
    public void changeBG (Color _color) {
        for (int i = selectedRowStart+1; i <= selectedRowEnd+1; i++) {
            for (int j = selectedColumnStart; j <= selectedColumnEnd; j++) {
                if (j == 0) continue;
                cd.getControladorHoja().asignaCelda((i)+"", (j)+"");
                cd.getControladorHoja().getCeldaRef().setBackground(_color);
            }
        }
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
    }

    /**
     * Estiliza la celda seleccionada con el color de letra seleccionado por el usuario
     * @param _color color de letra
     */
    public void changeFG (Color _color) {
        for (int i = selectedRowStart+1; i <= selectedRowEnd+1; i++) {
            for (int j = selectedColumnStart; j <= selectedColumnEnd; j++) {
                if (j == 0) continue;
                cd.getControladorHoja().asignaCelda((i)+"", (j)+"");
                cd.getControladorHoja().getCeldaRef().setForeground(_color);
            }
        }
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
    }
}
