package GUI;

import Structures.Countries;
import Structures.Utils;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Label lblConfirmed, lblRecovered, lblDeaths, lblMostCaseCountry, lblMostRecoveredCountry, lblMostDeathsCountry, lblSpecCountry;
    @FXML
    private ComboBox<String> comboCountries;


    @FXML
    private void initialize() {
        if (Countries.getInstance().getCountryList().size() != 0) {
            lblConfirmed.setText(Utils.getTotal(Utils.TotalType.CONFIRMED, Countries.getInstance()));
            lblRecovered.setText(Utils.getTotal(Utils.TotalType.RECOVERED, Countries.getInstance()) + "");
            lblDeaths.setText(Utils.getTotal(Utils.TotalType.DEATHS, Countries.getInstance()) + "");

            lblMostCaseCountry.setText(Utils.getMaxCountry(Utils.TotalType.CONFIRMED, Countries.getInstance()));
            lblMostRecoveredCountry.setText(Utils.getMaxCountry(Utils.TotalType.RECOVERED, Countries.getInstance()));
            lblMostDeathsCountry.setText(Utils.getMaxCountry(Utils.TotalType.DEATHS, Countries.getInstance()));

            populateComboBox();
            comboCountries.getSelectionModel().selectFirst();
            comboCountries.valueProperty().addListener(onCountryChange());
        }

    }

    private ChangeListener<? super String> onCountryChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Countries.getInstance().getCountryList().stream().filter(country -> country.getName().equals(t1)).forEach(country -> {
                    int lastIndex = country.getDays().size()-1;
                    lblSpecCountry.setText("Total deaths: " + String.format("%,.0f", (double)country.getDays().get(lastIndex).getDeaths()) + "\n" +
                                            "Total confirmed: " + String.format("%,.0f", (double)country.getDays().get(lastIndex).getConfirmed()) + "\n" +
                                            "Total recovered: " + String.format("%,.0f", (double)country.getDays().get(lastIndex).getRecoverd()));
                });
            }
        };
    }

    private void populateComboBox() {
        ObservableList<String> countryNames = comboCountries.getItems();
        countryNames.add("Select");
        Countries.getInstance().getCountryList().stream().forEach(country -> countryNames.add(country.getName()));

        comboCountries.setItems(countryNames);
    }
}