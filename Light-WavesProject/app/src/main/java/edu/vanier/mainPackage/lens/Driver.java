package edu.vanier.mainPackage.lens;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Steven
 */
public class Driver extends Application {
    
    
    
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lensMenu.fxml"));
        LensMenuController lmc = new LensMenuController(stage);
        loader.setController(lmc);
        BorderPane root = loader.load();

        //testing source object
        SourceObject so1 = new SourceObject(30,-100);
        Lens l1 = new Lens(10, 0);
        
        Item.addToList(so1);
        Item.addToList(l1);
        Item.addToList(so1.getImage());
        
        lmc.itemPane.getChildren().addAll(so1.getNode(),l1.getNode(), so1.getImage().getNode());
        
        so1.positionFix();
        l1.positionFix();
        //so1.getImage().updatePosition();
        
        
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}

//zoom pane testing
/*
        Pane zoomPane = new Pane();
        zoomPane.setPrefSize(1000, 1000);
        Group group = new Group();
        group.getChildren().addAll(new Rectangle(5, 5),new Circle(5, Color.ANTIQUEWHITE));
        zoomPane.getChildren().add(group);
        zoomPane.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaY() == 0) {
                    return;
                }
                double scaleFactor = (event.getDeltaY() > 0) ? 1.1 : (1 / 1.1);
                group.setScaleX(scaleFactor * group.getScaleX());
                group.setScaleY(scaleFactor * group.getScaleY());
            }
        });
        
        Scene scene = new Scene(zoomPane, 1000, 1000);
        
        stage.setScene(scene);
        
        stage.setTitle(
                "zooming test");
        
        stage.sizeToScene();
        
        stage.show();
 */
