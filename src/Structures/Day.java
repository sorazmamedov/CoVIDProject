package Structures;

import java.util.Date;

public class Day {

    private Date date;
    private int confirmed, recovered, deaths;

    Day(Date date, int confirmed, int deaths, int recovered) {
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public Date getDate() {
        return date;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getRecoverd() {
        return recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    @Override
    public String toString() {
        return date +
                ", " + confirmed +
                ", " + recovered +
                ", " + deaths;
    }
}
