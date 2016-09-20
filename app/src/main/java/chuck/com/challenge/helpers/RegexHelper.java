package chuck.com.challenge.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.support.annotation.NonNull;

import chuck.com.challenge.exceptions.NonSplittableNameException;

/**
 * Created by Laurence on 19/09/2016.
 *
 * Helper class that handled regex checking for the replace name functionality
 */
public class RegexHelper {

    /** regex which looks for 3 groups. the first group 1 - 25 of any language character, the second group is whitespace
     * used to split the string at a later point. The final group is another 1-25 language character that allow and optional "-"
     * "'" or " ". The regex will only allow these special characters if they are wrapped in language characters.
    */

    private static final String NAME_REGEX = "([\\p{L}]{1,25})(\\s)([\\p{L}]([-]?[\\s]?[']?[a-z]){1,25})";

    /**
     * a simple regex for finding white space in a string.
     */

    private static final String WHITE_SPACE_REGEX = "([\\s]+)";

    /**
     *Checks if the supplied string meets the NAME_REGEX requirements
     *
     * @param string the string to check against
     * @return      if supplied string fulfills requirements.
     */

    public static boolean isValidName(String string) {
        return !UIHelper.isStringEmptyOrNull(string)
                && Pattern.matches(NAME_REGEX, string.trim());
    }

    /**
     * Takes a full name string, and isolates the first and last names into separate groups using the NAME_REGEX groups.
     * Will then return either first or last name, depending on returnFirstName boolean. Returned names will have been processed by
     * replaceWhiteSpaceInRequest().
     *
     * This method should only be called only after the source string has been validated to avoid exception.
     *
     * @param string the string to check against
     * @param returnFirstName determines if the first name is required, will provide second name if false.
     * @throws NonSplittableNameException when the name is not a valid splittable format, likely to be returned of the source string has not been validated
     * prior to this method
     * @return  either a first or last name, depending on params
     */

    public static String splitNameString(@NonNull String string,
            boolean returnFirstName) throws NonSplittableNameException {

        Matcher matcher = Pattern.compile(NAME_REGEX).matcher(string);
        while (matcher.find()) {
            String name = matcher.group(returnFirstName ? 1 : 3);
            if (!UIHelper.isStringEmptyOrNull(name)) {
                return replaceWhiteSpaceInRequest(name);
            }
        }
        throw new NonSplittableNameException();
    }

    /**
     * finds any white space in the supplied text and replaces it with html space. This is required for the server call
     *
     * @param string the string to check against
     * @return     string formatted for the server.
     */

    private static String replaceWhiteSpaceInRequest(@NonNull String string) {
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
