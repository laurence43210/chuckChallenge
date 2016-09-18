package chuck.com.challenge.Classes;

import java.io.Serializable;

/**
 * Created by Laurence on 17/09/2016.
 */
public class JokeEntry implements Serializable {

    int id;

    String joke;

    String[] categories;

    public int getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }

    public String[] getCategories() {
        return categories;
    }
}
