package edu.vanier.mainPackage.refraction;

import javafx.animation.ScaleTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author Matthew Hantar
 */
public class Transition {
    
    /**
    Creates a transition animation for the incident and refracted rays and their corresponding arcs.
    @param incidentRay the incident ray Line object to animate
    @param refractedRay the refracted ray Line object to animate
    @param arcIncidentRay the incident ray Arc object to animate
    @param arcRefractedRay the refracted ray Arc object to animate
    @param animationPane the Pane object where the animation will be played
    */
    public void CreatingLinesTransition(Line incidentRay, Line refractedRay, Arc arcIncidentRay, Arc arcRefractedRay, Pane animationPane){
        ScaleTransition st = new ScaleTransition(Duration.millis(1500), incidentRay);
        st.toXProperty().multiply(0);
        st.toYProperty().bind(new SimpleIntegerProperty(0).asObject());
        st.setCycleCount(2);
        st.jumpTo(Duration.millis(1500));
        st.setAutoReverse(true);
        st.play();
        
        ScaleTransition st2 = new ScaleTransition(Duration.millis(1500), refractedRay);
        st2.toXProperty().multiply(0);
        st2.toYProperty().bind(animationPane.heightProperty());
        st2.setCycleCount(2);
        st2.jumpTo(Duration.millis(1500));
        st2.setAutoReverse(true);
        st2.play();
        
        ScaleTransition st3 = new ScaleTransition(Duration.millis(1500), arcIncidentRay);
        st3.toXProperty().bind(new SimpleIntegerProperty(0).asObject());
        st3.toYProperty().bind(new SimpleIntegerProperty(0).asObject());
        st3.setCycleCount(2);
        st3.jumpTo(Duration.millis(1500));
        st3.setAutoReverse(true);
        st3.play();
        
        ScaleTransition st4 = new ScaleTransition(Duration.millis(1500), arcRefractedRay);
        st4.toXProperty().bind(new SimpleIntegerProperty(0).asObject());
        st4.toYProperty().bind(new SimpleIntegerProperty(0).asObject());
        st4.setCycleCount(2);
        st4.jumpTo(Duration.millis(1500));
        st4.setAutoReverse(true);
        st4.play();
    }
    
}
