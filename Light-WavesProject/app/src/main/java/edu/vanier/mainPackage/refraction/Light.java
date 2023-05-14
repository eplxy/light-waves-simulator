package edu.vanier.mainPackage.refraction;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import lombok.Data;

/**
 * This class represents a light with its color and behavior.
 *
 * @author Matthew Hantar
 */
@Data
public class Light {
    
    /**
    The list of color hex codes for the light.

    ArrayList<String> colorHexList = new ArrayList<>(Arrays.asList("#0000ff" ,"#0029ff" ,
    "#003cff" ,"#004aff" ,"#0056ff" ,"#0061ff" ,"#006aff" ,"#0073ff" ,"#007bff" ,"#0082ff" ,
    "#0089ff" ,"#008fff" ,"#0095ff" ,"#009bff" ,"#00a0ff" ,"#00a5ff" ,"#00aaff" ,"#00afff" ,
    "#00b3ff" ,"#00b8ff" ,"#00bcff" ,"#00c1ff" ,"#00c5ff" ,"#00c9fe" ,"#00cdf2" ,"#00d1e5" ,
    "#00d5d8" ,"#00d9cb" ,"#00ddbe" ,"#00e1b0" ,"#00e4a3" ,"#00e895" ,"#00eb87" ,"#00ef79" ,
    "#00f26b" ,"#00f55c" ,"#00f84d" ,"#00fa3c" ,"#00fd28" ,"#06ff00" , "#38fb00" ,"#4ef700" ,
    "#5ff300" ,"#6cef00" ,"#77eb00" ,"#82e600" ,"#8be200" ,"#93de00" ,"#9bd900" ,"#a2d500" ,
    "#a9d000" ,"#b0cc00" ,"#b6c700" ,"#bcc200" ,"#c1bd00" ,"#c6b800" ,"#cbb300" ,"#d0ae00" ,
    "#d5a900" ,"#d9a400" ,"#dd9e00" ,"#e19900" ,"#e49300" ,"#e88e00" ,"#eb8800" ,"#ee8200" ,
    "#f07c00" ,"#f37500" ,"#f56f00" ,"#f76800" ,"#f96100" ,"#fa5a00" ,"#fc5200" ,"#fd4a00" ,
    "#fe4100" ,"#fe3700" ,"#ff2b00" ,"#ff1c00" ,"#ff0000"));

    
    /**
    Changes the color of the given lines to a color based on a given value.
    @param incidentLine The incident line.
    @param refractedLine The refracted line.
    @param totalRefractedLine The total refracted line.
    @param value The value based on which the color of the lines is determined.
    */
    public void colorLines(Line incidentLine, Line refractedLine, Line totalRefractedLine, double value){
        
        //Changes the range of the value, from 300 to 700, to the colorHexList length. 
        int newValue = (int) ((((value - 300) * 40) / 400) + 0);
        
        try {
            Stop[] stops = new Stop[] { new Stop(0, Color.TRANSPARENT), new Stop(1, Color.web(colorHexList.get(newValue)))};
            LinearGradient lngnt = new LinearGradient(0, 0, 0.5, 0, true, CycleMethod.NO_CYCLE, stops);
        
            incidentLine.setStroke(lngnt);
            
            Stop[] stops2 = new Stop[] { new Stop(0, Color.web(colorHexList.get(newValue))), new Stop(1, Color.TRANSPARENT)};
            LinearGradient lngnt2 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops2);
            
            refractedLine.setStroke(lngnt2);
            totalRefractedLine.setStroke(lngnt2);
        } catch (Exception e) {
        }
        
    }
    
}
