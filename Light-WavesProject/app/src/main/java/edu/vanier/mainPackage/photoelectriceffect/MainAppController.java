package edu.vanier.mainPackage.photoelectriceffect;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Slider;
import javafx.scene.shape.Circle;

/*
 * @author maesh
 */
public class MainAppController{
    
    //sliders
    @FXML
    private Slider sliderIntensity;
    
    @FXML
    private Slider sliderWavelength;
    
    @FXML
    private Slider sliderBatteryVoltage;
    
    @FXML
    public void handleSliderIntensity(){
        double intensity = sliderIntensity.getValue();
    }
    
    @FXML
    public void handleSliderWavelength(){
        double wavelength = sliderWavelength.getValue();
    }
    
    @FXML
    public void handleSliderBatteryVoltage(){
        double batteryVoltage = sliderBatteryVoltage.getValue();
    }
    
    //buttons
    @FXML
    private MenuButton metalMenuButton;
    
    @FXML
    Button btnGoBackToMainMenu = new Button();
    
    @FXML
    Button btnPlay = new Button();
    
    @FXML
    Button btnForward = new Button();
    
    public void initialize(){
        sliderIntensity.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Intensity slider value: " + newValue.intValue() + "%");
        });
        
        sliderWavelength.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Wavelength slider value: " + newValue.intValue() + " nm");
        });
        
        sliderBatteryVoltage.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Battery Voltage slider value: " + newValue.intValue() + " V");
        });
        
        sliderIntensity.valueProperty().addListener((observable, oldValue, newValue) -> {
            intensityLabel.setText((String.format("%.2f", newValue)) + " %");
        });
        
        sliderWavelength.valueProperty().addListener((observable, oldValue, newValue) -> {
            wavelengthLabel.setText((String.format("%.2f", newValue)) + " nm");
        });
        
        sliderBatteryVoltage.valueProperty().addListener((observable, oldValue, newValue) -> {
            batteryVoltageLabel.setText((String.format("%.2f", newValue)) + " V");
        });
    }
    
    //Label
    @FXML 
    private Label intensityLabel;
            
    @FXML 
    private Label wavelengthLabel;
            
    @FXML 
    private Label batteryVoltageLabel;
}
