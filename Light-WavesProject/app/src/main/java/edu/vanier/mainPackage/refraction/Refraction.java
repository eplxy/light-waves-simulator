package edu.vanier.mainPackage.refraction;

import edu.vanier.mainPackage.GeneralSettingsController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 *
 * @author Matthew Hantar
 */
public class Refraction extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        /**
         *Loads the main refraction menu and everything else around it.
         *It loads the menu controller with the initialize method. 
         */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/refractionMenu32.fxml"));
        MenuController menuController = new MenuController(primaryStage);
        loader.setController(menuController);
        BorderPane root = loader.load();
        
        Scene scene = new Scene(root);
        GeneralSettingsController.changeTheme(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        }
        public static void main(String[] args) {
            launch(args);
    }
}
  


