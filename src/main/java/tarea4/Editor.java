package tarea4;

import practica8.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static tarea4.ConverterJDOM.getCapitulos;
import static tarea4.ConverterJDOM.getSecciones;


public class Editor extends JFrame {
    private JPanel ventanaPanel;
    private JComboBox<String> comboBox;
    private JTextField textFieldEditable;
    private JButton buttonAgregar;
    private JComboBox comboBoxSeccion;
    private JComboBox comboBoxCapitulos;
    private JButton verCapitulosButton;
    private JButton guardarButton;
    private Libro libro;

    public Editor() {
        libro = new Libro("Mi libro");
        llenarSecciones();
        buttonAgregar.addActionListener(e -> {
            String seleccion = (String) comboBox.getSelectedItem();

            switch (seleccion) {
                case "Seccion":
                    ConverterJDOM.addSection(textFieldEditable.getText());
                    System.out.println("Visualizacion: ------------------------------------------------------\n");
                    ConverterJDOM.print();
                    break;
                case "Capitulo":
                    if (ConverterJDOM.getSecciones().isEmpty()) {
                        JOptionPane.showConfirmDialog(null, "No hay secciones", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    } else {
                        ConverterJDOM.addChapter((String) comboBoxSeccion.getSelectedItem(), textFieldEditable.getText());
                        System.out.println("Visualizacion: ------------------------------------------------------\n");
                        ConverterJDOM.print();

                    }
                    break;
                case "Parrafo":
                    if (ConverterJDOM.getSecciones().isEmpty() || comboBoxCapitulos.getSelectedItem() == null) {
                        JOptionPane.showConfirmDialog(null, "No hay secciones o capitulos", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    } else {
                        ConverterJDOM.addParraph((String) comboBoxCapitulos.getSelectedItem(), textFieldEditable.getText());
                        System.out.println("Visualizacion: ------------------------------------------------------\n");
                        ConverterJDOM.print();

                    }
                    break;
            }
            llenarSecciones();
        });

        guardarButton.addActionListener(e -> {
            ConverterJDOM.save();
            JOptionPane.showMessageDialog(null,"Archivo XML guardado");
            dispose();
        });

        verCapitulosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarCapitulos();
            }
        });
    }

    public void llenarSecciones() {
        List<String> secciones = getSecciones();
        comboBoxSeccion.removeAllItems();
        for (String seccion : secciones) {
            comboBoxSeccion.addItem(seccion);
        }
    }

    public void llenarCapitulos() {
        comboBoxCapitulos.removeAllItems();
        List<String> capitulos = getCapitulos();
        for (String capitulo : capitulos) {
            comboBoxCapitulos.addItem(capitulo);
        }
    }

    public void mostrarInterfaz() {
        JFrame frame = new JFrame("Crea tu XML");
        frame.setContentPane(new Editor().ventanaPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
        dispose();
    }
}