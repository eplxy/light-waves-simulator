package edu.vanier.mainPackage.DoubleSlit.UI;

import edu.vanier.mainPackage.GeneralSettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Sabrina Amoura
 */
public class DoubleSlitMain extends Application{

    public static void main(String[] args) {
        launch(args);
     }
     
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DoubleSlitMenu.fxml"));
        DoubleSlitMenuController menuController = new DoubleSlitMenuController(stage);
        loader.setController(menuController);
        //MainMenuController menuController = new MainMenuController(stage);
        BorderPane root = loader.load();
        Scene scene = new Scene(root);
        GeneralSettingsController.changeTheme(scene);
        stage.setScene(scene);
        stage.show();
    }
    
}