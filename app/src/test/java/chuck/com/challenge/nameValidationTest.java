package chuck.com.challenge;

import org.junit.Test;


import chuck.com.challenge.helpers.RegexHelper;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */


public class NameValidationTest {

    @Test
    public void nameValidation_Valid_Names() throws Exception {
        assertTrue(RegexHelper.isValidName("laurence O'dixon"));
        assertTrue(RegexHelper.isValidName("laurence smith-dixon"));
        assertTrue(RegexHelper.isValidName("laurence smith dixon"));
        assertTrue(RegexHelper.isValidName("laurence smith"));
    }

    @Test
    public void nameValidation_illegal_characters() throws Exception {
        assertFalse(RegexHelper.isValidName("laure!nce smith-dixon"));
        assertFalse(RegexHelper.isValidName("laurence s@mith-dixon"));
        assertFalse(RegexHelper.isValidName("laurence smith-di&xon"));
        assertFalse(RegexHelper.isValidName("lau£rence smith-dixon"));
        assertFalse(RegexHelper.isValidName("lau$rence smith"));
        assertFalse(RegexHelper.isValidName("laurence s%mith"));
        assertFalse(RegexHelper.isValidName("laurence s^mith"));
        assertFalse(RegexHelper.isValidName("laurence s*mith"));
    }

    @Test
    public void nameValidation_numbers() throws Exception {
        assertFalse(RegexHelper.isValidName("laurence s1mith"));
        assertFalse(RegexHelper.isValidName("laure3nce smith"));
        assertFalse(RegexHelper.isValidName("laurence smith-dixo4n"));
    }


    @Test
    public void nameValidation_all_groups_satisfied() throws Exception {
        assertFalse(RegexHelper.isValidName("laurencesmith"));
    }

    @Test
    public void nameValidation_groups_too_long() throws Exception {
        assertFalse(RegexHelper.isValidName("laurenceewivnerohveiruhvueirphveiruhveiruophveiruphveiruo smith-dixon"));
        assertFalse(RegexHelper.isValidName("laurence smith-dixewivnerohveiruhvueirphveiruhveiruophveiruphveiruoon"));
        assertFalse(RegexHelper.isValidName("laurenceewivnerohveiruhvueirphveiruhveiruophveiruphveiruo smith-dixon vververwvrever"));
        assertFalse(RegexHelper.isValidName("laurence smith-dixewivnerohveiruhvueirphveiruhveiruophveiruphveiruoon"));
    }
    @Test
    public void nameValidation_too_much_whitespace() throws Exception {
        assertFalse(RegexHelper.isValidName("laurence smith           "));
        assertFalse(RegexHelper.isValidName("laurence smith           dixon"));
        assertFalse(RegexHelper.isValidName("laurence smith           d"));
        assertFalse(RegexHelper.isValidName("laurence                  smith"));

    }
}