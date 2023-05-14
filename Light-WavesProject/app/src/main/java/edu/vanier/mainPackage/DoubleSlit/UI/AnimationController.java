package edu.vanier.mainPackage.DoubleSlit.UI;

import edu.vanier.mainPackage.DoubleSlit.Simulation.RaysManager;
import edu.vanier.mainPackage.DoubleSlit.Simulation.Parameters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lombok.Getter;

/**
 *
 * @author Sabrina Amoura
 */
public class AnimationController {
    Stage primaryStage;

    @FXML
    @Getter
    Pane animationPane;

    @FXML
    Button btnPlay;

    @FXML
    Button btnPause;

    @FXML
    Button btnReset;

    private DoubleSlitMenuController dsMenuController;
    @Getter private Circle circleTop;
    @Getter private Circle circleBottom;
    @Getter private RaysManager raysManager;
    private final double SPACING_SCALE = 20;
    private final double SPACING_BOUNDS = 100;

    /**
     * Sole constructor.
     * @param primaryStage
     * @param dsMenuController controller of the double slit experiment menu
     */
    public AnimationController
        (Stage primaryStage, DoubleSlitMenuController dsMenuController) {
        this.primaryStage = primaryStage;
        this.dsMenuController = dsMenuController;
        this.raysManager = new RaysManager(this);
    }

    /**
     * Sets up event handlers for the buttons and the slits.
     */
    public void initialize() {
        btnPause.setDisable(true);
        animationPane.setStyle("-fx-border-color: lightGrey");

        btnPlay.setOnAction((event) -> {
            handlePlay();
        });
        btnPause.setOnAction((event) -> {
            handlePause();
        });
        btnReset.setOnAction((event) -> {
            handleReset();
        });
        
        this.circleTop = new Circle(23, 300, 10);
        this.circleBottom = new Circle(23, 500, 10);
        setDragListenersTop(circleTop, circleBottom);
        setDragListenersBottom(circleBottom, circleTop);
        animationPane.getChildren().add(circleTop);
        animationPane.getChildren().add(circleBottom);
        initialCircles(this.circleTop, this.circleBottom);
    }

    /**
     * Handles the play button.
     * @param event 
     */
    public void handlePlay() {
        btnPlay.setDisable(true);
        btnPause.setDisable(false);
        raysManager.getAddRaysLoop().play();
        raysManager.getAnimationLoop().play();
    }

    /**
     * Handles the pause button.
     * @param event 
     */
    private void handlePause() {
        btnPause.setDisable(true);
        btnPlay.setDisable(false);
        raysManager.getAddRaysLoop().pause();
        raysManager.getAnimationLoop().pause();
    }

    /**
     * Handles the reset button.
     * @param event 
     */
    public void handleReset() {
        reset();
        btnPause.setDisable(true);
        btnPlay.setDisable(false);
    }
    
     /**
     * Clears the animationPane
     */
    public void reset() {
        raysManager.getAddRaysLoop().pause();
        raysManager.getAnimationLoop().pause();
        animationPane.getChildren().removeAll(raysManager.getArcList());
        raysManager.getArcList().clear();
        raysManager.getTopArcList().clear();
        raysManager.getBottomArcList().clear();
    }

    /**
     * Symmetrically move the two slits when you drag the one at the top
     * and update the value of the spacing in the parameters menu.
     * @param cTop top circle object representing a slit
     * @param cBottom bottom circle object representing a slit
     */
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
                //stops dragging if the object is at the maximum
                if (mouseEvent.getSceneY() + dragDelta.y 
                        > animationPane.getLayoutBounds().getCenterY()
                        || mouseEvent.getSceneY() + dragDelta.y < 
                        animationPane.getLayoutBounds().getCenterY() - SPACING_BOUNDS) {
                    return;
                }
                cTop.setCenterY(mouseEvent.getSceneY() + dragDelta.y);
                double newRelativePosY = animationPane.getLayoutBounds().getCenterY()
                        - cTop.getCenterY();
                cBottom.setCenterY(animationPane.getLayoutBounds().getCenterY() 
                        + newRelativePosY);
                updateSpacing(cTop.getCenterY(), cBottom.getCenterY());
            }
        });

    }

    /**
     * Symmetrically move the two slits when you drag the one at the top
     * and update the value of the spacing in the parameters menu.
     * @param cTop top circle object representing a slit
     * @param cBottom bottom circle object representing a slit
     */
    public final void setDragListenersBottom(Circle cBottom, Circle cTop) {
        final Delta dragDelta = new Delta();
        cBottom.setOnMousePressed((MouseEvent mouseEvent) -> {
            dragDelta.y = cBottom.getCenterY() - mouseEvent.getSceneY();
        });
        cBottom.setOnMouseReleased((MouseEvent mouseEvent) -> {
        });
        cBottom.setOnMouseDragged((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                if (mouseEvent.getSceneY() + dragDelta.y > 
                        animationPane.getLayoutBounds().getCenterY() + SPACING_BOUNDS
                        || mouseEvent.getSceneY() + dragDelta.y < 
                        animationPane.getLayoutBounds().getCenterY()) {
                    return;
                }
                cBottom.setCenterY(mouseEvent.getSceneY() + dragDelta.y);
                double newRelativePosY = animationPane.getLayoutBounds().getCenterY() 
                        - cBottom.getCenterY();
                cTop.setCenterY(animationPane.getLayoutBounds().getCenterY() 
                        + newRelativePosY);
                updateSpacing(cTop.getCenterY(), cBottom.getCenterY());
            }
        });
    }

    /**
     * Update the value of the slider and the text field when the user drags the slits.
     * @param topY y coordinate of the top slit
     * @param bottomY y coordinate of the bottom slit
     */
    public void updateSpacing(double topY, double bottomY) {
        double newSpacing = (bottomY - topY) / SPACING_SCALE;
        Parameters.setSpacing(newSpacing);
        dsMenuController.txtFieldSpacing.setText(String.valueOf(newSpacing));
        dsMenuController.sliderSpacing.setValue(newSpacing);
    }

    /**
     * Support class for the dragListeners.
     */
    class Delta {
        double x, y;
    }
    
    /**
     * Initial placement of the circles.
     * @param ctop top circle object representing a slit
     * @param cBottom bottom circle object representing a slit
     */
    private void initialCircles(Circle cTop, Circle cBottom){
        double relativeSpacing = Parameters.getSpacing() * 10;
            double center = 375;
            cBottom.setCenterY(center + relativeSpacing);
            cTop.setCenterY(center - relativeSpacing);
    }
}
