package chuck.com.challenge;

import org.junit.Test;

import java.util.regex.Pattern;
import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

public class NameValidationTest {

private static final String NAME_REGEX = "([\\p{L}]{1,20})(\\s)([\\p{L}]([-]?[\\s]?[']?[a-z]){1,20})";

    public boolean isValidName(String string) {
        return Pattern.matches(NAME_REGEX, string);
    }

    @Test
    public void nameValidation() throws Exception {
        assertTrue(isValidName("laurence O'dixon"));
        assertTrue(isValidName("laurence smith-dixon"));
        assertTrue(isValidName("laurence smith dixon"));
        assertTrue(isValidName("laurence smith"));


        assertFalse(isValidName("laure!nce smith-dixon"));
        assertFalse(isValidName("laurenc4e smith dixon"));
        assertFalse(isValidName("laurence smi4th"));
        assertFalse(isValidName("laurencesmith"));
        assertFalse(isValidName("laurence smi)th"));
        assertFalse(isValidName("laurence smi&th"));
        assertFalse(isValidName("laurence smi£th"));
        assertFalse(isValidName("laurence smith-di%on"));
        assertFalse(isValidName("laurence smith-di6on"));
        assertFalse(isValidName("laurenceewivnerohveiruhvueirphveiruhveiruophveiruphveiruo smith-dixon"));
        assertFalse(isValidName("laurence smith-dixewivnerohveiruhvueirphveiruhveiruophveiruphveiruoon"));
        assertFalse(isValidName("laurenceewivnerohveiruhvueirphveiruhveiruophveiruphveiruo smith-dixon vververwvrever"));
        assertFalse(isValidName("laurence smith-dixewivnerohveiruhvueirphveiruhveiruophveiruphveiruoon-nvuihviuereiru"));
        assertFalse(isValidName("laurence smith-dixewivnerohveiruhvueirphveiruhveiruophveiruphveiruoon"));
        assertFalse(isValidName("laurence smith           "));
        assertFalse(isValidName("laurence smith           dixon"));
        assertFalse(isValidName("laurence smith           d"));
        assertFalse(isValidName("laurence                  smith"));



    }
}