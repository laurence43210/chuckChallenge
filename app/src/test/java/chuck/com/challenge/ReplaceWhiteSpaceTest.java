package chuck.com.challenge;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Created by Laurence on 17/09/2016.
 */
public class ReplaceWhiteSpaceTest {

    private String replaceWhiteSpaceInRequest(String string) {
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile("([\\s]+)");
        Matcher m = p.matcher(string);

        while (m.find()) {
            String repString = m.group(1);
            if (repString != null)
                m.appendReplacement(sb, "%20");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    @Test
    public void nameValidation() throws Exception {

        assertEquals("smith%20dixon",replaceWhiteSpaceInRequest("smith dixon"));

        assertEquals("smith%20dixon",replaceWhiteSpaceInRequest("smith       dixon"));

    }


}
