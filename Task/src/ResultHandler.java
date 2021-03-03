import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultHandler extends DefaultHandler {
    static final String LOGIN_TAG = "login";
    static final String TEST_TAG = "test";

    private String currentTag = null;
    private List<Result> resultsList = null;
    private StringBuilder data = null;
    boolean isLogin = false;

    @Override
    public void startDocument() {
        System.out.println("Start parsing...");
    }

    @Override
    public void endDocument() {
        for (Result res: resultsList){
            System.out.println(res);
        }
        System.out.println("Parsing complete");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentTag = qName;
        switch (currentTag) {
            case LOGIN_TAG -> {
                isLogin = true;
                data = new StringBuilder();
            }
            case TEST_TAG -> {
                Result result = new Result();
                if (resultsList == null) {
                    resultsList = new ArrayList<>();
                }
                result.setLogin(data.toString());
                String name = attributes.getValue("name");
                result.setTest(name);
                String tmp = attributes.getValue("date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = dateFormat.parse(tmp);
                    result.setDate(date);
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
        if (qName.equals(LOGIN_TAG)) {
            isLogin = false;
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