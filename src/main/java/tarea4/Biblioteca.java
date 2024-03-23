package tarea4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Ejercicio 1
 *
 * @author Josue Santamaria
 */

public class Biblioteca {
    static Document documento;

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        try {
            documento = biblioteca.crearBiblio();
            biblioteca.imprimir(documento);
            biblioteca.guardar(documento);
            Explorador explorador = new Explorador();
            explorador.mostrarInterfaz();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este metodo nos ayuda a crear un objeto de tipo Docuemnt que va a contener el XML que vamos a crear
     * el XML se llenara con un directorio de ficheros que son los archivos XML que hay disponibles en la
     * biblioteca, cada libro se convertira en un elemento del XML resultante
     *
     * @return Se retorna un objeto de tipo Document con el que se van a realizar las operacionens
     */
    public Document crearBiblio() throws ParserConfigurationException, SAXException, IOException {
        File dir = new File("src/main/libros");
        String[] files = dir.list();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.newDocument();

        Element raiz = documento.createElement("biblio");
        documento.appendChild(raiz);

        if (!(files == null)) {
            for (String file : files) {
                Element libro = documento.createElement("libro");
                libro.setAttribute("titulo", file);
                raiz.appendChild(libro);
            }
        }
        return documento;
    }

    /**
     * Es metodo imprime en consola un doucmento XML formateado,
     * @param documento
     */
    public void imprimir(Document documento) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(documento), new StreamResult(writer));
            String xml = writer.toString();
            System.out.println(xml);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void guardar(Document documento) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(documento), new StreamResult(writer));
            FileWriter fileWriter = new FileWriter("src/main/xml/biblioteca.xml");
            transformer.transform(new DOMSource(documento), new StreamResult(fileWriter));
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public LinkedList<String> getLibros() {
        LinkedList<String> libros = new LinkedList<>();
        NodeList nodeList = documento.getElementsByTagName("libro");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            String titulo = element.getAttribute("titulo");
            libros.add(titulo);
        }
        return libros;
    }

    public void imprimirLibro(String titulo) {
        try {
            File archivo = new File("src/main/libros/" + titulo);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivo);
            System.out.println("Visualización " + titulo + " --------------------------------");
            imprimir(documento);
        } catch (Exception e) {
            System.out.println();
        }
    }
}