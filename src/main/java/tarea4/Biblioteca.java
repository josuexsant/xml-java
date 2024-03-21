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

public class Biblioteca {
    static Document documento;

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        try {
            documento = biblioteca.crearBiblio();
            biblioteca.mostrar(documento);
            biblioteca.imprimir(documento);
            Explorador explorador = new Explorador();
            explorador.mostrarInterfaz();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public Document crearBiblio() throws ParserConfigurationException, SAXException, IOException {
        File dir = new File("src/main/libros");
        String[] archivos = dir.list();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.newDocument();

        Element raiz = documento.createElement("biblio");
        documento.appendChild(raiz);

        for (String archivo : archivos) {
            Element libro = documento.createElement("libro");
            libro.setAttribute("titulo", archivo);
            raiz.appendChild(libro);
        }

        return documento;
    }

    public void mostrar(Document documento) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(documento), new StreamResult(writer));
            String xml = writer.toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void imprimir(Document documento){
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(documento), new StreamResult(writer));
            String xml = writer.toString();
            FileWriter fileWriter = new FileWriter("src/main/xml/biblioteca.xml");
            transformer.transform(new DOMSource(documento), new StreamResult(fileWriter));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
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
            System.out.println("Visualización "+titulo +" --------------------------------");
            mostrar(documento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
