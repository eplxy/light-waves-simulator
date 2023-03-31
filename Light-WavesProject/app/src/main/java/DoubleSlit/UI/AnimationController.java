/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoubleSlit.UI;

import DoubleSlit.Simulation.Parameters;
import javafx.stage.Stage;

/**
 *
 * @author sabri
 */
public class AnimationController {
    Stage primaryStage;
    private Parameters parameters;
     
    public AnimationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
    
    
}
