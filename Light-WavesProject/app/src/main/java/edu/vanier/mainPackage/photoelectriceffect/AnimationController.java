package edu.vanier.mainPackage.photoelectriceffect;

import javafx.fxml.FXML;

/**
 *
 * @author maesh
 */
public class AnimationController {
    
    PhotoelectricMenuController controller = new PhotoelectricMenuController();
    double minimumEnergy = controller.getMinimumEnergy();
    
    @FXML
    public void startAnimation() {}


}