import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Runner {
    private static ArrayList<Result> results = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Validation start...");
        boolean answer = validateXMLSchema("results.xsd","results.xml");
        ResultHandler resultHandler = new ResultHandler();

    }




    public static boolean validateXMLSchema (String xsdPath, String xmlPath){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println(e);
            return false;
        }
        System.out.println("Validation Success");
        return true;
    }
}
