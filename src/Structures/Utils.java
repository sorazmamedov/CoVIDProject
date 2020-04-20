package Structures;


import REST.IApiCall;
import REST.RestAPI;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

public class Utils {

    private Utils() {}

    public enum TotalType {
        CONFIRMED(0),
        DEATHS(1),
        RECOVERED(2);

        private int value;

        TotalType(int value) {
            this.value = value;
        }
    }

    public static String getTotal(TotalType type, Countries countries){
        double total = 0;

        for (Country country : countries.getCountryList()) {
            int lastDay = country.getDays().size();
            Day day = country.getDays().get(lastDay-1);
            total += getCount().apply(day, type);
        }

        return String.format("%,.0f", total);
    }

    private static BiFunction<Day, TotalType, Integer> getCount() {
        return (d, t) -> {
                if (t.value == 0) {
                    return d.getConfirmed();
                }
                else if (t.value == 1) {
                    return d.getDeaths();
                }
                else {
                    return d.getRecoverd();
                }
            };
    }

    public static String getMaxCountry(TotalType type, Countries countries) {
        int numberOfCases = 0;
        String maxCountryName = "";

        //using for loop
//        for (Country country : countries.getCountryList()) {
//            int lastDay = country.getDays().size();
//            Day day = country.getDays().get(lastDay-1);
//            if (numberOfCases < getCount().apply(day, type)) {
//                numberOfCases = getCount().apply(day, type);
//                maxCountryName = country.getName();
//            }
//        }


        //using stream
        maxCountryName = countries.getCountryList().stream().max(compareCountries(type)).get().getName();

        return maxCountryName;
    }

    private static Comparator<Country> compareCountries(TotalType type) {
        return (a, b) -> {
            Day dayA = a.getDays().get(a.getDays().size() - 1);
            Day dayB = b.getDays().get(b.getDays().size() - 1);

            return getCount().apply(dayA, type) - getCount().apply(dayB, type);
        };
    }


    public static void jsonParse(String api, Countries countries) {
        IApiCall apiCall = RestAPI.getInstance();
        JSONObject response = apiCall.get(api);

        for (String countryName : response.keySet()) {
            countries.getCountryList().add(createCountry(countryName, response.getJSONArray(countryName)));
        }

        countries.getCountryList().sort(Comparator.comparing(Country::getName));
    }


    private static Country createCountry(String countryName, JSONArray days) {
        List<Day> dayList = new ArrayList<>();
        for (int i = 0; i < days.length(); i++) {
            JSONObject dayDetails = days.getJSONObject(i);
            dayList.add(createDay(dayDetails));
        }

        return new Country(countryName, dayList);
    }


    private static Day createDay(JSONObject dayDetails) {
        try {
            String strDate = dayDetails.getString("date");
            Date date = new SimpleDateFormat("yyyy-mm-dd").parse(strDate);
            int confirmed = dayDetails.getInt("confirmed");
            int deaths = dayDetails.getInt("deaths");
            int recovered = dayDetails.getInt("recovered");

            return new Day(date, confirmed, deaths, recovered);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
