/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage;

import edu.vanier.mainPackage.DoubleSlit.UI.DoubleSlitMenuController;
import edu.vanier.mainPackage.lens.Driver;
import edu.vanier.mainPackage.refraction.Refraction;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author 2148289
 */
public class MainMenuController {
    
    Stage primaryStage; 
    @FXML
    Button btnDoubleSlit;
    
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
        
        btnDoubleslit.setOnAction((event) -> {
            handleDoubleSlit(event, primaryStage);
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
            //Must change name of photo electric effect main menu class because confusing naming convention
            //MainApp -> Photoelectric
            MainApp photoelectricMain = new MainApp();
            try{
                photoelectricMain.start(primaryStage);
            }catch (Exception ex){
                System.err.println(ex.toString());
            }
            
        });
        
    }
        

    public void initialize() throws IOException{
        btnDoubleSlit.setOnAction((event) -> {
            handleDoubleSlit(event, this.primaryStage);
        });
        
    }   
    
    public void handleDoubleSlit(ActionEvent event, Stage primaryStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DoubleSlitMenu.fxml"));
            DoubleSlitMenuController doubleSlitMenuController = new DoubleSlitMenuController(primaryStage);
            loader.setController(doubleSlitMenuController);
            BorderPane root = loader.load();
            
            primaryStage.close();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show(); 
          
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    
}
