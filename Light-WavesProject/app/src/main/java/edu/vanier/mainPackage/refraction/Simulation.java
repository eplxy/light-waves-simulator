
package edu.vanier.mainPackage.refraction;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *@author Matthew
 * 
 */
public class Simulation {
    
    private final double frameRate = 60;
    public Timeline loop;
    public Pane animationPane;
    
    
    public Simulation(){
        loop = setLoop();
    }
    
    private Timeline setLoop() {
        
        EventHandler<ActionEvent> onFinished = (event) -> {
            
        };

        final KeyFrame kf = new KeyFrame(Duration.millis((1000 / (float) this.frameRate)), onFinished);
        loop = new Timeline(kf);
        loop.setCycleCount(Animation.INDEFINITE);
        return loop;
    }
    
    public Pane getAnimationPane() {
        return animationPane;
    }

    public void setAnimationPane(Pane animationPane) {
        this.animationPane = animationPane;
    }
    
}
