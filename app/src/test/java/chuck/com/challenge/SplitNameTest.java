package chuck.com.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import chuck.com.challenge.exceptions.UnSplittableNameException;
import chuck.com.challenge.helpers.RegexHelper;

/**
 * Created by Laurence on 17/09/2016.
 */
public class SplitNameTest {

    @Test
    public void replaceWhiteSpaceTest_GreenPath() throws Exception {

        assertEquals("smith%20dixon",
                RegexHelper.splitNameString("laurence smith dixon", false));
        assertEquals("laurence",
                RegexHelper.splitNameString("laurence smith dixon", true));
        assertEquals("smith-dixon",
                RegexHelper.splitNameString("laurence smith-dixon", false));
        assertEquals("smith%20dixon%20jiojidso", RegexHelper.splitNameString(
                "laurence smith dixon jiojidso", false));
    }

    @Test(expected = UnSplittableNameException.class)
    public void replaceWhiteSpaceTest_RedPath() throws Exception {
            RegexHelper.splitNameString("laurencesmithdixon", true);
    }

}
