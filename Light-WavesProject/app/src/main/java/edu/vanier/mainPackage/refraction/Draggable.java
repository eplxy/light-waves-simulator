/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage.refraction;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

/**
 *
 * 
 */
public class Draggable {
    
    private double mouseAnchorX;
    private double mouseAnchorY;
    
    
    public void makeDraggable(Node node, Pane animationPane){
        
        Rotate rotate = new Rotate();
        
        node.setOnMousePressed((mouseEvent) -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
            
        });
        
        node.setOnMouseDragged((mouseEvent) -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
            
            rotate.pivotXProperty().bind(animationPane.widthProperty().divide(2));
            rotate.pivotYProperty().bind(animationPane.heightProperty().divide(2));
            node.getTransforms().setAll(rotate);
        });
        
    }
    
}
