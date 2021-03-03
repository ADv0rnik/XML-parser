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
    boolean isStudent = false;
    boolean isLogin = false;
    boolean isTests = false;
    boolean isTest = false;

    public List<Result> getResultsList() {
        return resultsList;
    }

    @Override
    public void startDocument() {
        System.out.println("Start parsing...");
    }

    @Override
    public void endDocument() {

        System.out.println("Parsing complete");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentTag = qName;
        switch (currentTag) {
            case STUDENT_TAG: {
                isStudent = true;
                result = new Result();
                if (resultsList == null) {
                    resultsList = new ArrayList<>();
                }
            }
            break;
            case LOGIN_TAG: {
                isLogin = true;
                data = new StringBuilder();
            }
            break;
            case TEST_TAG: {
                result.setLogin(data.toString());
                String name = attributes.getValue("name");
                result.setTest(name);
                String tmp = attributes.getValue("date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = dateFormat.parse(tmp);
                    result.setDate(date); //TODO: check date format
                } catch (ParseException e) {
                    System.out.println("Parse error: " + e.toString());
                }
                int mark = (int) Math.round(Double.parseDouble(attributes.getValue("mark")));
                result.setMark(mark);
                resultsList.add(result);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (isLogin && currentTag.equals(LOGIN_TAG)) {
            isLogin = false;
        } else if (isStudent && qName.equals(STUDENT_TAG)){
            isStudent = false;
        }
        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (isLogin) {
            data.append(new String(ch, start, length));
        }
    }
}