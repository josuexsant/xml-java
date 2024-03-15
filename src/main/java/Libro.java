import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Libro {

    private String titulo;
    private LinkedList<Seccion> secciones;

    public Libro(String titulo) {
        this.titulo = titulo;
        this.secciones = new LinkedList<>();
    }

    public List<Seccion> getSecciones(){
        return secciones;
    }

    public void agregarSeccion(String tituloSeccion) {
        Seccion seccion = new Seccion(tituloSeccion);
        secciones.add(seccion);
    }

    public void agregarCapitulo(String tituloSeccion, String tituloCapitulo) {
        for (Seccion seccion : secciones) {
            if (seccion.getTitulo().equals(tituloSeccion)) {
                seccion.agregarCapitulo(tituloCapitulo);
                break;
            }
        }
    }

    public void agregarParrafo(String tituloCapitulo, String texto) {
        for (Seccion seccion : secciones) {
            for (Capitulo capitulo : seccion.getCapitulos()) {
                if (capitulo.getTitulo().equals(tituloCapitulo)) {
                    capitulo.agregarParrafo(texto);
                    break;
                }
            }
        }
    }

    public String visualizarLibro() {
        String xml = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Crear el documento XML
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("libro");
            rootElement.setAttribute("titulo", titulo);
            doc.appendChild(rootElement);

            // Agregar autores (simulados)
            Element autoresElement = doc.createElement("autores");
            Element autor1 = doc.createElement("autor");
            autor1.setAttribute("nombre", "Edgar Allan");
            autor1.setAttribute("apellido", "Poe");
            autoresElement.appendChild(autor1);
            Element autor2 = doc.createElement("autor");
            autor2.setAttribute("nombre", "Gabriel");
            autor2.setAttribute("apellido", "Garcia Marquez");
            autoresElement.appendChild(autor2);
            rootElement.appendChild(autoresElement);

            // Agregar secciones y capítulos
            Element seccionesElement = doc.createElement("secciones");
            for (Seccion seccion : secciones) {
                Element seccionElement = doc.createElement("seccion");
                seccionElement.setAttribute("titulo", seccion.getTitulo());
                for (Capitulo capitulo : seccion.getCapitulos()) {
                    Element capituloElement = doc.createElement("capitulo");
                    capituloElement.setAttribute("titulo", capitulo.getTitulo());
                    for (String parrafo : capitulo.getParrafos()) {
                        Element parrafoElement = doc.createElement("parrafo");
                        parrafoElement.setTextContent(parrafo);
                        capituloElement.appendChild(parrafoElement);
                    }
                    seccionElement.appendChild(capituloElement);
                }
                seccionesElement.appendChild(seccionElement);
            }
            rootElement.appendChild(seccionesElement);

            // Transformar el documento XML a texto
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            xml = writer.toString();
            FileWriter fileWriter = new FileWriter("src/main/xml/xml-creado.xml");
            transformer.transform(new DOMSource(doc), new StreamResult(fileWriter));
            fileWriter.close();

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }


    public static void main(String[] args) {
        Ventana ventana = new Ventana();
        ventana.mostrarInterfaz();
    }

}


