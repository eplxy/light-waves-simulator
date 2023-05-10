package edu.vanier.mainPackage.lens;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author someo
 */
public class ResourceManager {

    public static final String FOLDERPATH = "/images/lens/";
    public static final String CANDLE = "candle.png";
    public static final String CONVERGENTLENS = "convergentlens.png";
    
    

    public static void changeSourceImage(SourceObject source, String fileName){
        source.setNode(new ImageView(retrieveImage(fileName)));
        source.setRayPointHeight(2*source.getNode().getBoundsInLocal().getWidth()/3);
        
    }
    public static Image retrieveImage(String fileName) {
        return new Image(ResourceManager.class.getResource(FOLDERPATH+fileName).toString());
    }

    
    //default retrieves
    public static Image retrieveConvergentLensImage(){
        return new Image(ResourceManager.class.getResource(FOLDERPATH+CONVERGENTLENS).toString());
    }
    public static Image retrieveCandleImage(){
        return new Image(ResourceManager.class.getResource(FOLDERPATH+CANDLE).toString());
    }
}
