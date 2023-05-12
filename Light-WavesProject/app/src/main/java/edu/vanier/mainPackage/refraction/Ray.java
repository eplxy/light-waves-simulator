package edu.vanier.mainPackage.refraction;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

/**
 * This class represents a ray of light and provides methods to update the ray 
 * based on angle and refractive index.
 * 
 * It extends the abstract class LineBuild and all the methods are described in
 * the LineAndRay interface. 
 *
 * @author Matthew Hantar
 */
public class Ray extends LineBuild{
    
    @Override
    public void updateLines(double angle, double index1, double index2) {

        Rotate newRotate = new Rotate();
        newRotate.pivotXProperty().bind(incidentRay.endXProperty());
        newRotate.pivotYProperty().bind(incidentRay.endYProperty());
        incidentRay.getTransforms().setAll(newRotate);
        newRotate.setAngle(angle);
        arcIncidentRay.setLength(-angle);

        angle2 = Vector.CalculateAngle(index1, index2, angle);
        labelAngleIncident.setText("Incident angle: " + String.format("%.2f", angle) + "°");

        //This happens if it is a total internal reflection
        if (Double.isNaN(angle2)) {
            rotateAnglesInternalReflection(angle);
        }
        //This happens if it is not a total internal reflection
        if (!Double.isNaN(angle2)) {
            rotateAnglesNormal(angle2);
        }
    }

    @Override
    public void materialUpdateLines1(int index, int index2, Pane materialPane1, ArrayList<Material> list, String angle1) {
        BackgroundFill backgroundFill
                = new BackgroundFill(list.get(index).getMaterialColor(), new CornerRadii(10), new Insets(10));

        Background background
                = new Background(backgroundFill);

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

    @Override
    public void materialUpdateLines2(int index, int index2, Pane materialPane1, ArrayList<Material> list, String angle1) {

        BackgroundFill backgroundFill
                = new BackgroundFill(list.get(index2).getMaterialColor(), new CornerRadii(10), new Insets(10));

        Background background
                = new Background(backgroundFill);

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

    @Override
    public void rotateAnglesNormal(double angle2) {
        totalRefractedRay.setVisible(false);
        refractedRay.setVisible(true);
        labelTotalInternalReflection.setVisible(false);

        Rotate newRotate2 = new Rotate();
        newRotate2.pivotXProperty().bind(incidentRay.endXProperty());
        newRotate2.pivotYProperty().bind(incidentRay.endYProperty());
        newRotate2.setAngle(90 - angle2);

        arcRefractedRay.setLength(-(90 - angle2));
        labelAngleRefracted.setText("Refracted angle: " + String.format("%.2f", 90 - angle2) + "°");
        
        refractedRay.getTransforms().setAll(newRotate2);
    }

    @Override
    public void rotateAnglesInternalReflection(double angle) {

        totalRefractedRay.setVisible(true);
        refractedRay.setVisible(false);
        labelTotalInternalReflection.setVisible(true);

        Rotate newRotate2 = new Rotate();
        newRotate2.pivotXProperty().bind(incidentRay.endXProperty());
        newRotate2.pivotYProperty().bind(incidentRay.endYProperty());
        totalRefractedRay.getTransforms().setAll(newRotate2);
        newRotate2.setAngle(angle - 90);

        arcRefractedRay.setLength(90 - angle);

        labelAngleRefracted.setText("Refracted angle: " + String.format("%.2f", (90 - angle)) + "°");
    }
}
