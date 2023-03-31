package edu.vanier.mainPackage.refraction;

import javafx.scene.paint.Color;
import lombok.Data;

/**
 *
 * @author Matthew
 */
@Data
public class Material {
    
    private Color materialColor;
    private String materialName;
    private double refractionIndex;

    public Material(Color materialColor, String materialName, double refractionIndex) {
        this.materialColor = materialColor;
        this.materialName = materialName;
        this.refractionIndex = refractionIndex;
    }
    
    @Override
    public String toString(){
        return materialName + ", " + refractionIndex;
    }
    
}
