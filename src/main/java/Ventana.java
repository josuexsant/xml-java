import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;


public class Ventana extends JFrame {
    private JPanel ventanaPanel;
    private JComboBox<String> comboBox;
    private JTextField textFieldEditable;
    private JButton buttonAgregar;
    private JComboBox comboBoxSeccion;
    private JComboBox comboBoxCapitulos;
    private JButton verCapitulosButton;
    private Libro libro;

    public Ventana() {
        libro = new Libro("Mi libro");
        llenarSecciones();
        buttonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboBox.getSelectedItem();

                switch (seleccion){
                    case "Seccion":
                        libro.agregarSeccion(textFieldEditable.getText());
                        break;
                    case "Capitulo":
                        libro.agregarCapitulo((String) comboBoxSeccion.getSelectedItem(), textFieldEditable.getText());
                        break;
                    case "Parrafo":
                        libro.agregarParrafo((String) comboBoxCapitulos.getSelectedItem(), textFieldEditable.getText());
                        break;
                }
                llenarSecciones();

                System.out.println(libro.visualizarLibro());
            }
        });

        verCapitulosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarCapitulos();
            }
        });
    }

    public void llenarSecciones() {
        LinkedList<Seccion> secciones = (LinkedList<Seccion>) libro.getSecciones();
        comboBoxSeccion.removeAllItems();
        for (Seccion seccion : secciones) {
            comboBoxSeccion.addItem(seccion.getTitulo());
        }
    }

    public void llenarCapitulos() {
        comboBoxCapitulos.removeAllItems();
        LinkedList<Seccion> secciones = (LinkedList<Seccion>) libro.getSecciones();
        Seccion seccionSeleccionada = null;
        for (Seccion seccion : secciones) {
            if (seccion.getTitulo().equals(comboBoxSeccion.getSelectedItem())){
                seccionSeleccionada = seccion;
            }
        }

        LinkedList<Capitulo> capitulos = (LinkedList<Capitulo>) seccionSeleccionada.getCapitulos();
        for (Capitulo capitulo : capitulos) {
            comboBoxCapitulos.addItem(capitulo.getTitulo());
        }
    }


    public void mostrarInterfaz() {
        JFrame frame = new JFrame("Crea tu XML");
        frame.setContentPane(new Ventana().ventanaPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
        dispose();
    }
}