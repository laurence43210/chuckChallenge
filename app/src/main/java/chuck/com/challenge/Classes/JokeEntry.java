package chuck.com.challenge.Classes;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Laurence on 17/09/2016.
 */
public class JokeEntry implements Serializable {

    int id;

    String joke;

    List<String> categories;

    public int getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }

    public List<String> getCategories() {
        return categories;
    }
}
