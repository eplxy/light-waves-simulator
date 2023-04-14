package edu.vanier.mainPackage.refraction;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author 2148289
 */
@Getter
@Setter
public class Ray {
    
    Pane animationPane;
    MenuController menuController;
    private Stage primaryStage;
    
    double angle1 =45 ;
    double angle2 = 45;
    
    private Line incidentRay;
    private Line refractedRay;
    private Line normalRay;
    private Line horizontalRay;
    private Line totalRefractedRay;
    private Arc arcIncidentRay;
    private Arc arcRefractedRay;
    private Label labelTotalInternalReflection;
    private String textAngleIncident;
    private String textAngleRefracted;
    private Label labelAngleIncident;
    private Label labelAngleRefracted; 
    
    
    
    
    public Ray() {
        this.menuController = new MenuController(primaryStage);
    }
    
    public void CreateLines(Stage primaryStage, Pane animationPane){
        
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
        
        angle2 = Vector.CalculateAngle(Vector.NAIR, Vector.NWATER, angle1);
        textAngleIncident = "LOL"; 
        textAngleRefracted = Double.toString(angle2);
        
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
        
        //arc incident ray
        arcIncidentRay.centerXProperty().bind(animationPane.widthProperty().divide(2));
        arcIncidentRay.centerYProperty().bind(animationPane.heightProperty().divide(2));
        arcIncidentRay.setRadiusX(50); 
        arcIncidentRay.setRadiusY(50); 
        arcIncidentRay.setStartAngle(180); 
        arcIncidentRay.setLength(-45); 
        arcIncidentRay.setType(ArcType.ROUND);
        
        labelAngleIncident.setLayoutX(15);
        labelAngleIncident.layoutYProperty().bind(animationPane.heightProperty().divide(2).subtract(35));
        labelAngleIncident.setFont(new Font("Lexend", 15));
        
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
        
        //arc refracted ray
        arcRefractedRay.centerXProperty().bind(animationPane.widthProperty().divide(2));
        arcRefractedRay.centerYProperty().bind(animationPane.heightProperty().divide(2));
        arcRefractedRay.setRadiusX(50); 
        arcRefractedRay.setRadiusY(50); 
        arcRefractedRay.setStartAngle(0); 
        arcRefractedRay.setLength(-60); 
        arcRefractedRay.setType(ArcType.ROUND);
        
        labelAngleRefracted.setLayoutX(15);
        labelAngleRefracted.layoutYProperty().bind(animationPane.heightProperty().divide(2).add(10));
        labelAngleRefracted.setFont(new Font("Lexend", 15));
        
        
        //Total internal Reflection
        totalRefractedRay.startXProperty().bind(animationPane.widthProperty().divide(2));
        totalRefractedRay.startYProperty().bind(animationPane.heightProperty().divide(2));
        totalRefractedRay.endXProperty().bind(animationPane.widthProperty());
        totalRefractedRay.endYProperty().bind(animationPane.heightProperty().divide(2));
        totalRefractedRay.setVisible(false);
        
        //label total internal reflection
        labelTotalInternalReflection.layoutXProperty().bind(animationPane.widthProperty().divide(2));
        labelTotalInternalReflection.setLayoutY(10);
        labelTotalInternalReflection.setFont(new Font("Lexend", 26));
        labelTotalInternalReflection.setVisible(false);
        
        
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
        
        animationPane.getChildren().addAll(incidentRay, refractedRay, normalRay, horizontalRay, totalRefractedRay, arcIncidentRay, arcRefractedRay,
        labelTotalInternalReflection, labelAngleIncident, labelAngleRefracted);
    }
    
    public void updateLines(double angle, double index1, double index2){
        
        Rotate newRotate = new Rotate();
        newRotate.pivotXProperty().bind(incidentRay.endXProperty());
        newRotate.pivotYProperty().bind(incidentRay.endYProperty());
        incidentRay.getTransforms().setAll(newRotate);
        newRotate.setAngle(angle);
        System.out.println(angle);
        arcIncidentRay.setLength(-angle);
        
        angle2 = Vector.CalculateAngle(index1, index2, angle);
        labelAngleIncident.setText("Incident angle: " + String.format("%.2f", angle)+ "°");
        
        //This happens if it is a total internal reflection
        if (Double.isNaN(angle2)) {
            rotateAnglesInternalReflection(angle);
        }
        //This happens if it is not a total internal reflection
        if (!Double.isNaN(angle2)) {
            rotateAnglesNormal(angle2);
        }
    }
    
    public void materialUpdateLines1(int index, int index2, Pane materialPane1, ArrayList<Material> list, String angle1){
        BackgroundFill backgroundFill =
        new BackgroundFill(list.get(index).getMaterialColor(),new CornerRadii(10),new Insets(10));

                Background background =
                    new Background(backgroundFill);

                materialPane1.setBackground(background);
                
        angle2 = Vector.CalculateAngle(list.get(index).getRefractionIndex(), list.get(index2).getRefractionIndex(), Double.parseDouble(angle1));
        
        if (Double.isNaN(angle2)) {
            rotateAnglesInternalReflection(Double.parseDouble(angle1));
        }
        //This happens if it is not a total internal reflection
        if (!Double.isNaN(angle2)) {
            rotateAnglesNormal(angle2);
        }
    }
    
     public void materialUpdateLines2(int index, int index2, Pane materialPane1, ArrayList<Material> list, String angle1){
        
        BackgroundFill backgroundFill =
        new BackgroundFill(list.get(index2).getMaterialColor(),new CornerRadii(10),new Insets(10));

                Background background =
                    new Background(backgroundFill);

                materialPane1.setBackground(background);
                
        angle2 = Vector.CalculateAngle(list.get(index).getRefractionIndex(), list.get(index2).getRefractionIndex(), Double.parseDouble(angle1));
        
        if (Double.isNaN(angle2)) {
            rotateAnglesInternalReflection(Double.parseDouble(angle1));
        }
        //This happens if it is not a total internal reflection
        if (!Double.isNaN(angle2)) {
            rotateAnglesNormal(angle2);
        }
    }
    
    public void rotateAnglesNormal(double angle2){
            totalRefractedRay.setVisible(false);
            refractedRay.setVisible(true);
            
            labelTotalInternalReflection.setVisible(false);
            
            Rotate newRotate2 = new Rotate();
            newRotate2.pivotXProperty().bind(incidentRay.endXProperty());
            newRotate2.pivotYProperty().bind(incidentRay.endYProperty());
            newRotate2.setAngle(90 - angle2);
             
            arcRefractedRay.setLength(-(90 - angle2));
            labelAngleRefracted.setText("Refracted angle: " + String.format("%.2f", angle2)+ "°");
            
            System.out.println("Angle 2: " + (90 - angle2)); 
            refractedRay.getTransforms().setAll(newRotate2);
    }
    
    public void rotateAnglesInternalReflection(double angle){
        
            totalRefractedRay.setVisible(true);
            refractedRay.setVisible(false);
            
            labelTotalInternalReflection.setVisible(true);
            
            
            Rotate newRotate2 = new Rotate();
            newRotate2.pivotXProperty().bind(incidentRay.endXProperty());
            newRotate2.pivotYProperty().bind(incidentRay.endYProperty());
            totalRefractedRay.getTransforms().setAll(newRotate2); 
            newRotate2.setAngle(angle - 90);
             
            arcRefractedRay.setLength(90 - angle);
            
            labelAngleRefracted.setText("Refracted angle: " + String.format("%.2f", (90 - angle))+ "°");
            
            System.out.println("total internal reflection and angle: " + angle);
    }
    
}
