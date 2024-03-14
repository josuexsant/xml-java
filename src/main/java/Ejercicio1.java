import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Ejercicio1 extends DefaultHandler {
    public boolean enParrafo;

    public static void main(String[] args) {
        try {
            File inputFile = new File("src/main/xml/libro.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            Ejercicio1 handler = new Ejercicio1();
            saxParser.parse(inputFile, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private StringBuilder value;

    public Ejercicio1() {
        this.value = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "libro":
                String titulo = attributes.getValue("titulo");
                System.out.println("Titulo: " +titulo);
                break;
            case "autor":
                System.out.print("Autor: ");
                String apellido = attributes.getValue("apellido");
                String nombre = attributes.getValue("nombre");
                System.out.println(apellido + " " + nombre);
                break;
            case "seccion":
                titulo = attributes.getValue("titulo");
                System.out.println(titulo);
                break;
            case "capitulo":
                titulo = attributes.getValue("titulo");
                System.out.println(titulo);
            case "parrado":
                enParrafo = true;


        }

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (enParrafo) {
            String contenido = new String(ch, start, length);
            System.out.println(contenido);

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("parrafo")) {
            enParrafo = false;
        }
    }
}
