/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage.photoelectriceffect;

import edu.vanier.mainPackage.photoelectriceffect.PhotoelectricMenuController;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

//@author Maesha Mahmud
public class Driver extends Application {

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
        try {
            //set title
            stage.setTitle("PhotoElectric Effect");

            //load fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PhotoelectricEffectMenu.fxml"));

            //connect to the controller class
            PhotoelectricMenuController controller = new PhotoelectricMenuController(stage);
            loader.setController(controller);

            //set and show scene
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();

            //photoelectron animation
            /*
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.017), event -> controller.moveCircle()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();*/

        } catch (Exception e) {
            System.out.println("there was an exception in the program: " + e + " and " + e.getCause());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
