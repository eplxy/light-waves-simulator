package edu.vanier.mainPackage.lens;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author stella
 */
public class LensMenuController {

    Stage primaryStage;
    @FXML
    TextField ctrlTextField2, ctrlTextField3, ctrlTextField4, ctrlTextField5, ctrlTextField6;
    @FXML
    TextField ctrlTextField1;
    @FXML
    BorderPane borderPane;
    @FXML
    Line principalAxis;
    @FXML
    Pane animationPane, controlPane, itemPane;
    @FXML
    HBox itemBox;
    @FXML
    AnchorPane midAnchorPane;
    @FXML
    ToggleButton toggleButton;

    
    public LensMenuController(Stage stage) {
        this.primaryStage = stage;
    }
}
