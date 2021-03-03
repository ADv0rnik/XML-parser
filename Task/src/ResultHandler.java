import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultHandler extends DefaultHandler {
    static final String STUDENT_TAG = "student";
    static final String LOGIN_TAG = "login";
    static final String TESTS_TAG = "tests";
    static final String TEST_TAG = "test";

    private String currentTag = null;
    private List<Result> resultsList = null;
    private Result result = null;
    private StringBuilder data = null;
    /*String login, test;
    Date date;
    int mark*/;

    public List<Result> getResultsList() {
        return resultsList;
    }

    boolean isStudent = false;
    boolean isLogin = false;
    boolean isTests = false;


    @Override
    public void startDocument(){
        System.out.println("Start parsing...");
    }

    @Override
    public void endDocument(){
        System.out.println(resultsList.toString());
        System.out.println("Parsing complete");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentTag = qName;
        if (currentTag.equals(STUDENT_TAG)){
            isStudent = true;
        }
       /* if(currentTag.equals(TEST_TAG)){
                test = attributes.getValue("name");
                String tmp = attributes.getValue("date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = dateFormat.parse(tmp);
                   // System.out.println(new SimpleDateFormat("dd.MM.yyyy").format(date));
                } catch (ParseException e) {
                    //System.out.println("Parse error: " + e.toString());
                }
                mark = (int)Math.round(Double.parseDouble(attributes.getValue("mark")));
               // System.out.println(mark);
        }System.out.println("Start element: " + qName);*/
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(STUDENT_TAG)) {
           // result = new Result(login,test,date, mark);
            resultsList.add(result);
        }
        //System.out.println("End element: " + qName);

        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
       /* String s = new String(ch, start, length);
        if (LOGIN_TAG.equals(currentTag)) {
            login = s;
        }
            System.out.println(login);
*/
    }
}
