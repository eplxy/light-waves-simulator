/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage;

import java.io.IOException;
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
    
    public MainMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
    }
        

   /***
    * Sets the event handling methods for the menu buttons
    * @throws IOException 
    */
    public void initialize() throws IOException{
        btnDoubleSlit.setOnAction((event) -> {
            handleDoubleSlit(event, this.primaryStage);
        });
        
    }   
    
   public void handleDoubleSlit(ActionEvent event, Stage primaryStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DoubleSlitMenu.fxml"));
            //edu.vanier.collisionsimulator.ui.CollisionMenuController menuController = new edu.vanier.collisionsimulator.ui.CollisionMenuController(primaryStage);
            //loader.setController(menuController);
            BorderPane root = loader.load();
            //menuController.initialize(sim);
            
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
