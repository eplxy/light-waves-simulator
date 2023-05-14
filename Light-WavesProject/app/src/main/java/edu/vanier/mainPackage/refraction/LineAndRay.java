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
    
    //Methods in the abstract class LineBuild
    
    /**
     * Creates the necessary lines and arcs for the simulation of the refraction
     * of light.
     *
     * @param primaryStage The primary stage of the application.
     * @param animationPane The pane on which the simulation is drawn.
     */
    public void CreateLines(Stage primaryStage, Pane animationPane);
    
    /**
     * Creates the incident ray of light and its corresponding arc.
     *
     * @param incidentRay The incident ray of light.
     * @param arcIncidentRay The arc of the incident ray of light.
     */
    public void incidentObj(Line incidentRay, Arc arcIncidentRay);
    
    /**
     * Creates the refracted ray of light and its corresponding arc.
     *
     * @param refractedray The refracted ray of light.
     * @param arcIncidentRay The arc of the incident ray of light.
     */
    public void refractedObj(Line refractedray, Arc arcIncidentRay);
        
    /**
     * Creates the total refracted ray of light.
     *
     * @param totalRefractedRay The total refracted ray of light.
     */
    public void totalInternalRefractionObj(Line totalRefractedRay);
    
    
    
    //Methods in the class Ray.
    
     /**
     * This method updates the angle and length of the incident ray and the 
     * refracted ray based on the given angle and refractive indices. 
     * It also updates the angle label for the incident ray.
     *
     * @param angle  the angle of incidence in degrees
     * @param index1 the refractive index of the first material
     * @param index2 the refractive index of the second material
     */
    public void updateLines(double angle, double index1, double index2);
    
     /**
     * This method updates the refracted ray based on the angle of refraction 
     * and the refractive indices of the two materials involved. It also updates
     * the angle label for the refracted ray and changes the background of the
     * given material pane to the color of the first material.
     *
     * @param index   the index of the first material in the list
     * @param index2  the index of the second material in the list
     * @param materialPane1 the pane to change the background of
     * @param list    the list of materials
     * @param angle1  the angle of incidence in degrees
     */
    public void materialUpdateLines1(int index, int index2, Pane materialPane1, ArrayList<Material> list, String angle1);
    
     /**
     * This method updates the refracted ray based on the angle of refraction 
     * and the refractive indices of the two materials involved. It also updates
     * the angle label for the refracted ray and changes the background of the
     * given material pane to the color of the second material.
     *
     * @param index   the index of the first material in the list
     * @param index2  the index of the second material in the list
     * @param materialPane1 the pane to change the background of
     * @param list    the list of materials
     * @param angle1  the angle of incidence in degrees
     */
    public void materialUpdateLines2(int index, int index2, Pane materialPane1, ArrayList<Material> list, String angle1);
    
    /**
     * This method rotates the refracted ray based on the angle of refraction 
     * and updates the angle label for the refracted ray. It also hides the 
     * total refracted ray and shows the refracted ray.
     *
     * @param angle2 the angle of refraction in degrees
     */
    public void rotateAnglesNormal(double angle2);
    
    /**
     * This method rotates the total refracted ray based on the angle of 
     * incidence and updates the angle label for the refracted ray. It also 
     * hides the refracted ray and shows the total refracted ray and the label 
     * indicating total internal reflection.
     *
     * @param angle the angle of incidence in degrees
     */
    public void rotateAnglesInternalReflection(double angle);
    
    
}
