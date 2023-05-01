package edu.vanier.mainPackage.refraction;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import lombok.Data;

/**
 *
 * @author Matthew Hantar
 */
@Data
public class Light {
    
    ArrayList<String> colorHexList = new ArrayList<>(Arrays.asList("#800000", "#8B0000", "#A52A2A", "#B22222", "#DC143C",
            "#FF0000", "#FF6347", "#CD5C5C", "#FA8072", "#FFA07A", "#FF8C00", "#FFA500", "#FFD700", "#B8860B", "#DAA520",
            "#BDB76B", "#808000", "#9ACD32", "#7CFC00", "#7FFF00", "#008000", "#006400", "#228B22", "#00FF00", "#90EE90",
            "#00FA9A", "#00FF7F", "#20B2AA", "#4682B4", "#6495ED", "#1E90FF", "#0000FF", "#00008B", "#000080", "#4169E1",
            "#483D8B", "#6A5ACD", "#9370DB", "#8B008B", "#800080"));
    
    public void color(Line line, double value){
        
        int newValue = (int) ((((value - 300) * 255) / 400) + 0);
        
        if (value >= 300) {
            
            Stop[] stops = new Stop[] { new Stop(0, Color.TRANSPARENT), new Stop(1, Color.rgb(255, newValue, 0))};
            LinearGradient lngnt = new LinearGradient(0, 0, 1.5, 0, true, CycleMethod.NO_CYCLE, stops);
        
            line.setStroke(lngnt);
        }
        
        if (value >= 400 && value < 500) {
            
            Stop[] stops = new Stop[] { new Stop(0, Color.TRANSPARENT), new Stop(1, Color.rgb(newValue, 255 , newValue))};
            LinearGradient lngnt = new LinearGradient(0, 0, 1.5, 0, true, CycleMethod.NO_CYCLE, stops);
        
            line.setStroke(lngnt);
        }
        
        if (value >= 500 && value < 600) {
            
            Stop[] stops = new Stop[] { new Stop(0, Color.TRANSPARENT), new Stop(1, Color.rgb(newValue, newValue , 255))};
            LinearGradient lngnt = new LinearGradient(0, 0, 1.5, 0, true, CycleMethod.NO_CYCLE, stops);
        
            line.setStroke(lngnt);
        }
        
        if (value >= 600) {
            
            Stop[] stops = new Stop[] { new Stop(0, Color.TRANSPARENT), new Stop(1, Color.rgb(0, 0 , 255))};
            LinearGradient lngnt = new LinearGradient(0, 0, 1.5, 0, true, CycleMethod.NO_CYCLE, stops);
        
            line.setStroke(lngnt);
        }
        
    }
    
    public void colorLines(Line line, double value){
        
        int newValue = (int) ((((value - 300) * 40) / 400) + 0);
        
        try {
            Stop[] stops = new Stop[] { new Stop(0, Color.TRANSPARENT), new Stop(1, Color.web(colorHexList.get(newValue)))};
            LinearGradient lngnt = new LinearGradient(0, 0, 0.5, 0, true, CycleMethod.NO_CYCLE, stops);
        
            line.setStroke(lngnt);
        } catch (Exception e) {
        }
        
    }
    
}
