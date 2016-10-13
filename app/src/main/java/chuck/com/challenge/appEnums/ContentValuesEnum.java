package chuck.com.challenge.appEnums;

/**
 * Created by Laurence on 17/09/2016.
 */
public enum ContentValuesEnum {

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
