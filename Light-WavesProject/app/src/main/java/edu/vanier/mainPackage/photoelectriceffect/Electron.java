/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage.photoelectriceffect;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author maesh
 */
public class Electron {
    
    public Timeline circlesTimeline;
    private int randomNumber;
    private double duration;
    private static final double plankConstant = 6.62607015e-34;
    private double chargeElectron = 1.602e-19;
    
    public Electron(){}

    public Electron(Timeline circlesTimeline, int randomNumber, double duration) {
        this.circlesTimeline = circlesTimeline;
        this.randomNumber = randomNumber;
        this.duration = duration;
    }    
    
    /**
     * This is the main animation where the electron (or photoelectrons) eject
     * from the metal plate if the minimum energy of the photons are higher than
     * the work function of the metal
     * @param numCircles
     * @param pane 
     */
    public void generateCircles(int numCircles, Pane pane){
        Random random = new Random();
        for (int i = 0; i < numCircles; i++){
            Circle circle = new Circle(3);
            circle.setFill(Color.BLUE);
            circle.setLayoutX(230);
            randomNumber = random.nextInt(245 - 55 + 1) + 55;
            circle.setLayoutY(randomNumber);
            pane.getChildren().add(circle);
            
            duration = random.nextDouble() * 2 + 1;
            KeyValue keyValue = new KeyValue(circle.layoutXProperty(), 600);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(duration), keyValue);
            Timeline timeline = new Timeline(keyFrame);
            timeline.setOnFinished(event -> circle.setLayoutX(0));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            circlesTimeline = timeline;
            }
    }
    
    public double kineticEnergyElectron(double wavelength, double workFunction, double voltage){
        double kineticEnergyElectron = (plankConstant * wavelength) - workFunction - (chargeElectron * voltage);
        return kineticEnergyElectron;
    }

    public Timeline getCirclesTimeline() {
        return circlesTimeline;
    }

    public void setCirclesTimeline(Timeline circlesTimeline) {
        this.circlesTimeline = circlesTimeline;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    
    
    
}
