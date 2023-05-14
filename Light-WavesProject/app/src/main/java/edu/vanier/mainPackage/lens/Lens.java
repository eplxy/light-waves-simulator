package edu.vanier.mainPackage.lens;

import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Steven
 */
@Getter
@Setter

public class Lens extends Item {

    //properties
    static double mouseAnchorX;

    private double focalLength, refractionIndex, radius;
    private String lensType;

    //constructor(s)
    public Lens(double focalLength, double absPos) {
        this.itemType = Item.ITEMTYPE_LENS;
        this.focalLength = focalLength;
        this.setLensType(this.determineType());
        this.node = new ImageView(ResourceManager.retrieveConvergentLensImage());
        setMouseListeners();
        this.label = new ItemLabel(this);
        this.refractionIndex = 1.5;
        this.radius = LensPhysics.lensMakerToRadius(this);
        this.updateLensImage();
    }

    //lens types
    public static final String LENSTYPE_CONVERGENT = "Convergent";
    public static final String LENSTYPE_DIVERGENT = "Divergent";

    //methods
    /**
     * Returns the type of lens, dependent on focal length.
     *
     * @return "Convergent" if focal length is positive, "Divergent" if focal
     * length is negative.
     */
    public String determineType() {
        if (this.focalLength > 0) {
            return LENSTYPE_CONVERGENT;
        } else {
            return LENSTYPE_DIVERGENT;
        }
    }

    /**
     * Setup mouse listeners for dragging and clicking.
     */
    private void setMouseListeners() {
        node.setOnMousePressed((mouseEvent) -> {
            mouseAnchorX = mouseEvent.getX();

            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                try {
                    LensMenuController.currentLMC.createParametersPane(this);
                } catch (IOException e) {
                }

            }

        });

        node.setOnMouseDragged((mouseEvent) -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            this.setAbsPos(((this.node.getLayoutX() + this.node.getBoundsInLocal()
                    .getWidth() / 2) - 1400 / 2) / LensMain.CONVERSIONFACTOR);
            this.label.updateLabel();
            SourceObject tempSource = LensPhysics.sourceSearch();
            tempSource.getImage().update();
            tempSource.setRelPos(LensPhysics.computeRelPos(tempSource)[0]);
            tempSource.getLabel().updateLabel();
            LensMenuController.currentLMC.lensLineMove();

            Rays.updateRays();

        });

        node.setOnMouseReleased((mouseEvent) -> {
            if (LensMenuController.currentPPController != null) {
                LensMenuController.currentPPController.updateTextFields();
            }
        });

    }

    /**
     * Updates position in case of needed fix.
     */
    private void positionAdjustFix() {
        this.move(absPos);
    }

    /**
     * Updates the node iamge of the lens, depending on whether it is convergent
     * or divergent.
     */
    public void updateLensImage() {
        if (this.getLensType().equals(Lens.LENSTYPE_CONVERGENT)) {
            ((ImageView) this.node).setImage(ResourceManager.retrieveConvergentLensImage());
        } else {
            ((ImageView) this.node).setImage(ResourceManager.retrieveDivergentLensImage());
        }
        this.node.setScaleX(1 - Math.abs(this.radius) / 400);
        positionAdjustFix();
    }

    /**
     * Updates the focal length of the lens.
     */
    public void updateFocalLength() {
        this.setFocalLength(LensPhysics.lensMakerToFocalLength(this));
    }

    /**
     * Updates the radius of curvature of the lens.
     */
    public void updateRadius() {
        this.setRadius(LensPhysics.lensMakerToRadius(this));
    }

}
