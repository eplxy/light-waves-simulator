package photoelectriceffect.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import photoelectriceffect.controllers.MenuController;

/**
 * This is a JavaFX project template to be used for creating GUI applications.
 * JavaFX 18 is already linked to this project in the build.gradle file.
 * @link: https://openjfx.io/javadoc/18/
 * @see: Build Scripts/build.gradle
 * @author Maesha Mahmud
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Hit The Target");
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PhotoelectricEffectMenu.fxml"));
        MenuController mainController = new MenuController(stage);
        loader.setController(mainController);
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 1500, 800);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        }catch(Exception e){
            System.out.println(e);
        }
    
    }

    public static void main(String[] args) {
        launch(args);
    }
}