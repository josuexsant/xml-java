package practica8;

import org.xml.sax.*;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Ejercicio2 implements ContentHandler {
    public Boolean enParrafo;
    public StringBuilder texto;
    public static void main(String[] args) {

        // Creamos el handler para visualizar los textos en la consola
        ContentHandler handler = new Ejercicio2();
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new FileReader("src/main/xml/100_anios_de_soledad.xml")));

            // Creamos el filtro para convertir los textos a mayúsculas
            ContentHandler filtroMayusculas = new FiltroMayusculas(new OutputStreamWriter(System.out));

            // Utilizamos SAX para parsear el documento XML y aplicar el filtro
            xmlReader.setContentHandler(filtroMayusculas);
            xmlReader.parse(new InputSource(new FileReader("src/main/xml/100_anios_de_soledad.xml")));
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

    public Ejercicio2(){
        texto = new StringBuilder();
        enParrafo = false;
    }
    /**
     * Receive an object for locating the origin of SAX document events.
     *
     * <p>SAX parsers are strongly encouraged (though not absolutely
     * required) to supply a locator: if it does so, it must supply
     * the locator to the application by invoking this method before
     * invoking any of the other methods in the ContentHandler
     * interface.</p>
     *
     * <p>The locator allows the application to determine the end
     * position of any document-related event, even if the parser is
     * not reporting an error.  Typically, the application will
     * use this information for reporting its own errors (such as
     * character content that does not match an application's
     * business rules).  The information returned by the locator
     * is probably not sufficient for use with a search engine.</p>
     *
     * <p>Note that the locator will return correct information only
     * during the invocation SAX event callbacks after
     * {@link #startDocument startDocument} returns and before
     * {@link #endDocument endDocument} is called.  The
     * application should not attempt to use it at any other time.</p>
     *
     * @param locator an object that can return the location of
     *                any SAX document event
     * @see Locator
     */
    @Override
    public void setDocumentLocator(Locator locator) {

    }

    /**
     * Receive notification of the beginning of a document.
     *
     * <p>The SAX parser will invoke this method only once, before any
     * other event callbacks (except for {@link #setDocumentLocator
     * setDocumentLocator}).</p>
     *
     * @throws SAXException any SAX exception, possibly
     *                      wrapping another exception
     * @see #endDocument
     */
    @Override
    public void startDocument() throws SAXException {

    }

    /**
     * Receives notification of the XML declaration.
     *
     * @param version    the version string as in the input document, null if not
     *                   specified
     * @param encoding   the encoding string as in the input document, null if not
     *                   specified
     * @param standalone the standalone string as in the input document, null if
     *                   not specified
     * @throws SAXException if the application wants to report an error or
     *                      interrupt the parsing process
     * @implSpec The default implementation in the SAX API is to do nothing.
     * @since 14
     */
    @Override
    public void declaration(String version, String encoding, String standalone) throws SAXException {
        ContentHandler.super.declaration(version, encoding, standalone);
    }

    /**
     * Receive notification of the end of a document.
     *
     * <p>
     * This method is invoked by the parser to signal it has reached the end of
     * the document after successfully completing the parsing process.
     * After the event, the parser will return the control to the application.
     *
     * @throws SAXException any SAX exception, possibly
     *                      wrapping another exception
     * @apiNote In case of a fatal error, the parser may choose to stop the
     * parsing process with a {@link SAXException}, in which case, this method
     * will never be called. Refer to
     * {@link ErrorHandler#fatalError(SAXParseException)}.
     * @see #startDocument
     */
    @Override
    public void endDocument() throws SAXException {

    }

    /**
     * Begin the scope of a prefix-URI Namespace mapping.
     *
     * <p>The information from this event is not necessary for
     * normal Namespace processing: the SAX XML reader will
     * automatically replace prefixes for element and attribute
     * names when the <code>http://xml.org/sax/features/namespaces</code>
     * feature is <var>true</var> (the default).</p>
     *
     * <p>There are cases, however, when applications need to
     * use prefixes in character data or in attribute values,
     * where they cannot safely be expanded automatically; the
     * start/endPrefixMapping event supplies the information
     * to the application to expand prefixes in those contexts
     * itself, if necessary.</p>
     *
     * <p>Note that start/endPrefixMapping events are not
     * guaranteed to be properly nested relative to each other:
     * all startPrefixMapping events will occur immediately before the
     * corresponding {@link #startElement startElement} event,
     * and all {@link #endPrefixMapping endPrefixMapping}
     * events will occur immediately after the corresponding
     * {@link #endElement endElement} event,
     * but their order is not otherwise
     * guaranteed.</p>
     *
     * <p>There should never be start/endPrefixMapping events for the
     * "xml" prefix, since it is predeclared and immutable.</p>
     *
     * @param prefix the Namespace prefix being declared.
     *               An empty string is used for the default element namespace,
     *               which has no prefix.
     * @param uri    the Namespace URI the prefix is mapped to
     * @throws SAXException the client may throw
     *                      an exception during processing
     * @see #endPrefixMapping
     * @see #startElement
     */
    @Override
    public void startPrefixMapping  (String prefix, String uri) throws SAXException {

    }

    /**
     * End the scope of a prefix-URI mapping.
     *
     * <p>See {@link #startPrefixMapping startPrefixMapping} for
     * details.  These events will always occur immediately after the
     * corresponding {@link #endElement endElement} event, but the order of
     * {@link #endPrefixMapping endPrefixMapping} events is not otherwise
     * guaranteed.</p>
     *
     * @param prefix the prefix that was being mapped.
     *               This is the empty string when a default mapping scope ends.
     * @throws SAXException the client may throw
     *                      an exception during processing
     * @see #startPrefixMapping
     * @see #endElement
     */
    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    /**
     * Receive notification of the beginning of an element.
     *
     * <p>The Parser will invoke this method at the beginning of every
     * element in the XML document; there will be a corresponding
     * {@link #endElement endElement} event for every startElement event
     * (even when the element is empty). All of the element's content will be
     * reported, in order, before the corresponding endElement
     * event.</p>
     *
     * <p>This event allows up to three name components for each
     * element:</p>
     *
     * <ol>
     * <li>the Namespace URI;</li>
     * <li>the local name; and</li>
     * <li>the qualified (prefixed) name.</li>
     * </ol>
     *
     * <p>Any or all of these may be provided, depending on the
     * values of the <var>http://xml.org/sax/features/namespaces</var>
     * and the <var>http://xml.org/sax/features/namespace-prefixes</var>
     * properties:</p>
     *
     * <ul>
     * <li>the Namespace URI and local name are required when
     * the namespaces property is <var>true</var> (the default), and are
     * optional when the namespaces property is <var>false</var> (if one is
     * specified, both must be);</li>
     * <li>the qualified name is required when the namespace-prefixes property
     * is <var>true</var>, and is optional when the namespace-prefixes property
     * is <var>false</var> (the default).</li>
     * </ul>
     *
     * <p>Note that the attribute list provided will contain only
     * attributes with explicit values (specified or defaulted):
     * #IMPLIED attributes will be omitted.  The attribute list
     * will contain attributes used for Namespace declarations
     * (xmlns* attributes) only if the
     * <code>http://xml.org/sax/features/namespace-prefixes</code>
     * property is true (it is false by default, and support for a
     * true value is optional).</p>
     *
     * <p>Like {@link #characters characters()}, attribute values may have
     * characters that need more than one <code>char</code> value.  </p>
     *
     * @param uri       the Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed
     * @param localName the local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed
     * @param qName     the qualified name (with prefix), or the
     *                  empty string if qualified names are not available
     * @param atts      the attributes attached to the element.  If
     *                  there are no attributes, it shall be an empty
     *                  Attributes object.  The value of this object after
     *                  startElement returns is undefined
     * @throws SAXException any SAX exception, possibly
     *                      wrapping another exception
     * @see #endElement
     * @see Attributes
     * @see AttributesImpl
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        switch (qName) {
            case "libro":
                String titulo = atts.getValue("titulo");
                System.out.println("Titulo: " +titulo);
                break;
            case "autor":
                System.out.print("Autor: ");
                String apellido = atts.getValue("apellido");
                String nombre = atts.getValue("nombre");
                System.out.println(apellido + " " + nombre);
                break;
            case "seccion":
                titulo = atts.getValue("titulo");
                System.out.println(titulo);
                break;
            case "capitulo":
                titulo = atts.getValue("titulo");
                System.out.println(titulo);
            case "parrado":
                enParrafo = true;
        }
    }

    /**
     * Receive notification of the end of an element.
     *
     * <p>The SAX parser will invoke this method at the end of every
     * element in the XML document; there will be a corresponding
     * {@link #startElement startElement} event for every endElement
     * event (even when the element is empty).</p>
     *
     * <p>For information on the names, see startElement.</p>
     *
     * @param uri       the Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed
     * @param localName the local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed
     * @param qName     the qualified XML name (with prefix), or the
     *                  empty string if qualified names are not available
     * @throws SAXException any SAX exception, possibly
     *                      wrapping another exception
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("parrafo")) {
            enParrafo = false;
            System.out.println("Texto: " + texto.toString().trim());
        }
    }

    /**
     * Receive notification of character data.
     *
     * <p>The Parser will call this method to report each chunk of
     * character data.  SAX parsers may return all contiguous character
     * data in a single chunk, or they may split it into several
     * chunks; however, all of the characters in any single event
     * must come from the same external entity so that the Locator
     * provides useful information.</p>
     *
     * <p>The application must not attempt to read from the array
     * outside of the specified range.</p>
     *
     * <p>Individual characters may consist of more than one Java
     * <code>char</code> value.  There are two important cases where this
     * happens, because characters can't be represented in just sixteen bits.
     * In one case, characters are represented in a <em>Surrogate Pair</em>,
     * using two special Unicode values. Such characters are in the so-called
     * "Astral Planes", with a code point above U+FFFF.  A second case involves
     * composite characters, such as a base character combining with one or
     * more accent characters. </p>
     *
     * <p> Your code should not assume that algorithms using
     * <code>char</code>-at-a-time idioms will be working in character
     * units; in some cases they will split characters.  This is relevant
     * wherever XML permits arbitrary characters, such as attribute values,
     * processing instruction data, and comments as well as in data reported
     * from this method.  It's also generally relevant whenever Java code
     * manipulates internationalized text; the issue isn't unique to XML.</p>
     *
     * <p>Note that some parsers will report whitespace in element
     * content using the {@link #ignorableWhitespace ignorableWhitespace}
     * method rather than this one (validating parsers <em>must</em>
     * do so).</p>
     *
     * @param ch     the characters from the XML document
     * @param start  the start position in the array
     * @param length the number of characters to read from the array
     * @throws SAXException any SAX exception, possibly
     *                      wrapping another exception
     * @see #ignorableWhitespace
     * @see Locator
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (enParrafo) {
            String contenido = new String(ch, start, length);
            System.out.println(contenido);
        }
    }

    /**
     * Receive notification of ignorable whitespace in element content.
     *
     * <p>Validating Parsers must use this method to report each chunk
     * of whitespace in element content (see the W3C XML 1.0
     * recommendation, section 2.10): non-validating parsers may also
     * use this method if they are capable of parsing and using
     * content models.</p>
     *
     * <p>SAX parsers may return all contiguous whitespace in a single
     * chunk, or they may split it into several chunks; however, all of
     * the characters in any single event must come from the same
     * external entity, so that the Locator provides useful
     * information.</p>
     *
     * <p>The application must not attempt to read from the array
     * outside of the specified range.</p>
     *
     * @param ch     the characters from the XML document
     * @param start  the start position in the array
     * @param length the number of characters to read from the array
     * @throws SAXException any SAX exception, possibly
     *                      wrapping another exception
     * @see #characters
     */
    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    /**
     * Receive notification of a processing instruction.
     *
     * <p>The Parser will invoke this method once for each processing
     * instruction found: note that processing instructions may occur
     * before or after the main document element.</p>
     *
     * <p>A SAX parser must never report an XML declaration (XML 1.0,
     * section 2.8) or a text declaration (XML 1.0, section 4.3.1)
     * using this method.</p>
     *
     * <p>Like {@link #characters characters()}, processing instruction
     * data may have characters that need more than one <code>char</code>
     * value. </p>
     *
     * @param target the processing instruction target
     * @param data   the processing instruction data, or null if
     *               none was supplied.  The data does not include any
     *               whitespace separating it from the target
     * @throws SAXException any SAX exception, possibly
     *                      wrapping another exception
     */
    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    /**
     * Receive notification of a skipped entity.
     * This is not called for entity references within markup constructs
     * such as element start tags or markup declarations.  (The XML
     * recommendation requires reporting skipped external entities.
     * SAX also reports internal entity expansion/non-expansion, except
     * within markup constructs.)
     *
     * <p>The Parser will invoke this method each time the entity is
     * skipped.  Non-validating processors may skip entities if they
     * have not seen the declarations (because, for example, the
     * entity was declared in an external DTD subset).  All processors
     * may skip external entities, depending on the values of the
     * <code>http://xml.org/sax/features/external-general-entities</code>
     * and the
     * <code>http://xml.org/sax/features/external-parameter-entities</code>
     * properties.</p>
     *
     * @param name the name of the skipped entity.  If it is a
     *             parameter entity, the name will begin with '%', and if
     *             it is the external DTD subset, it will be the string
     *             "[dtd]"
     * @throws SAXException any SAX exception, possibly
     *                      wrapping another exception
     */
    @Override
    public void skippedEntity(String name) throws SAXException {

    }

    static class FiltroMayusculas extends XMLFilterImpl {
        private final Writer salida;

        public FiltroMayusculas(Writer salida) {
            this.salida = salida;
        }

        @Override
        public void setDocumentLocator(Locator locator) {
        }

        @Override
        public void startDocument() throws SAXException {
        }

        @Override
        public void endDocument() throws SAXException {
            try {
                salida.close();
            } catch (IOException e) {
                throw new SAXException(e);
            }
        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            try {
                String texto = new String(ch, start, length).toUpperCase();
                salida.write(texto);
            } catch (IOException e) {
                throw new SAXException(e);
            }
        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        }

        @Override
        public void processingInstruction(String target, String data) throws SAXException {
        }

        @Override
        public void skippedEntity(String name) throws SAXException {
        }

    }
}
