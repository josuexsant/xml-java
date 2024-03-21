package practica8;

import java.util.LinkedList;
import java.util.List;

public class Seccion {
    private String titulo;
    private LinkedList<Capitulo> capitulos;

    public Seccion(String titulo) {
        this.titulo = titulo;
        this.capitulos = new LinkedList<>();
    }

    public void agregarCapitulo(String tituloCapitulo) {
        Capitulo capitulo = new Capitulo(tituloCapitulo);
        capitulos.add(capitulo);
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }
}
