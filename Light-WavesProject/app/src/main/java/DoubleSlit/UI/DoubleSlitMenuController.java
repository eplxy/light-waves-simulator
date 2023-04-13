/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoubleSlit.UI;

import DoubleSlit.Simulation.Parameters;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author sabri
 */
public class DoubleSlitMenuController {

    Stage primaryStage;
    
    private Parameters parameters;
    
    
    @FXML
    Slider sliderWavelength;
    @FXML
    Slider sliderWidth;
    @FXML
    Slider sliderScreen;
    @FXML
    Slider sliderSpacing;
    @FXML
    TextField txtFieldWavelength;
    @FXML
    TextField txtFieldWidth;
    @FXML
    TextField txtFieldScreen;
    @FXML
    TextField txtFieldSpacing;
    @FXML
    RadioButton radioBtnAnimation;
    @FXML
    RadioButton radioBtnGraph;
    @FXML
    Pane paneView;
    @FXML
    Button btnEnter;
    
    GraphController graphController;
    String selectedView;
                

    public DoubleSlitMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }

    public void initialize() throws IOException {
        ToggleGroup viewTG = new ToggleGroup();
        radioBtnAnimation.setToggleGroup(viewTG);
        radioBtnGraph.setToggleGroup(viewTG);
        viewTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                    Toggle o, Toggle n) {

                RadioButton rb = (RadioButton) viewTG.getSelectedToggle();

                if (rb != null) {
                    selectedView = rb.getText();
                    changeView(selectedView);
                }
            }
        });
        radioBtnAnimation.setSelected(true);
        //https://www.geeksforgeeks.org/javafx-slider-class/
        sliderWavelength.valueProperty().addListener(
             new ChangeListener<Number>() {
 
            public void changed(ObservableValue <? extends Number >
                      observable, Number oldValue, Number newValue)
            {
 
                txtFieldWavelength.setText(newValue.toString());
            }
        });
        sliderWidth.valueProperty().addListener(
             new ChangeListener<Number>() {
 
            public void changed(ObservableValue <? extends Number >
                      observable, Number oldValue, Number newValue)
            {
 
                txtFieldWidth.setText(newValue.toString());
            }
        });
        sliderScreen.valueProperty().addListener(
             new ChangeListener<Number>() {
 
            public void changed(ObservableValue <? extends Number >
                      observable, Number oldValue, Number newValue)
            {
 
                txtFieldScreen.setText(newValue.toString());
            }
        });
        sliderSpacing.valueProperty().addListener(
             new ChangeListener<Number>() {
 
            public void changed(ObservableValue <? extends Number >
                      observable, Number oldValue, Number newValue)
            {
 
                txtFieldSpacing.setText(newValue.toString());
            }
        });
        btnEnter.setOnAction((event) -> {
            handleEnter(event, this.primaryStage);
        });
    }
    
    public void changeView(String selectedView){
        if (selectedView.equals("Animation")){
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/animation.fxml"));
            AnimationController animationController = new AnimationController(primaryStage, this.parameters);
            loader.setController(animationController);
            BorderPane root = loader.load();
            paneView.getChildren().clear();
            paneView.getChildren().add(root);

        } catch (IOException e) {
            System.out.println(e);
        }
        }
        else{
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/graph.fxml"));
            GraphController graphController = new GraphController(primaryStage);
            this.graphController = graphController;
            loader.setController(graphController);
            BorderPane root = loader.load();
            paneView.getChildren().clear();
            paneView.getChildren().add(root);

        } catch (IOException e) {
            System.out.println(e);
        }
        }
        
    }
    
    public void handleEnter(ActionEvent event, Stage primaryStage){
        //this.parameters = new Parameters(Double.parseDouble(txtFieldWavelength.getText()), Double.parseDouble(txtFieldWidth.getText()),Double.parseDouble(txtFieldScreen.getText()),Double.parseDouble(txtFieldSpacing.getText()));
        Parameters.setWavelength(Double.parseDouble(txtFieldWavelength.getText()));
        Parameters.setWidth (Double.parseDouble(txtFieldWidth.getText()));
        Parameters.setScreen(Double.parseDouble(txtFieldScreen.getText()));
        Parameters.setSpacing( Double.parseDouble(txtFieldSpacing.getText()));
        System.out.println(Parameters.getSpacing());
        if (selectedView.equals("Graph")){
            this.graphController.plotLine();
          
        }
    }

    

}
