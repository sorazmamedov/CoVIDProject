package GUI;

import Structures.Countries;
import Structures.StructuralFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String coronaAPI = "https://pomber.github.io/covid19/timeseries.json";
    private final String styles = "stylesheet.css";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CoVID-19");

        Scene scene = new Scene(root, 800, 675);
        scene.getStylesheets().add(styles);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        StructuralFactory.create(coronaAPI);
        launch(args);
    }
}
