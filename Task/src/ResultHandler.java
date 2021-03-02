import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ResultHandler extends DefaultHandler {
    static final String STUDENT_TAG = "student";
    static final String LOGIN_TAG = "login";
    static final String TESTS_TAG = "tests";
    static final String TEST_TAG = "test";

    private String currentTag = null;
    private Result result;
    List<Result> resultsList = new ArrayList<>();

    @Override
    public void startDocument(){
        System.out.println("Start parsing...");
    }

    @Override
    public void endDocument(){
        System.out.println("Parsing complete");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentTag = qName;
        switch (currentTag){
            case LOGIN_TAG: {
                result = new Result();
            }
            break;
            case TEST_TAG: {
                result.setTest(attributes.getValue("name"));
                String tmp = attributes.getValue("date");
                System.out.println(tmp);
                try {
                    result.setDate(new SimpleDateFormat("yyyy-mm-dd").parse(tmp));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
            break;


            }//System.out.println("Start element: " + qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        /*if (currentTag.equals(STUDENT_TAG)) {

        }*/// System.out.println("End element: " + qName);
        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length);
        if (LOGIN_TAG.equals(currentTag)) {
            result.setLogin(s);
        }
           // System.out.println(s);

    }
}
