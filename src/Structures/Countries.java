package Structures;

import java.util.ArrayList;
import java.util.List;

public class Countries {
    private List<Country> countryList = new ArrayList<>();
    private static Countries singleton_Countries = null;

    private Countries() {
    }

    public static Countries getInstance() {
        if (singleton_Countries == null) {
            singleton_Countries = new Countries();
        }

        return singleton_Countries;
    }

    public List<Country> getCountryList() {
        return countryList;
    }
}
