package tarea4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Explorador extends JFrame {
    private JComboBox comboBox1;
    private JPanel panel1;
    private JLabel label;
    private JButton visualizarButton;

    public Explorador() {
        setComboBox1();

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Biblioteca biblioteca = new Biblioteca();
                biblioteca.imprimirLibro((String) comboBox1.getSelectedItem());
            }
        });
    }

    public void setComboBox1() {
        Biblioteca biblioteca = new Biblioteca();
        LinkedList<String> libros = biblioteca.getLibros();
        for (String libro : libros) {
            comboBox1.addItem(libro);
        }
    }

    public void mostrarInterfaz() {
        JFrame frame = new JFrame("Crea tu XML");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
        dispose();
    }
}
