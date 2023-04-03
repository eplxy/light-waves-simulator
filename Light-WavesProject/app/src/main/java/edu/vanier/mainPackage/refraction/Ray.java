package edu.vanier.mainPackage.refraction;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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
    Line totalRefractedRay;
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
        totalRefractedRay = new Line();
        
        double angle2 = Vector.CalculateAngle(Vector.NAIR, Vector.NWATER, angle1);
        
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
        
        //Total internal Reflection
        totalRefractedRay.startXProperty().bind(animationPane.widthProperty().divide(2));
        totalRefractedRay.startYProperty().bind(animationPane.heightProperty().divide(2));
        totalRefractedRay.endXProperty().bind(animationPane.widthProperty());
        totalRefractedRay.endYProperty().bind(animationPane.heightProperty().divide(2));
        totalRefractedRay.setVisible(false);
        
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
        
        animationPane.getChildren().addAll(incidentRay, refractedRay, normalRay, horizontalRay, totalRefractedRay);
    }
    
    public void updateLines(double angle, double index1, double index2){
        
        Rotate newRotate = new Rotate();
        newRotate.pivotXProperty().bind(incidentRay.endXProperty());
        newRotate.pivotYProperty().bind(incidentRay.endYProperty());
        incidentRay.getTransforms().setAll(newRotate);
        newRotate.setAngle(angle);
        System.out.println(angle);
        
        double angle2 = Vector.CalculateAngle(index1, index2, angle);
        
        //This happens if it is a total internal reflection
        if (Double.isNaN(angle2)) {
            totalRefractedRay.setVisible(true);
            refractedRay.setVisible(false);
            Rotate newRotate2 = new Rotate();
            newRotate2.pivotXProperty().bind(incidentRay.endXProperty());
            newRotate2.pivotYProperty().bind(incidentRay.endYProperty());
            totalRefractedRay.getTransforms().setAll(newRotate2); 
            newRotate2.setAngle(angle - 90);
            System.out.println("total internal reflection and angle: " + angle);
        }
        
        //This happens if it is not a total internal reflection
        if (!Double.isNaN(angle2)) {
            totalRefractedRay.setVisible(false);
            refractedRay.setVisible(true);
            Rotate newRotate2 = new Rotate();
            newRotate2.pivotXProperty().bind(incidentRay.endXProperty());
            newRotate2.pivotYProperty().bind(incidentRay.endYProperty());
            refractedRay.getTransforms().setAll(newRotate2); 
            newRotate2.setAngle(90 - angle2);
            System.out.println("Angle 2: " + angle2); 
        }
    }
    
    public void materialUpdateLines1(int index, int index2, Pane materialPane1, ArrayList<Material> list, String angle1){
        BackgroundFill backgroundFill =
        new BackgroundFill(list.get(index).getMaterialColor(),new CornerRadii(10),new Insets(10));

                Background background =
                    new Background(backgroundFill);

                materialPane1.setBackground(background);
                
        double angle2 = Vector.CalculateAngle(list.get(index).getRefractionIndex(), list.get(index2).getRefractionIndex(), Double.parseDouble(angle1));
        Rotate rotate2 = new Rotate();
        rotate2.pivotXProperty().bind(incidentRay.endXProperty());
        rotate2.pivotYProperty().bind(incidentRay.endYProperty());
        rotate2.setAngle(90 - angle2);
        refractedRay.getTransforms().setAll(rotate2);
    }
    
    public void materialUpdateLines2(int index, int index2, Pane materialPane1, ArrayList<Material> list, String angle1){
        
        BackgroundFill backgroundFill =
        new BackgroundFill(list.get(index2).getMaterialColor(),new CornerRadii(10),new Insets(10));

                Background background =
                    new Background(backgroundFill);

                materialPane1.setBackground(background);
                
        double angle2 = Vector.CalculateAngle(list.get(index).getRefractionIndex(), list.get(index2).getRefractionIndex(), Double.parseDouble(angle1));
        Rotate rotate2 = new Rotate();
        rotate2.pivotXProperty().bind(incidentRay.endXProperty());
        rotate2.pivotYProperty().bind(incidentRay.endYProperty());
        rotate2.setAngle(90 - angle2);
        refractedRay.getTransforms().setAll(rotate2);
    }
    
}
