package edu.vanier.mainPackage.photoelectriceffect;

import edu.vanier.mainPackage.photoelectriceffect.MainAppController;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

//@author Maesha Mahmud
public class PhotoelectricMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            //set title
            stage.setTitle("PhotoElectric Effect");

            //load fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PhotoelectricEffectMenu.fxml"));

            //connect to the controller class
            MainAppController controller = new MainAppController();
            loader.setController(controller);

            //set and show scene
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();

        } catch (Exception e) {
            System.out.println("there was an exception in the program: " + e + " and " + e.getCause());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
