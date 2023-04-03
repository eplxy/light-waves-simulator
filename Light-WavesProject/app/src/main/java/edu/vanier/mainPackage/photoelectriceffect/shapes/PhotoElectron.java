/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage.photoelectriceffect.shapes;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author maesh
 */
public class PhotoElectron {

    private Circle circle;

    public PhotoElectron (double x, double y, double radius) {
        circle = new Circle(x, y, radius);
        circle.setFill(Color.RED);

        //translate transition for the circle
        TranslateTransition translate = new TranslateTransition(Duration.seconds(3), circle);
        translate.setByX(200); 
        translate.setAutoReverse(false); 

        // start the animation
        translate.play();
    }
    
    

    public Circle getCircle() {
        return circle;
    }
}