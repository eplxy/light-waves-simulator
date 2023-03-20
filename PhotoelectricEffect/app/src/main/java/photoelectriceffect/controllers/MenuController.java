/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package photoelectriceffect.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 *
 * @author maesh
 */
public class MenuController {
    //sliders
    @FXML
    Slider sliderIntensity = new Slider();
    @FXML
    Slider sliderWavelegth = new Slider();
    @FXML
    Slider sliderBatteryVoltage = new Slider();
    //buttons
    @FXML
    Button btnSurfaceMaterial = new Button();
    @FXML
    Button btnGoBackToMainMenu = new Button();
    @FXML
    Button btnPlay = new Button();
    @FXML
    Button btnForward = new Button();

    public MenuController(Stage stage) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
