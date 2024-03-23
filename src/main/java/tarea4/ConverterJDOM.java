package tarea4;

import org.jdom2.*;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Ejercicio 3
 *
 * @author Josue Santamaria
 */

public class ConverterJDOM {
    private static Document documento;
    static String path = "src/main/xml/libro.xml";

    public static void main(String[] args) {
        documento = parseToJDOM(path);
        Editor editor = new Editor();
        editor.mostrarInterfaz();
    }

    public static Document parseToJDOM(String path) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document domDocument = builder.parse(new File(path));

            DOMBuilder domBuilder = new DOMBuilder();
            return domBuilder.build(domDocument);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    static void addSection(String tituloSeccion) {
        Element nuevaSeccion = new Element("seccion");
        nuevaSeccion.setAttribute("titulo", tituloSeccion);
        documento.getRootElement().getChild("secciones").addContent(nuevaSeccion);
    }

    static void addChapter(String tituloSeccion, String tituloCapitulo) {
        Element nuevoCapitulo = new Element("capitulo");
        nuevoCapitulo.setAttribute("titulo", tituloCapitulo);
        documento.getRootElement().getChild("secciones").getChildren("seccion").stream()
                .filter(seccion -> seccion.getAttributeValue("titulo").equals(tituloSeccion))
                .findFirst()
                .ifPresent(seccion -> seccion.addContent(nuevoCapitulo));
    }

    static void addParraph(String tituloCapitulo, String textoParrafo) {
        Element nuevoParrafo = new Element("parrafo");
        nuevoParrafo.setText(textoParrafo);
        documento.getRootElement().getChild("secciones").getChildren("seccion").stream()
                .flatMap(seccion -> seccion.getChildren("capitulo").stream())
                .filter(capitulo -> capitulo.getAttributeValue("titulo").equals(tituloCapitulo))
                .findFirst()
                .ifPresent(capitulo -> capitulo.addContent(nuevoParrafo));
    }

    static void save() {
        try {
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            FileWriter writer = new FileWriter("src/main/xml/conversion_libro.xml");
            xmlOutputter.output(documento, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static List<String> getSecciones() {
        List<String> secciones = new LinkedList<>();
        List<Element> seccionesElement = documento.getRootElement().getChild("secciones").getChildren("seccion");
        for (Element seccionElement : seccionesElement) {
            secciones.add(seccionElement.getAttributeValue("titulo"));
        }
        return secciones;
    }

    static List<String> getCapitulos() {
        List<String> capitulos = new LinkedList<>();
        List<Element> seccionesElement = documento.getRootElement().getChild("secciones").getChildren("seccion");
        for (Element seccionElement : seccionesElement) {
            List<Element> capitulosElement = seccionElement.getChildren("capitulo");
            for (Element capituloElement : capitulosElement) {
                capitulos.add(capituloElement.getAttributeValue("titulo"));
            }
        }
        return capitulos;
    }

    public static void print() {
        try {
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            String xml = xmlOutputter.outputString(documento);
            System.out.println(xml);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}