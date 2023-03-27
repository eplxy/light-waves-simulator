package edu.vanier.mainPackage.refraction;

import java.util.HashMap;
import javafx.scene.paint.Color;
import lombok.Data;

/**
 *
 * @author Matthew
 */
@Data
public class Material {
    
    public HashMap<Double, Color> hashMapMaterials = new HashMap<>();
    
    public HashMap AddingMaterials(){
        this.hashMapMaterials.put(1.33, Color.BLUE);
        
        return this.hashMapMaterials;
    }
    
}
