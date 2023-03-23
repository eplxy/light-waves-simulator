package edu.vanier.mainPackage.lens;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        
        Button b1 = new Button("button");
        TextField t1 = new TextField("abs pos one");
        TextField t2 = new TextField("abs pos two");
        TextField t3 = new TextField("focal length");
        VBox vbox = new VBox(b1, t1, t2, t3);
        
        Scene scene = new Scene(vbox, 300, 300);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                SourceObject so = new SourceObject();
                so.setAbsPos(Double.parseDouble(t1.getText()));
                Lens lens = new Lens();
                lens.setAbsPos(Double.parseDouble(t2.getText()));
                lens.setFocalLength(Double.parseDouble(t3.getText()));
                Item.addToList(so);
                Item.addToList(lens);
                System.out.println(LensPhysics.computeImagePosition(so));
                System.out.println(lens.getLensType());
            }
        });
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
