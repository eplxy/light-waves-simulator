/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage;

import edu.vanier.mainPackage.lens.Driver;
import edu.vanier.mainPackage.refraction.Refraction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author 2148289
 */
public class MainMenuController {
    
    Stage primaryStage; 
    
    @FXML
    Button btnRefraction;
    
    @FXML
    Button btnLens;
    
    @FXML
    Button btnDoubleslit;
    
    @FXML
    Button btnPhotoelectric;
    
    public MainMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void initialize(){
        
        btnDoubleslit.setOnAction((event) -> {
            
        });
        
        btnLens.setOnAction((event) -> {
            Driver lensMain = new Driver();
            try {
                lensMain.start(primaryStage);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        });
        
        btnRefraction.setOnAction((event) -> {
            Refraction refractionMain = new Refraction();
            
            try {
                refractionMain.start(primaryStage);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        });
        
        btnPhotoelectric.setOnAction((event) -> {
            
        });
        
    }
}
