package edu.vanier.mainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
     }
     
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
        MainMenuController menuController = new MainMenuController(stage);
        loader.setController(menuController);
        
        BorderPane root = loader.load();
        
        
        Scene scene = new Scene(root);
        stage.setResizable(false);
        //stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
    }

    
}
