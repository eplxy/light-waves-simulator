package edu.vanier.mainPackage.DoubleSlit.UI;

import edu.vanier.mainPackage.DoubleSlit.Simulation.Parameters;
import edu.vanier.mainPackage.MainApp;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author Sabrina Amoura
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
    
    @FXML
    Button btnHelp;
    
    @FXML
    Button btnMainMenu;

    private GraphController graphController;
    private AnimationController animationController;
    private String selectedView;
    private final DecimalFormat dfWavelength = new DecimalFormat("0.00");
    private final DecimalFormat df = new DecimalFormat("0.000");
    private double lastWavelength;

    /**
     * 
     * @param primaryStage 
     */
    public DoubleSlitMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Sets up the radio buttons to switch between the graph and animation, the 
     * parameters text fields and sliders and sets initial random parameters.
     * Loads help window.
     * @throws IOException 
     */
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
            public void changed(ObservableValue<? extends Number> observable, 
                    Number oldValue, Number newValue) {
                updateTextField(txtFieldWavelength, newValue);
            }
        });
        sliderWidth.valueProperty().addListener(
            new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, 
                    Number oldValue, Number newValue) {
                updateTextField(txtFieldWidth, newValue);
            }
        });
        sliderSpacing.valueProperty().addListener(
            new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, 
                    Number oldValue, Number newValue) {
                updateTextField(txtFieldSpacing, newValue);
            }
        });
        
        txtFieldWavelength.setOnAction(e -> {
            updateSlider(sliderWavelength, txtFieldWavelength.getText());
        });
        txtFieldWidth.setOnAction(e -> {
            updateSlider(sliderWidth, txtFieldWidth.getText());
        });
        txtFieldSpacing.setOnAction(e -> {
            updateSlider(sliderSpacing, txtFieldSpacing.getText());
        });
        
        btnMainMenu.setOnAction((event) -> {
            MainApp mainApp = new MainApp();
            try {
                mainApp.start(primaryStage);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        });
        
        btnHelp.setOnAction((event) -> {
            Stage helpStage = new Stage();
            helpStage.setTitle("Double Slit Experiment");
            FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/helpDoubleSlit.fxml"));
                BorderPane root;
            try {
                root = loader.load();
                Scene helpScene = new Scene(root);
                 helpStage.setScene(helpScene);
            helpStage.show();
            } catch (IOException ex) {}
            
            
           
        });

        defaultValues();
    }

    /**
     * Changes the color of the rays when the user changes the wavelength.
     * @param wavelength user set parameter
     * @param arcList List of all the animated arcs
     */
    public void updateColor(double wavelength, List<Arc> arcList) {
        try {
            Color color = animationController.getRaysManager().colorPicker(wavelength);
            for (Arc a : arcList) {
                a.setStroke(color);
            }
        } catch (NullPointerException e) {
        };
    }

    /**
     * Switch between the graph and animation view.
     * @param selectedView option selected by the user
     */
    public void changeView(String selectedView) {
        if (selectedView.equals("Animation")) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/animation.fxml"));
                AnimationController animationController = 
                        new AnimationController(primaryStage, this);
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
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/graph.fxml"));
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

    /**
     * Sets new values for the parameters based on the text fields.
     */
    private void updateParameters() {
        Parameters.setWavelength(Double.parseDouble(txtFieldWavelength.getText()));
        Parameters.setWidth(Double.parseDouble(txtFieldWidth.getText()));
        Parameters.setSpacing(Double.parseDouble(txtFieldSpacing.getText()));
        if (selectedView.equals("Graph")) {
            this.graphController.plotLine();
        } 
    }

    /**
     * Formats the number entered in the text field.
     * If the spacing parameter was modified, moves the slits accordingly.
     * If the wavelength was modified, changes the color of the rays and 
     * resets the animation to change the distance between the rays.
     * @param textField text field that has been modified
     * @param newValue value entered by the user in the text field
     */
    private void updateTextField(TextField textField, Number newValue) {
        try {
            if (textField.equals(txtFieldWavelength)) {
                textField.setText(dfWavelength.format(newValue));
            } else {
                textField.setText(df.format(newValue));
            }

            updateParameters();
            
            if (textField.equals(txtFieldSpacing)) {
                updateCircles((double) newValue, animationController);
                updateArcs(animationController.getRaysManager().getTopArcList(),
                        animationController.getRaysManager().getBottomArcList());
            } else if (textField.equals(txtFieldWavelength)) {
                
                if(selectedView.equals("Animation")){
                    updateColor(Parameters.getWavelength(), 
                        animationController.getRaysManager().getArcList());
                    animationController.reset();
                    animationController.handlePlay();
                }
        } }catch (Exception e) {
        }
    }

    /**
     * Moves the rays if the slit spacing has been modified to follow the slits.
     * @param topArcList List of the rays coming out of the top slit
     * @param bottomArcList List of the rays coming out of the bottom slit
     */
    private void updateArcs(List<Arc> topArcList, List<Arc> bottomArcList) {
        for (Arc a : topArcList) {
            a.setCenterY(this.animationController.getCircleTop().getCenterY());
        }
        for (Arc a : bottomArcList) {
            a.setCenterY(this.animationController.getCircleBottom().getCenterY());
        }
    }

    /**
     * Moves the two slits when the slit spacing parameter was modified with the
     * slider or text field.
     * @param spacing new distance between the two slits
     */
    public void updateCircles(double spacing, AnimationController animationController) {
        try {
            double relativeSpacing = spacing * 10;
            double center = this.animationController.
                    animationPane.getLayoutBounds().getCenterY();
            this.animationController.getCircleBottom().
                    setCenterY(center + relativeSpacing);
            this.animationController.getCircleTop().
                    setCenterY(center - relativeSpacing);
        } catch (NullPointerException e) {
        };
    }

    /**
     * Changes the position of the slider to match the value of a text field
     * that has been modified.
     * @param slider slider to update
     * @param newValue input entered by the user in the text field
     */
    private void updateSlider(Slider slider, String newValue) {
        try {
            slider.setValue(Double.parseDouble(newValue));
        } catch (NumberFormatException e) {
        }
    }

    /**
     * Sets initial random parameters.
     */
    private void defaultValues() {
        lastWavelength = generateRandom(380, 750);
        sliderWavelength.setValue(lastWavelength);
        sliderWidth.setValue(generateRandom(0, 10));
        sliderSpacing.setValue(generateRandom(0, 10));
        if (selectedView.equals("Graph")) {
            this.graphController.plotLine();
        }
    }

    /**
     * Generates a random number in a given range
     * @param min 
     * @param max
     * @return the generated number
     */
    private double generateRandom(double min, double max) {
        return Math.random() * (max - min + 1) + min;
    }
}
