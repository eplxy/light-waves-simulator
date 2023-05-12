package DoubleSlit.UI;

import DoubleSlit.Simulation.Parameters;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author sabri
 */
public class AnimationController {
    Stage primaryStage;
    private DoubleSlitMenuController dsMenuController;
    @FXML
    Pane animationPane;
    @FXML
    Button btnPlay;
    @FXML
    Button btnPause;
    @FXML
    Button btnReset;
    
    private Circle circleTop;
    private Circle circleBottom;
    
    private RaysManager raysManager;
    
    
    public AnimationController(Stage primaryStage, DoubleSlitMenuController dsMenuController) {
        this.primaryStage = primaryStage;
        this.dsMenuController = dsMenuController;
        this.raysManager = new RaysManager(this);
    }
    
    
 

    public void initialize() {
        
        btnPlay.setOnAction((event) -> {
            handlePlay(event);
        });
        btnPause.setOnAction((event) -> {
            handlePause(event);
        });
        btnReset.setOnAction((event) -> {
                handleReset(event);

        });
        this.circleTop = new Circle(23, 300, 10);
        
        this.circleBottom = new Circle(23, 500, 10);
        setDragListenersTop(circleTop, circleBottom);
        setDragListenersBottom(circleBottom, circleTop);
        

        animationPane.getChildren().add(circleTop);
        animationPane.getChildren().add(circleBottom);
        
        
    }
 
    private void handlePlay(ActionEvent event) {
        raysManager.getAddRaysLoop().play();
        raysManager.getAnimationLoop().play();
    }
    private void handlePause(ActionEvent event) {
        raysManager.getAddRaysLoop().pause();
        raysManager.getAnimationLoop().pause();
    }
    private void handleReset(ActionEvent event) {
        raysManager.reset();
    }


    
    public final void setDragListenersTop(Circle cTop, Circle cBottom) {
        final Delta dragDelta = new Delta();
        cTop.setOnMousePressed((MouseEvent mouseEvent) -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.y = cTop.getCenterY() - mouseEvent.getSceneY();
        });
        cTop.setOnMouseReleased((MouseEvent mouseEvent) -> {
        });
        cTop.setOnMouseDragged((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                //stops dragging if the object is about to leave the animation pane
                if (mouseEvent.getSceneY() + dragDelta.y> animationPane.getLayoutBounds().getCenterY()
                        || mouseEvent.getSceneY() + dragDelta.y < animationPane.getLayoutBounds().getCenterY()-300){
                    return;
                }
                cTop.setCenterY(mouseEvent.getSceneY() + dragDelta.y);
                double newRelativePosY = animationPane.getLayoutBounds().getCenterY() - cTop.getCenterY();
                cBottom.setCenterY(animationPane.getLayoutBounds().getCenterY() + newRelativePosY);
                updateSpacing(cTop.getCenterY(), cBottom.getCenterY());
            }
        });
       
    }
    
    public final void setDragListenersBottom(Circle cBottom, Circle cTop) {
        final Delta dragDelta = new Delta();
        cBottom.setOnMousePressed((MouseEvent mouseEvent) -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.y = cBottom.getCenterY() - mouseEvent.getSceneY();
        });
        cBottom.setOnMouseReleased((MouseEvent mouseEvent) -> {
        });
        cBottom.setOnMouseDragged((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                //stops dragging if the object is about to leave the animation pane
                if (mouseEvent.getSceneY() + dragDelta.y> animationPane.getLayoutBounds().getCenterY()+300
                        || mouseEvent.getSceneY() + dragDelta.y < animationPane.getLayoutBounds().getCenterY()){
                    return;
                }
                cBottom.setCenterY(mouseEvent.getSceneY() + dragDelta.y);
                double newRelativePosY = animationPane.getLayoutBounds().getCenterY() - cBottom.getCenterY();
                cTop.setCenterY(animationPane.getLayoutBounds().getCenterY() + newRelativePosY);
                updateSpacing(cTop.getCenterY(), cBottom.getCenterY());

            }
        });
            }
    
    public void updateSpacing(double topY, double bottomY){
        double newSpacing = (bottomY-topY)/60;
        Parameters.setSpacing(newSpacing);
        dsMenuController.txtFieldSpacing.setText(String.valueOf(newSpacing));
        dsMenuController.sliderSpacing.setValue(newSpacing);
        
        
    }
    
    class Delta {

        double x, y;
    }

    public Circle getCircleTop() {
        return circleTop;
    }

    public Circle getCircleBottom() {
        return circleBottom;
    }

    public RaysManager getRaysManager() {
        return raysManager;
    }
    

}
