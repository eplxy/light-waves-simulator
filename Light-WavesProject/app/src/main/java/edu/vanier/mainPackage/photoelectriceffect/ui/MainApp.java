package edu.vanier.photoelectriceffect.ui;

import edu.vanier.photoelectriceffect.controller.MainAppController;
import edu.vanier.photoelectriceffect.shapes.PhotoElectron;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

//@author Maesha Mahmud
 
public class MainApp extends Application {
    
    /*
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Electrons electrons = new Electrons(50, 50, 10);

        pane.getChildren().add(electrons.getCircle());


        Scene scene = new Scene(pane, 300, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/

    //main
    @Override
    public void start(Stage stage) throws IOException {
        try{
            //set title
            stage.setTitle("PhotoElectric Effect");

            //load fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PhotoelectricEffectMenu.fxml"));
            
            //connect to the controller class
            MainAppController controller = new MainAppController();
            loader.setController(controller);
            
            //set and show scene
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
            //photoelectron animation
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.017), event -> controller.moveCircle()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            
        }catch (Exception e){
            System.out.println("there was an exception in the program: " + e + " and " + e.getCause());
        }
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}