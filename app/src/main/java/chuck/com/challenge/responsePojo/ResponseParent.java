package chuck.com.challenge.responsePojo;

import java.util.List;

/**
 * Created by Laurence on 17/09/2016.
 */
public class ResponseParent {

    String type;

    List<JokeEntry> value;

    public String getType() {
        return type;
    }

    public List<JokeEntry> getValues() {
        return value;
    }

    public void setValue(List<JokeEntry> value) {
        this.value = value;
    }
}
