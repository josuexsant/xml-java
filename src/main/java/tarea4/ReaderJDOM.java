package tarea4;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.util.List;

/**
 * Ejercicio 2
 *
 * @author Josue Santamaria
 */
public class ReaderJDOM {
    public static void main(String[] args) {
        try {
            File inputFile = new File("src/main/xml/libro.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element rootElement = document.getRootElement();
            String tituloLibro = rootElement.getAttributeValue("titulo");
            StringBuilder schemaBuilder = new StringBuilder();
            schemaBuilder.append("Libro: ").append(tituloLibro).append("\n");
            List<Element> autores = rootElement.getChild("autores").getChildren("autor");

            for (Element autor : autores) {
                String apellido = autor.getAttributeValue("apellido");
                String nombre = autor.getAttributeValue("nombre");
                schemaBuilder.append("Autor: ").append(apellido).append(" ").append(nombre).append("\n");
            }

            List<Element> secciones = rootElement.getChild("secciones").getChildren("seccion");

            int numeroSeccion = 1;
            for (Element seccion : secciones) {
                schemaBuilder.append(numeroSeccion).append(". ").append(seccion.getAttributeValue("titulo")).append("\n");
                List<Element> capitulos = seccion.getChildren("capitulo");
                int numeroCapitulo = 1;
                for (Element capitulo : capitulos) {
                    schemaBuilder.append("\t").append(numeroSeccion).append(".").append(numeroCapitulo).append(" ")
                            .append(capitulo.getAttributeValue("titulo")).append("\n");

                    List<Element> parrafos = capitulo.getChildren("parrafo");
                    for (Element parrafo : parrafos) {
                        schemaBuilder.append(parrafo.getValue()).append("\n");
                    }
                    numeroCapitulo++;
                }
                numeroSeccion++;
            }
            System.out.println(schemaBuilder);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
