import org.w3c.dom.ls.LSOutput;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ResultHandler extends DefaultHandler {
    static  final String STUDENT_TAG = "student";
    static  final String LOGIN_TAG = "login";
    static  final String TESTS_TAG = "tests";
    static  final String TEST_TAG = "test";

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Parsing complete");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Start element: " + qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("End element: " + qName);
    }
}
