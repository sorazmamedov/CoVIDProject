package Structures;

import java.text.ParseException;
import java.util.function.Function;

public class StructuralFactory {

    private StructuralFactory() {
    }

    public static Countries create(String api) {
        Countries countries = Countries.getInstance();
        Utils.jsonParse(api, countries);
        return countries;
    }

}
