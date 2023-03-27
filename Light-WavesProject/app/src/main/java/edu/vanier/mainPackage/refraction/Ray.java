/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage.refraction;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author 2148289
 */
public class Ray {
    
    Pane animationPane;
    MenuController menuController;
    private Stage primaryStage;
    
    Line incidentRay;
    Line refractedRay;
    Line normalRay;
    Line horizontalRay;
    double angle1 = 45;
    
    public Ray() {
        this.menuController = new MenuController(primaryStage);
    }
    
    public void CreateLines(Stage primaryStage, Pane animationPane){
        
        this.animationPane = animationPane;
        
        incidentRay = new Line();
        refractedRay = new Line();
        normalRay = new Line();
        horizontalRay = new Line();
        
        double angle2 = Vector.CalculateAngle(Vector.NAIR, Vector.NWATER, angle1);
        System.out.println(angle1);
        System.out.println(angle2);
        
        //Incident Ray
        incidentRay.startXProperty().setValue(0);
        incidentRay.startYProperty().bind(animationPane.heightProperty().divide(2));
        incidentRay.endXProperty().bind(animationPane.widthProperty().divide(2));
        incidentRay.endYProperty().bind(animationPane.heightProperty().divide(2));
        
        Rotate rotate = new Rotate();
        rotate.pivotXProperty().bind(incidentRay.endXProperty());
        rotate.pivotYProperty().bind(incidentRay.endYProperty());
        rotate.setAngle(angle1);
        incidentRay.getTransforms().addAll(rotate);
        
        //Refracted Ray
        refractedRay.startXProperty().bind(animationPane.widthProperty().divide(2));
        refractedRay.startYProperty().bind(animationPane.heightProperty().divide(2));
        refractedRay.endXProperty().bind(animationPane.widthProperty());
        refractedRay.endYProperty().bind(animationPane.heightProperty().divide(2));
        
        Rotate rotate2 = new Rotate();
        rotate2.pivotXProperty().bind(incidentRay.endXProperty());
        rotate2.pivotYProperty().bind(incidentRay.endYProperty());
        rotate2.setAngle(90 - angle2);
        refractedRay.getTransforms().addAll(rotate2);
        
        //Horizontal Ray
        horizontalRay.startXProperty().setValue(0);
        horizontalRay.startYProperty().bind(animationPane.heightProperty().divide(2));
        horizontalRay.endXProperty().bind(animationPane.widthProperty());
        horizontalRay.endYProperty().bind(animationPane.heightProperty().divide(2));
        horizontalRay.getStrokeDashArray().addAll(3d);
        
        //Normal Ray
        normalRay.startXProperty().bind(animationPane.widthProperty().divide(2));
        normalRay.startYProperty().setValue(-20);
        normalRay.endXProperty().bind(animationPane.widthProperty().divide(2));
        normalRay.endYProperty().bind(animationPane.heightProperty().add(10));
        normalRay.getStrokeDashArray().addAll(10d);
        
        animationPane.getChildren().addAll(incidentRay, refractedRay, normalRay, horizontalRay);
    }
    
    public void updateLines(double angle){
        
        Rotate newRotate = new Rotate();
        newRotate.pivotXProperty().bind(incidentRay.endXProperty());
        newRotate.pivotYProperty().bind(incidentRay.endYProperty());
        incidentRay.getTransforms().setAll(newRotate);
        newRotate.setAngle(angle);
        System.out.println(angle);
        
        double angle2 = Vector.CalculateAngle(Vector.NAIR, Vector.NWATER, angle);
        
        Rotate newRotate2 = new Rotate();
        newRotate2.pivotXProperty().bind(incidentRay.endXProperty());
        newRotate2.pivotYProperty().bind(incidentRay.endYProperty());
        refractedRay.getTransforms().setAll(newRotate2); 
        newRotate2.setAngle(90 - angle2);
        System.out.println("Angle 2: " + angle2);
        
    }
}
