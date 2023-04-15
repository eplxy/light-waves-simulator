package edu.vanier.mainPackage.photoelectriceffect;

import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;


/*
 * @author maesha
 */
public class PhotoelectricMenuController{
    
    //variables
    private double minimumEnergy;
    
    //sliders
    @FXML
    private Slider sliderIntensity;
    
    @FXML
    private Slider sliderWavelength;
    
    @FXML
    private Slider sliderBatteryVoltage;
    
    //menu buttons
    @FXML
    private MenuButton metalMenuButton;
    
    //menu items
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
    
    private HashMap<String, Double> metalWorkFunctions;
    
    //buttons
    @FXML
    Button btnGoBackToMainMenu = new Button();
    
    @FXML
    Button btnPlay = new Button();
    
    @FXML
    Button btnForward = new Button();
    
    //Label
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
    
    public void initialize(){
        
        sliderIntensity.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Intensity slider value: " + newValue.intValue() + " %");
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
        
        metalWorkFunctions = new HashMap<>();
        metalWorkFunctions.put("Magnesium", 3.7);
        metalWorkFunctions.put("Aluminium", 4.28);
        metalWorkFunctions.put("Calcium", 3.0);
        metalWorkFunctions.put("Copper", 5.0);
        metalWorkFunctions.put("Gold", 5.1);

        magnesiumMenuItem.setOnAction(event -> setWorkFunction("Magnesium"));
        aluminiumMenuItem.setOnAction(event -> setWorkFunction("Aluminium"));
        calciumMenuItem.setOnAction(event -> setWorkFunction("Calcium"));
        copperMenuItem.setOnAction(event -> setWorkFunction("Copper"));
        goldMenuItem.setOnAction(event -> setWorkFunction("Gold"));
    
    }
    
    private void setWorkFunction(String metal) {
        double workFunction = metalWorkFunctions.get(metal);
        workFunctionLabel.setText(String.format("%.2f eV", workFunction));
        System.out.println("the Work Function value: " + workFunction + " eV");
        
        Photon photon = new Photon();
        System.out.println("wavelength: " + sliderWavelength.getValue() + " and metal is: " + metal);
        minimumEnergy = photon.photonMinuimumEnergy(sliderWavelength.getValue(), metal);
        minimumEnergyLabel.setText(String.format("%.2f J", minimumEnergy));

        System.out.println("Minimum Energy of Photon: " + minimumEnergy);    }
    
    @FXML
    public void calculateMinimumEnergy(){
        Photon photon = new Photon();
        minimumEnergy = photon.photonMinuimumEnergy(sliderWavelength.getValue(), metalMenuButton.getText());
        System.out.println(minimumEnergy);
    }
    
    @FXML
    public double getMinimumEnergy(){
        return minimumEnergy;
    }
}
