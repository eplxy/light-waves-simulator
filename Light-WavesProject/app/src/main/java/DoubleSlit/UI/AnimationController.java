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
     
    public AnimationController(Stage primaryStage, Parameters parameters) {
        this.primaryStage = primaryStage;
        this.parameters = parameters;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
}
