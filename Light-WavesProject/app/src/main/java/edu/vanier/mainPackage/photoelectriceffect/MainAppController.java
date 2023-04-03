package edu.vanier.mainPackage.photoelectriceffect;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.shape.Circle;

/*
 * @author maesh
 */
public class MainAppController{
    @FXML
    Slider sliderIntensity = new Slider();
    
    @FXML
    Slider sliderWavelegth = new Slider();
    
    @FXML
    Slider sliderBatteryVoltage = new Slider();
  
    @FXML
    Button btnSurfaceMaterial = new Button();
    
    @FXML
    Button btnGoBackToMainMenu = new Button();
    
    @FXML
    Button btnPlay = new Button();
    
    @FXML
    Button btnForward = new Button();
    
    @FXML
    Circle photoelectron1 = new Circle();
    
    @FXML
    Circle photoelectron2 = new Circle();
    
    @FXML
    Circle photoelectron3 = new Circle();
    
    @FXML
    Circle photoelectron4 = new Circle();
    
    @FXML
    Circle photoelectron5 = new Circle();
    
    @FXML
    Circle photoelectron6 = new Circle();
    
    @FXML
    Circle photoelectron7 = new Circle();
    
    @FXML
    Circle photoelectron8 = new Circle();
    
    @FXML
    Circle photoelectron9 = new Circle();
    
    private boolean initialized = false;
    
    public void initialize(URL location, ResourceBundle resources) {
        initialized = true;
    }
    
    public void moveCircle() {
        
        double x = photoelectron1.getCenterX();
        double y = photoelectron1.getCenterY();
        double radius = photoelectron1.getRadius();
        
        double newX = x + 5;
        photoelectron1.setCenterX(newX);
        
        if (newX - radius >= 0.001) {
        photoelectron1.setCenterX(0.001 - radius);
        }
        
        
        double maxY = photoelectron1.getParent().getBoundsInLocal().getMaxY();
        if (newX + radius >= photoelectron1.getParent().getBoundsInLocal().getMaxX()) {
            newX = photoelectron1.getParent().getBoundsInLocal().getMinX() + radius;
            y += 50; 
            if (y + radius >= maxY) {
                y = photoelectron1.getCenterY();
            }
        }
        photoelectron1.setCenterX(newX);
        photoelectron1.setCenterY(y);
    }
}