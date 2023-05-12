package DoubleSlit.UI;

import DoubleSlit.Simulation.Parameters;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

/**
 *
 * @author sabri
 */
//TODO: 
/*
remove redundant comments (licence)
empty line btween fields if the field has an annotation
use lombok to generate getters and setters
simplify the formula
remove useless empty lines
create constants 
private functions

 */
public class DoubleSlitMenuController {

    Stage primaryStage;

    @FXML
    Slider sliderWavelength;

    @FXML
    Slider sliderWidth;

    @FXML
    Slider sliderSpacing;

    @FXML
    TextField txtFieldWavelength;

    @FXML
    TextField txtFieldWidth;

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
    AnimationController animationController;
    String selectedView;
    private final DecimalFormat dfWavelength = new DecimalFormat("0.00");
    private final DecimalFormat df = new DecimalFormat("0.0000");



    public DoubleSlitMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() throws IOException {
        ToggleGroup viewTG = new ToggleGroup();
        radioBtnAnimation.setToggleGroup(viewTG);
        radioBtnGraph.setToggleGroup(viewTG);
        viewTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
                RadioButton rb = (RadioButton) viewTG.getSelectedToggle();
                if (rb != null) {
                    selectedView = rb.getText();
                    changeView(selectedView);
                }
            }
        });
        radioBtnGraph.setSelected(true);
        sliderWavelength.valueProperty().addListener(
                new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                updateTextField(txtFieldWavelength, newValue);
            }
        });
        sliderWidth.valueProperty().addListener(
                new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                updateTextField(txtFieldWidth, newValue);

            }
        });
        sliderSpacing.valueProperty().addListener(
                new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                updateTextField(txtFieldSpacing, newValue);
            }
        });
        txtFieldWavelength.setOnAction(e->{
              updateSlider(sliderWavelength, txtFieldWavelength.getText());
         });
        txtFieldWidth.setOnAction(e->{
              updateSlider(sliderWavelength, txtFieldWidth.getText());
         });
        txtFieldSpacing.setOnAction(e->{
              updateSlider(sliderWavelength, txtFieldSpacing.getText());
         });

        defaultValues();
    }
    
    public void updateColor(double wavelength, List<Arc> arcList){
        try{
        Color color = animationController.getRaysManager().colorPicker(wavelength);
        
        for(Arc a: arcList){
            a.setStroke(color);
        }
        }catch(NullPointerException e){};
    }

    public void changeView(String selectedView) {
        if (selectedView.equals("Animation")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/animation.fxml"));
                AnimationController animationController = new AnimationController(primaryStage, this);
                this.animationController = animationController;
                
                loader.setController(animationController);
                BorderPane root = loader.load();
                paneView.getChildren().clear();
                paneView.getChildren().add(root);

            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/graph.fxml"));
                GraphController graphController = new GraphController(primaryStage);
                this.graphController = graphController;
                loader.setController(graphController);
                BorderPane root = loader.load();
                paneView.getChildren().clear();
                paneView.getChildren().add(root);
                graphController.plotLine();

            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    private void updateParameters() {
        Parameters.setWavelength(Double.parseDouble(txtFieldWavelength.getText()));
        Parameters.setWidth(Double.parseDouble(txtFieldWidth.getText()));
        Parameters.setSpacing(Double.parseDouble(txtFieldSpacing.getText()));
        if (selectedView.equals("Graph")) {
            this.graphController.plotLine();
        } else {

        }
    }

    private void updateTextField(TextField textField, Number newValue) {
        try {
            if(textField.equals(txtFieldWavelength)){
                textField.setText(dfWavelength.format(newValue).toString());
            
        }else{
            textField.setText(df.format(newValue).toString());
        }
            
            updateParameters();
            if(textField.equals(txtFieldSpacing)){
                updateCircles((double)newValue);
                updateArcs(animationController.getRaysManager().getTopArcList(),
                        animationController.getRaysManager().getBottomArcList());
            }else if(textField.equals(txtFieldWavelength)){
                updateColor(Parameters.getWavelength(),animationController.getRaysManager().getArcList());
            }
            
        } catch (Exception e) {
        }
    }
    
    public void updateArcs(List<Arc> topArcList, List<Arc> bottomArcList) {
        for( Arc a:topArcList){
            a.setCenterY(this.animationController.getCircleTop().getCenterY());
        }
        for( Arc a:bottomArcList){
            a.setCenterY(this.animationController.getCircleBottom().getCenterY());
        }
    }
    public void updateCircles(double spacing){
        try{
        double relativeSpacing = spacing*30;
        double center = this.animationController.animationPane.getLayoutBounds().getCenterY();
        this.animationController.getCircleBottom().setCenterY(center + relativeSpacing);
        this.animationController.getCircleTop().setCenterY(center-relativeSpacing);
        }catch(NullPointerException e){};
    }

    public void updateSlider(Slider slider, String newValue) {
        try {
            slider.setValue(Double.parseDouble(newValue));
        } catch (NumberFormatException e) {
        }
    }

    public void defaultValues() {
        sliderWavelength.setValue(generateRandom(380, 750));
        sliderWidth.setValue(generateRandom(0, 10));
        sliderSpacing.setValue(generateRandom(0, 10));
        if (selectedView.equals("Graph")) {
            this.graphController.plotLine();
        }
        else{
            
        }
    }

    private double generateRandom(double min, double max) {
        return Math.random() * (max - min + 1) + min;
    }

}
