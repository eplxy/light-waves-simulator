package edu.vanier.mainPackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is a JavaFX project template to be used for creating GUI applications.
 * JavaFX 18 is already linked to this project in the build.gradle file.
 * @link: https://openjfx.io/javadoc/18/
 * @see: Build Scripts/build.gradle
 * @author Sleiman Rabah.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        VBox root = new VBox();
        
        Scene scene = new Scene(root, 600, 600);
        
        stage.setScene(scene);        

        stage.setTitle("This is a JavaFX app template...");
        
        stage.sizeToScene();
        
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}