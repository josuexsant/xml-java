package practica8;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
public class Ejercicio3 {
    public static void main(String[] args) {
        try {
            // Cargar el documento XML
            File xmlFile = new File("src/main/xml/100_anios_de_soledad.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Obtener la raíz del documento
            Element rootElement = doc.getDocumentElement();

            // Mostrar información del libro
            System.out.println("Libro: " + rootElement.getAttribute("titulo"));
            NodeList autores = rootElement.getElementsByTagName("autor");
            for (int i = 0; i < autores.getLength(); i++) {
                Element autor = (Element) autores.item(i);
                System.out.println("Autor: " + autor.getAttribute("nombre") + " " + autor.getAttribute("apellido"));
            }

            NodeList secciones = rootElement.getElementsByTagName("seccion");
            for (int i = 0; i < secciones.getLength(); i++) {
                Element seccion = (Element) secciones.item(i);
                System.out.println(seccion.getAttribute("titulo"));

                NodeList capitulos = seccion.getElementsByTagName("capitulo");
                for (int j = 0; j < capitulos.getLength(); j++) {
                    Element capitulo = (Element) capitulos.item(j);
                    System.out.println(capitulo.getAttribute("titulo"));

                    NodeList parrafos = capitulo.getElementsByTagName("parrafo");
                    for (int k = 0; k < parrafos.getLength(); k++) {
                        Element parrafo = (Element) parrafos.item(k);
                        System.out.println("    Párrafo: " + parrafo.getTextContent());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
