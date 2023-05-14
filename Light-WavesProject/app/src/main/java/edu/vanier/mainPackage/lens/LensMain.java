package edu.vanier.mainPackage.lens;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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

        so1.fixVerticalPosition();
        l1.fixVerticalPosition();
        so1.getImage().fixVerticalPosition();

        lmc.itemListViewUpdate();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

        Rays.generateRays();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
