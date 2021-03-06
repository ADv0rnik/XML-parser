package by.basnet.irb;

import helper.FileValidator;
import helper.ResultHandler;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Runner {
    static final String XML = "Task/src/resource/results.xml";
    static final String XSD = "Task/src/resource/results.xsd";
    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        System.out.println("Validation start...");
        FileValidator.validateXMLSchema(XSD,XML);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            ResultHandler resultHandler = new ResultHandler();
            parser.parse(new File(XML), resultHandler);
        } catch (IOException e) {
            System.out.println("Parser open error: " + e.toString());
        }
    }
}