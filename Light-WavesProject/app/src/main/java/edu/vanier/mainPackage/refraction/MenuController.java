/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage.refraction;

import javafx.beans.value.ObservableNumberValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author 2148289
 */
public class MenuController {
    
    @FXML
    Button btnMainMenu;
    @FXML
    Button btnLight;
    @FXML
    Button btnAngles;
    @FXML
    Slider sliderColor;
    @FXML
    ChoiceBox btnMaterial1;
    @FXML
    ChoiceBox btnMaterial2;
    @FXML
    Pane animationPane;
    @FXML
    Rectangle rectangle;
    @FXML
    TextField textAngle;
    @FXML
    TextField textN1;
    @FXML
    TextField textN2;
    
    Stage primaryStage;
    Simulation sim;

    Draggable draggableMaker = new Draggable();
    
    private String n1;
    private String n2;
    private String angle1;
    
    MenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        
        
    }
    
    public void initialize(){
        
        draggableMaker.makeDraggable(rectangle, animationPane);
        
        textAngle.setOnAction(e -> {
            angle1 = textAngle.getText();
            System.out.println(angle1);
         });
        
        textN1.setOnAction(e -> {
            n1 = textN1.getText();
            System.out.println(n1);
         });
        
        textN2.setOnAction(e -> {
            n2 = textN2.getText();
            System.out.println(n2);
         });
    } 
    
    public void createLines(Stage primaryStage){
        
        Line incidentRay = new Line();
        incidentRay.setStartX(0);
        incidentRay.setStartY(0);
        incidentRay.setStartX(100);
        incidentRay.setStartY(200);
        
        
    }
    
}
