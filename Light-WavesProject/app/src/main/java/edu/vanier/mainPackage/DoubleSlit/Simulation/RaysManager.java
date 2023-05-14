package edu.vanier.mainPackage.DoubleSlit.Simulation;

import edu.vanier.mainPackage.DoubleSlit.UI.AnimationController;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lombok.Getter;

/**
 *
 * @author Sabrina Amoura
 */
public class RaysManager {

    @Getter private Timeline animationLoop;
    @Getter private Timeline addRaysLoop;
    private final double FRAMERATE = 60;
    private AnimationController animationController;
    @Getter private List<Arc> arcList = new ArrayList<>();
    @Getter private List<Arc> topArcList = new ArrayList<>();
    @Getter private List<Arc> bottomArcList = new ArrayList<>();

    /**
     * Sole constructor.
     * @param animationController controller of the BorderPane where the animation takes place
     */
    public RaysManager(AnimationController animationController) {
        this.animationLoop = animationLoop();
        this.addRaysLoop = addRaysLoop();
        this.animationController = animationController;
    }

    /**
     * Creates an animation that makes all the rays move.
     * @return the Timeline created
     */
    private Timeline animationLoop() {
        EventHandler<ActionEvent> onFinished = (event) -> {
            update(arcList);
        };
        final KeyFrame kf = new KeyFrame
            (Duration.millis((1000 / (float) FRAMERATE)), onFinished);
        animationLoop = new Timeline(kf);
        animationLoop.setCycleCount(Animation.INDEFINITE);
        return animationLoop;
    }

    /**
     * Creates an animation that adds new rays according to the wavelength.
     * @return the Timeline created
     */
    private Timeline addRaysLoop() {
        EventHandler<ActionEvent> onFinished = (event) -> {
            checkWavelength();
        };
        final KeyFrame kf = new KeyFrame
            (Duration.millis((1000 / (float) FRAMERATE)), onFinished);
        addRaysLoop = new Timeline(kf);
        addRaysLoop.setCycleCount(Animation.INDEFINITE);
        return addRaysLoop;
    }

    /**
     * Creates the first ray, then the following are 
     * separated by the set wavelength.
     */
    public void checkWavelength() {
        if (topArcList.isEmpty()) {
            topArcList.add(arcConstructor
                (animationController.getCircleTop().getCenterY()));
            bottomArcList.add(arcConstructor
                (animationController.getCircleBottom().getCenterY()));
        } else if (topArcList.get(topArcList.size() - 1).getRadiusX() 
                >= Parameters.getWavelength() / 5) {
            topArcList.add(arcConstructor
                (animationController.getCircleTop().getCenterY()));
            bottomArcList.add(arcConstructor
                (animationController.getCircleBottom().getCenterY()));
        }
    }

    /**
     * Creates an arc and adds it to the list to animate.
     * @param posY the position of the slit is the center of the arc
     * @return the arc created
     */
    public Arc arcConstructor(double posY) {
        Arc arc = new Arc();
        arc.setCenterX(20);
        arc.setCenterY(posY);
        arc.setRadiusX(10);
        arc.setRadiusY(10);
        arc.setStartAngle(270);
        arc.setLength(180);
        arc.setType(ArcType.OPEN);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(colorPicker(Parameters.getWavelength()));
        
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind
            (animationController.getAnimationPane().widthProperty());
        clip.heightProperty().bind
            (animationController.getAnimationPane().heightProperty());
        arc.setClip(clip);
        animationController.getAnimationPane().getChildren().add(arc);
        arcList.add(arc);
        return arc;
    }

    /**
     * Determines the color od the rays based on the wavelength parameter.
     * @param wavelength parameter set by the user
     * @return the color associated with the set wavelength
     */
    public Color colorPicker(double wavelength) {
        Color color;
        if (380 <= wavelength && wavelength < 450) {
            color = Color.VIOLET;
        } else if (450 <= wavelength && wavelength < 495) {
            color = Color.BLUE;
        } else if (495 <= wavelength && wavelength < 570) {
            color = Color.GREEN;
        } else if (570 <= wavelength && wavelength < 590) {
            color = Color.YELLOW;
        } else if (590 <= wavelength && wavelength < 620) {
            color = Color.ORANGE;
        } else {
            color = Color.RED;
        }
        return color;
    }

    /**
     * Increases the raduis of the rays when the animation is playing.
     * @param arcList a list of all the arcs in the animationPane
     */
    public void update(List<Arc> arcList) {
        try {
            for (Arc a : arcList) {
                a.setRadiusX(a.getRadiusX() + 1);
                a.setRadiusY(a.getRadiusY() + 1);
                if (a.getRadiusX() > 2000) {
                    arcList.remove(a);
                }
            }
        } catch (ConcurrentModificationException e) {};
    }
}
