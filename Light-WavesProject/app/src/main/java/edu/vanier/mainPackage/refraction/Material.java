package edu.vanier.mainPackage.refraction;

import javafx.scene.paint.Color;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Matthew
 */
@Getter
@Setter
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

    public Color getMaterialColor() {
        return this.materialColor;
    }

    public void setMaterialColor(Color materialColor) {
        this.materialColor = materialColor;
    }

    public String getMaterialName() {
        return this.materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public double getRefractionIndex() {
        return this.refractionIndex;
    }

    public void setRefractionIndex(double refractionIndex) {
        this.refractionIndex = refractionIndex;
    }
    
    
    
}
