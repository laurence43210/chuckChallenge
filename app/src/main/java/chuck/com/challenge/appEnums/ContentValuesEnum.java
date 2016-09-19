package chuck.com.challenge.appEnums;

/**
 * Created by Laurence on 17/09/2016.
 */
public enum ContentValuesEnum {

    JOKES_TO_RETRIEVE("jokes_to_retrieve"),
    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    RESTRICT_EXPLICIT("restrict_explicit"),
    RECYCLER_VIEW("recycler_view"),
    RECYCLER_VIEW_DATA("recycler_view_data");





    String key;

    ContentValuesEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
