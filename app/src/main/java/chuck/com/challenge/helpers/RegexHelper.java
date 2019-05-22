package chuck.com.challenge.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;

import chuck.com.challenge.exceptions.NonSplittableNameException;

/**
 * Created by Laurence on 19/09/2016.
 *
 * Helper class that handled regex checking for the replace name functionality
 */
public class RegexHelper {

    private UIHelper uiHelper;

    public RegexHelper(UIHelper uiHelper) {
        this.uiHelper = uiHelper;
    }

    /** regex which looks for 3 groups. the first group 1 - 25 of any language character, the second group is whitespace
     * used to split the string at a later point. The final group is another 1-25 language character that allow and optional "-"
     * "'" or " ". The regex will only allow these special characters if they are wrapped in language characters.
    */

    private static final String NAME_REGEX = "([\\p{L}]{1,25})(\\s)([\\p{L}]([-]?[\\s]?[']?[a-z]){1,25})";

    /**
     * a simple regex for finding white space in a string.
     */

    /**
     *Checks if the supplied string meets the NAME_REGEX requirements
     *
     * @param string the string to check against
     * @return      if supplied string fulfills requirements.
     */

    public boolean isValidName(String string) {
        return !uiHelper.isStringEmptyOrNull(string)
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

    public String splitNameString(@NonNull String string,
            boolean returnFirstName) throws NonSplittableNameException {

        Matcher matcher = Pattern.compile(NAME_REGEX).matcher(string);
        while (matcher.find()) {
            String name = matcher.group(returnFirstName ? 1 : 3);
            if (!uiHelper.isStringEmptyOrNull(name)) {
                return name;
            }
        }
        throw new NonSplittableNameException();
    }

}
