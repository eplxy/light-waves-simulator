/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoubleSlit.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author sabri
 */
public class RaysManager {
     private Timeline animationLoop;
     private Timeline addRaysLoop;
     private double frameRate = 60;
     private AnimationController animationController;
    
    public RaysManager(AnimationController animationController ) {
        this.animationLoop = animationLoop();
        this.addRaysLoop = addRaysLoop();
        this.animationController = animationController;
        
    }
    
     private List<Arc> arcList = new ArrayList<>();
     private List<Arc> topArcList = new ArrayList<>();
     private List<Arc> bottomArcList = new ArrayList<>();
    

    
    /***
     * Instantiates the simulation loop. All updating events 
     * @return 
     */ 
    private Timeline animationLoop() {

        EventHandler<ActionEvent> onFinished = (event) -> {
            update(arcList);            
        };
        
        final KeyFrame kf = new KeyFrame(Duration.millis((1000 / (float) this.frameRate)), onFinished);
        animationLoop = new Timeline(kf);
        animationLoop.setCycleCount(Animation.INDEFINITE);
        return animationLoop;
    }
    
    private Timeline addRaysLoop(){
        EventHandler<ActionEvent> onFinished = (event) -> {
            topArcList.add(arcConstructor(animationController.getCircleTop().getCenterY())); 
            bottomArcList.add(arcConstructor(animationController.getCircleBottom().getCenterY()));
        };
        final KeyFrame kf = new KeyFrame(Duration.millis((100000 / (float) this.frameRate)), onFinished);
        addRaysLoop = new Timeline(kf);
        addRaysLoop.setCycleCount(Animation.INDEFINITE);
        return addRaysLoop;
    }
    
     public Arc arcConstructor(double posY) {
        Arc arc = new Arc();

        //Setting the properties of the arc 
        arc.setCenterX(20);
        arc.setCenterY(posY);
        arc.setRadiusX(10);
        arc.setRadiusY(10);
        arc.setStartAngle(270);
        arc.setLength(180);
        
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(animationController.animationPane.widthProperty());
        clip.heightProperty().bind(animationController.animationPane.heightProperty());

        arc.setClip(clip);

        //Setting the type of the arc 
        arc.setType(ArcType.OPEN);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.BLACK);
        animationController.animationPane.getChildren().add(arc);
        arcList.add(arc);
        return arc;

    }

    public void update(List<Arc> arcList) {
        for( Arc a:arcList){
            a.setRadiusX(a.getRadiusX()+1);
            a.setRadiusY(a.getRadiusY()+1);
            try{
            if(a.getRadiusX()>2000){
                arcList.remove(a);
            }}catch(ConcurrentModificationException e){};
        }
    }
    
    public void reset(){
        addRaysLoop().stop();
        animationLoop().stop();
        animationController.animationPane.getChildren().removeAll(arcList);
        arcList.clear();
    }

    public Timeline getAnimationLoop() {
        return animationLoop;
    }
    
    public Timeline getAddRaysLoop() {
        return addRaysLoop;
    }


    public double getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(double frameRate) {
        this.frameRate = frameRate;
    }

    public List<Arc> getArcList() {
        return arcList;
    }

    public void setArcList(List<Arc> arcList) {
        this.arcList = arcList;
    }

    public List<Arc> getTopArcList() {
        return topArcList;
    }

    public void setTopArcList(List<Arc> topArcList) {
        this.topArcList = topArcList;
    }

    public List<Arc> getBottomArcList() {
        return bottomArcList;
    }

    public void setBottomArcList(List<Arc> bottomArcList) {
        this.bottomArcList = bottomArcList;
    }
    
    
}
