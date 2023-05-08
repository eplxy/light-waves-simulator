package edu.vanier.mainPackage.refraction;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author Matthew Hantar
 */
public interface LineAndRay {
    
    /**
     * 
     * @param primaryStage
     * @param animationPane 
     */
    public void CreateLines(Stage primaryStage, Pane animationPane);
    
    /**
     * 
     * @param incidentRay
     * @param arcIncidentRay 
     */
    public void incidentObj(Line incidentRay, Arc arcIncidentRay);
    
    /**
     * 
     * @param refractedray
     * @param arcIncidentRay 
     */
    public void refractedObj(Line refractedray, Arc arcIncidentRay);
        
    /**
     * 
     * @param totalRefractedRay 
     */
    public void totalInternalRefractionObj(Line totalRefractedRay);
    
    /**
     * 
     * @param angle
     * @param index1
     * @param index2 
     */
    public void updateLines(double angle, double index1, double index2);
    
    /**
     * 
     * @param index
     * @param index2
     * @param materialPane1
     * @param list
     * @param angle1 
     */
    public void materialUpdateLines1(int index, int index2, Pane materialPane1, ArrayList<Material> list, String angle1);
    
    /**
     * 
     * @param index
     * @param index2
     * @param materialPane1
     * @param list
     * @param angle1 
     */
    public void materialUpdateLines2(int index, int index2, Pane materialPane1, ArrayList<Material> list, String angle1);
    
    /**
     * 
     * @param angle2 
     */
    public void rotateAnglesNormal(double angle2);
    
    /**
     * 
     * @param angle 
     */
    public void rotateAnglesInternalReflection(double angle);
    
    
}
