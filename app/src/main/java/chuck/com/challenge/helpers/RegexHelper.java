package chuck.com.challenge.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chuck.com.challenge.exceptions.NonSplittableNameException;

/**
 * Created by Laurence on 19/09/2016.
 */
public class RegexHelper {

    private static final String NAME_REGEX = "([\\p{L}]{1,20})(\\s)([\\p{L}]([-]?[\\s]?[']?[a-z]){1,20})";

    private static final String WHITE_SPACE_REGEX = "([\\s]+)";


    public static boolean isValidName(String string) {
        return !UIHelper.isStringEmptyOrNull(string)
                && Pattern.matches(NAME_REGEX, string);
    }

    public static String splitNameString(String string,
            boolean firstNameRequired) throws NonSplittableNameException {

        Matcher matcher = Pattern.compile(NAME_REGEX).matcher(string);
        while (matcher.find()) {
            String name = matcher.group(firstNameRequired ? 1 : 3);
            if (!UIHelper.isStringEmptyOrNull(name)) {
                return replaceWhiteSpaceInRequest(name);
            }
        }
        throw new NonSplittableNameException();
    }

    private static String replaceWhiteSpaceInRequest(String string) {
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(WHITE_SPACE_REGEX);
        Matcher m = p.matcher(string);

        while (m.find()) {
            String repString = m.group(1);
            if (repString != null)
                m.appendReplacement(sb, "%20");
        }
        m.appendTail(sb);
        return sb.toString();
    }



}
