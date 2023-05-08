package edu.vanier.mainPackage.refraction;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author Matthew Hantar
 */
@lombok.Data
public abstract class LineBuild implements LineAndRay{
    
    protected Line incidentRay;
    protected Line refractedRay;
    protected Line normalRay;
    protected Line horizontalRay;
    protected Line totalRefractedRay;
    protected Arc arcIncidentRay;
    protected Arc arcRefractedRay;
    protected Label labelTotalInternalReflection;
    protected String textAngleIncident;
    protected String textAngleRefracted;
    protected Label labelAngleIncident;
    protected Label labelAngleRefracted;
    
    protected Pane animationPane;
    protected MenuController menuController;
    protected Stage primaryStage;

    protected double angle1 = 45;
    protected double angle2 = 45;
    
    @Override
    public void CreateLines(Stage primaryStage, Pane animationPane) {

        this.animationPane = animationPane;

        incidentRay = new Line();
        refractedRay = new Line();
        normalRay = new Line();
        horizontalRay = new Line();
        totalRefractedRay = new Line();
        arcIncidentRay = new Arc();
        arcRefractedRay = new Arc();

        labelTotalInternalReflection = new Label("Total internal reflection");
        labelAngleIncident = new Label("Incident angle: " + String.format("%.2f", angle1) + "°");
        labelAngleRefracted = new Label("Refracted angle: " + String.format("%.2f", angle2) + "°");

        incidentObj(incidentRay, arcIncidentRay);
        refractedObj(refractedRay, arcIncidentRay);
        totalInternalRefractionObj(totalRefractedRay);

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

        Transition transition = new Transition();
        transition.CreatingLinesTransition(incidentRay, refractedRay, arcIncidentRay, arcRefractedRay, animationPane);
        
        animationPane.getChildren().addAll(incidentRay, refractedRay, normalRay, horizontalRay, totalRefractedRay, arcIncidentRay, arcRefractedRay,
                labelTotalInternalReflection, labelAngleIncident, labelAngleRefracted);
    }
    
    @Override
    public void incidentObj(Line incidentRay, Arc arcIncidentRay){
        
        incidentRay.startXProperty().setValue(0);
        incidentRay.startYProperty().bind(animationPane.heightProperty().divide(2));
        incidentRay.endXProperty().bind(animationPane.widthProperty().divide(2));
        incidentRay.endYProperty().bind(animationPane.heightProperty().divide(2));
        incidentRay.setStrokeWidth(3);
        
        Rotate rotate = new Rotate();
        rotate.pivotXProperty().bind(incidentRay.endXProperty());
        rotate.pivotYProperty().bind(incidentRay.endYProperty());
        rotate.setAngle(angle1);
        incidentRay.getTransforms().addAll(rotate);

        //arc incident ray
        arcIncidentRay.centerXProperty().bind(animationPane.widthProperty().divide(2));
        arcIncidentRay.centerYProperty().bind(animationPane.heightProperty().divide(2));
        arcIncidentRay.setRadiusX(50);
        arcIncidentRay.setRadiusY(50);
        arcIncidentRay.setStartAngle(180);
        arcIncidentRay.setLength(-45);
        arcIncidentRay.setOpacity(0.7);
        arcIncidentRay.setType(ArcType.ROUND);

        labelAngleIncident.setLayoutX(15);
        labelAngleIncident.layoutYProperty().bind(animationPane.heightProperty().divide(2).subtract(35));
        labelAngleIncident.setFont(new Font("Lexend", 15));
        
    }
    
    @Override
    public void refractedObj(Line refractedray, Arc arcIncidentRay){
        
        refractedRay.startXProperty().bind(animationPane.widthProperty().divide(2));
        refractedRay.startYProperty().bind(animationPane.heightProperty().divide(2));
        refractedRay.endXProperty().bind(animationPane.widthProperty());
        refractedRay.endYProperty().bind(animationPane.heightProperty().divide(2));
        refractedRay.setStrokeWidth(3);
        
        Rotate rotate2 = new Rotate();
        rotate2.pivotXProperty().bind(incidentRay.endXProperty());
        rotate2.pivotYProperty().bind(incidentRay.endYProperty());
        rotate2.setAngle(90 - angle2);
        refractedRay.getTransforms().addAll(rotate2);

        //arc refracted ray
        arcRefractedRay.centerXProperty().bind(animationPane.widthProperty().divide(2));
        arcRefractedRay.centerYProperty().bind(animationPane.heightProperty().divide(2));
        arcRefractedRay.setRadiusX(50);
        arcRefractedRay.setRadiusY(50);
        arcRefractedRay.setStartAngle(0);
        arcRefractedRay.setLength(-45);
        arcRefractedRay.setOpacity(0.7);
        arcRefractedRay.setType(ArcType.ROUND);

        labelAngleRefracted.setLayoutX(15);
        labelAngleRefracted.layoutYProperty().bind(animationPane.heightProperty().divide(2).add(10));
        labelAngleRefracted.setFont(new Font("Lexend", 15));
        
    }
    
    @Override
    public void totalInternalRefractionObj(Line totalRefractedRay){
        totalRefractedRay.startXProperty().bind(animationPane.widthProperty().divide(2));
        totalRefractedRay.startYProperty().bind(animationPane.heightProperty().divide(2));
        totalRefractedRay.endXProperty().bind(animationPane.widthProperty());
        totalRefractedRay.endYProperty().bind(animationPane.heightProperty().divide(2));
        totalRefractedRay.setVisible(false);
        totalRefractedRay.setStrokeWidth(3);
        
        //label total internal reflection
        labelTotalInternalReflection.layoutXProperty().bind(animationPane.widthProperty().divide(2));
        labelTotalInternalReflection.setLayoutY(10);
        labelTotalInternalReflection.setFont(new Font("Lexend", 26));
        labelTotalInternalReflection.setVisible(false);
    }
    
}
