package edu.vanier.mainPackage.lens;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author Steven
 */
public class LensMain extends Application {

    //conversion factor, abspos to layoutx 
    public static final double CONVERSIONFACTOR = 15;

    LensMenuController lmc;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lensMenu.fxml"));
        lmc = new LensMenuController(stage, this);
        loader.setController(lmc);
        BorderPane root = loader.load();

        //testing source object
        SourceObject so1 = new SourceObject(30, -20);
        Lens l1 = new Lens(10, 0);
        l1.move(l1.absPos);

        Item.addToList(so1);
        Item.addToList(l1);
        Item.addToList(so1.getImage());

        lmc.itemPane.getChildren().addAll(l1.getNode(), so1.getImage().getNode(), so1.getNode());

        so1.positionFix();
        l1.positionFix();

        so1.setRelPos(LensPhysics.computeRelPos(so1)[0]);
        so1.getImage().update();
        so1.getLabel().updateLabel();
        so1.getImage().getLabel().updateLabel();

        lmc.itemListViewUpdate();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        positionDebug();

        Rays.generateRays();

    }

    public static void main(String[] args) {
        launch(args);
    }

//    public Circle rayDotDebug() {
//        Circle c = new Circle(10);
//
//        lmc.animationPane.setOnMouseClicked((mouseEvent) -> {
//
//            c.setLayoutX(LensPhysics.sourceSearch().getImage().getAbsPos() * CONVERSIONFACTOR + 700);
//
//        });
//        c.setLayoutX(20 * CONVERSIONFACTOR + 700);
//        c.setLayoutY(375);
//        return c;
//    }

    private void positionDebug() {

        VBox box = new VBox();
        Label lblX = new Label();
        Label lblY = new Label();

        lmc.animationPane.setOnMouseMoved((mouseEvent) -> {
            lblX.setText("x=" + mouseEvent.getX());
            lblY.setText("y=" + mouseEvent.getY());
        });

        box.getChildren().addAll(lblX, lblY);
        lmc.controlPane.getChildren().add(box);

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
