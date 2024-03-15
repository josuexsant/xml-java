import java.util.ArrayList;
import java.util.List;

public class Capitulo {
    private String titulo;
    private List<String> parrafos;

    public Capitulo(String titulo) {
        this.titulo = titulo;
        this.parrafos = new ArrayList<>();
    }

    public void agregarParrafo(String texto) {
        parrafos.add(texto);
    }

    public String getTitulo() {
        return titulo;
    }

    public List<String> getParrafos() {
        return parrafos;
    }
}
