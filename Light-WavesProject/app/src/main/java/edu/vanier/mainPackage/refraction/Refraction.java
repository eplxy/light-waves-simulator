package edu.vanier.mainPackage.refraction;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Matthew Hantar
 */
public class Refraction extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/refractionMenu22.fxml"));
        MenuController menuController = new MenuController(primaryStage);
        loader.setController(menuController);
        BorderPane root = loader.load();
        
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        }
        public static void main(String[] args) {
            launch(args);
    }
}
  


