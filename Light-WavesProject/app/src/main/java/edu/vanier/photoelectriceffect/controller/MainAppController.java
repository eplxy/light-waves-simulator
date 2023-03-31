package edu.vanier.photoelectriceffect.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/*
 * @author maesh
 */
public class MainAppController implements Initializable{
    Stage stage;
    public MainAppController(Stage stage){
        this.stage = stage;
    }

    public MainAppController() {}
    
    @FXML
    Slider sliderIntensity = new Slider();
    
    @FXML
    Slider sliderWavelegth = new Slider();
    
    @FXML
    Slider sliderBatteryVoltage = new Slider();
  
    @FXML
    Button btnSurfaceMaterial = new Button();
    
    @FXML
    Button btnGoBackToMainMenu = new Button();
    
    @FXML
    Button btnPlay = new Button();
    
    @FXML
    Button btnForward = new Button();
    
    @FXML
    public void initialize(){
        System.out.println("Initialization in progress...");
        btnPlay.setOnAction((event)-> {
            playSimulation(event);
        });
    }
    
    @FXML 
    public void playSimulation(ActionEvent event){
        System.out.println("Play button was pressed");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}