package edu.vanier.mainPackage.photoelectriceffect;

import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/*
 * @author maesha
 */
public class PhotoelectricMenuController{
    //Variables
    /**
     * these are the variable that are used in the method situated in this class
     */
    private int numCircles;
    private boolean circlesGenerated = false;
    private double minimumEnergy;
    private boolean buttonWasPressed = false;
    private double wavelength;
    private String metal;
    
    //object
    Photon photon = new Photon();
    
    //JavaFX components
    @FXML
    private AnchorPane pane;
    @FXML
    Polygon light = new Polygon();
    @FXML
    private Slider sliderIntensity;
    @FXML
    private Slider sliderWavelength;
    @FXML
    private Slider sliderBatteryVoltage;
    @FXML
    private MenuButton metalMenuButton;
    @FXML
    private MenuItem magnesiumMenuItem;
    @FXML
    private MenuItem aluminiumMenuItem;
    @FXML
    private MenuItem calciumMenuItem;
    @FXML
    private MenuItem copperMenuItem;
    @FXML
    private MenuItem goldMenuItem;
    @FXML
    private HashMap<String, Double> metalWorkFunctions;
    @FXML
    Button btnPlay = new Button();
    @FXML 
    Button btnHelp = new Button();
    @FXML 
    private Label intensityLabel;
    @FXML 
    private Label wavelengthLabel;
    @FXML 
    private Label batteryVoltageLabel;
    @FXML
    private Label workFunctionLabel;
    @FXML
    private Label minimumEnergyLabel;
    @FXML
    private Label displayBatteryVoltageLabel;
    
    Electron electron = new Electron();
    
    //initialize
    public void initialize(){
        /**
         * This is to initialize the metalWorkFunction Hashmap with a metal with
         * its corresponding work function
         */
        metalWorkFunctions = new HashMap<>();
        metalWorkFunctions.put("Magnesium", 3.7);
        metalWorkFunctions.put("Aluminium", 4.28);
        metalWorkFunctions.put("Calcium", 3.0);
        metalWorkFunctions.put("Copper", 5.0);
        metalWorkFunctions.put("Gold", 5.1);
        /**
         * what this does is when the help button is clicked, a window opens up
         * explaining how to use this simulation. 
         */
        btnHelp.setOnAction((ActionEvent event) -> {
            Stage helpStage = new Stage();
            helpStage.setTitle("PHOTOELECTRIC EFFECT");
            
            TextFlow helpTextFlow = new TextFlow();
            Text helpText = new Text("This is a photoelectric effect simulation.\n"
                    + "To insure the proper functionning of the program, make \n"
                    + "sure to input the initial intensity, wavelength, and \n"
                    + "metal, to get the energy of the photon. It must be a \n"
                    + "positive number or else it will not eject any electrons.\n"
                    + "When a positive minimum energy has been acheived, \n"
                    + "you can click the play button to action the animation\n"
            );
            helpTextFlow.getChildren().add(helpText);

            VBox helpLayout = new VBox();
            helpLayout.getChildren().add(helpTextFlow);
            helpLayout.getChildren().add(helpText);
            Scene helpScene = new Scene(helpLayout, 300, 200);
            helpStage.setScene(helpScene);
            helpStage.show();
        });
        
        /**
         * this generates the animation code when the play button is clicked.
         * it will only run if the minimum energy is higher than 0
         */
        
        btnPlay.setOnAction(e ->{
            if(minimumEnergy > 0){
                
                if (circlesGenerated) {
                    pane.getChildren().removeIf(node -> node instanceof Circle);
                }
                electron.generateCircles(numCircles, pane);
                circlesGenerated = true;
                buttonWasPressed = true;
            }  
                
        });
        /**
         * this is to update the intensity slider
         */
        sliderIntensity.valueProperty().addListener((observable, oldValue, newValue) -> {
            /**
             * this updates the intensity label every time the user changes 
             * something
             */
            intensityLabel.setText((String.format("%.2f", newValue)) + " %");
           
            /**
             * similarly, this updates the intensity of the animation according 
             * to the percentage of intensity
             */
            int intensity = newValue.intValue();
            numCircles = (intensity / 2);
            
            
            
            if(minimumEnergy > 0){
                if (buttonWasPressed == true) {
                    if (circlesGenerated) {
                        
                        pane.getChildren().removeIf(node -> node instanceof Circle);
                    }
                    electron.generateCircles(numCircles, pane);
                    circlesGenerated = true;
                }
            }
            
        });
        
        sliderWavelength.valueProperty().addListener((observable, oldValue, newValue) -> {
            wavelengthLabel.setText((String.format("%.2f", newValue)) + " nm");
            wavelength = newValue.doubleValue();
            Color colour = photon.getColorForWavelength(wavelength);
            
            Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(1, colour)};
            LinearGradient lg1 = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops);
            light.setFill(lg1);
            
            if(buttonWasPressed == true){
            minimumEnergy = photon.photonMinuimumEnergy(wavelength, metal);
            minimumEnergyLabel.setText(String.format("%.2f J", minimumEnergy));
            }
            
            if(minimumEnergy > 0 && buttonWasPressed == true){
                if (circlesGenerated) {
                    pane.getChildren().removeIf(node -> node instanceof Circle);
                }
                electron.generateCircles(numCircles, pane);
                circlesGenerated = true;
            }
        }
        
        );
        
        sliderBatteryVoltage.valueProperty().addListener((observable, oldValue, newValue) -> {
            batteryVoltageLabel.setText((String.format("%.2f", newValue)) + " V");
            displayBatteryVoltageLabel.setText((String.format("%.2f", newValue)) + " V");
        });
        
        magnesiumMenuItem.setOnAction(event -> {
            setWorkFunction("Magnesium", wavelength);
            metal = "Magnesium";
        });
        
        aluminiumMenuItem.setOnAction(event -> {
            setWorkFunction("Aluminium", wavelength);
            metal = "Aluminium";
        });
        
        calciumMenuItem.setOnAction(event -> {
                setWorkFunction("Calcium", wavelength);
                metal = "Calcium";
        });
        
        copperMenuItem.setOnAction(event -> {
            setWorkFunction("Copper", wavelength);
            metal = "Copper";
        });
        
        goldMenuItem.setOnAction(event -> {
            setWorkFunction("Gold", wavelength);
            metal = "Copper";
        });
    }
    
    private void setWorkFunction(String metal, double wavelength) {
        double workFunction = metalWorkFunctions.get(metal);
        workFunctionLabel.setText(String.format("%.2f eV", workFunction));
        minimumEnergy = photon.photonMinuimumEnergy(wavelength, metal);
        minimumEnergyLabel.setText(String.format("%.2f J", minimumEnergy));
    }
}