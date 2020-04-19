package Structures;

import java.util.List;

public class Country {
    private String name;
    private List<Day> days;

    Country(String name, List<Day> days) {
        this.name = name;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public List<Day> getDays() {
        return days;
    }

    @Override
    public String toString() {
        return name + ' ' +
                ", days=" + days;
    }
}
