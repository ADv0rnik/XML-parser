import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Runner {
    private static ArrayList<Result> results = new ArrayList<>();
    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        System.out.println("Validation start...");
        boolean answer = validateXMLSchema("results.xsd","results.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            ResultHandler resultHandler = new ResultHandler();
            parser.parse(new File("results.xml"), resultHandler);
        } catch (IOException | SAXException e) {
            System.out.println("Parser open error: " + e.toString());
        }
    }

    public static boolean validateXMLSchema (String xsdPath, String xmlPath){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Validation failed with error. " + e.toString());
            return false;
        }
        System.out.println("Validation Success");
        return true;
    }
}
