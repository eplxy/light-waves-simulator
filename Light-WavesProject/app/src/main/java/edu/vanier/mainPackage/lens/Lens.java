package edu.vanier.mainPackage.lens;

import javafx.scene.image.ImageView;

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
    private FocalPoint focalPoint;

    //constructor(s)
    public Lens(double focalLength, double absPos) {
        this.itemType = Item.ITEMTYPE_LENS;
        this.focalLength = focalLength;
        this.node = new ImageView(ResourceManager.retrieveConvergentLensImage());
        setDragListeners();
        this.label = new ItemLabel(this);
        this.refractionIndex = 1.5;
        this.radius = LensPhysics.lensMakerToRadius(this);
        this.updateLensImage();
    }

    //lens types
    public static final String LENSTYPE_CONVERGENT = "Convergent";
    public static final String LENSTYPE_DIVERGENT = "Divergent";

    public String determineType() {
        if (this.focalLength > 0) {
            return LENSTYPE_CONVERGENT;
        } else {
            return LENSTYPE_DIVERGENT;
        }
    }

    //methods
    private void setDragListeners() {
        node.setOnMousePressed((mouseEvent) -> {
            mouseAnchorX = mouseEvent.getX();

        });

        node.setOnMouseDragged((mouseEvent) -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            this.setAbsPos(((this.node.getLayoutX() + this.node.getBoundsInLocal()
                    .getWidth() / 2) - 1400 / 2) / LensMain.CONVERSIONFACTOR);
            this.label.updateLabel();
            SourceObject tempSource = LensPhysics.sourceSearch();
            tempSource.getImage().update();
            tempSource.setRelPos(LensPhysics.computeRelPos(tempSource)[0]);
            LensMenuController.currentLMC.lensLineMove();
            Rays.updateRays();

        });

        node.setOnMouseReleased((mouseEvent) -> {
            if (LensMenuController.currentPPController != null) {
                LensMenuController.currentPPController.updateTextFields();
            }
        });

    }

    public void updateLensImage() {
        //TODO:inaccurate radius changes
        this.node.setScaleX(1 - this.radius / 400);
    }

    public void updateFocalLength() {
        this.setFocalLength(LensPhysics.lensMakerToFocalLength(this));
    }

    public void updateRadius() {
        this.setRadius(LensPhysics.lensMakerToRadius(this));
    }

    //getters and setters
    public double getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(double focalLength) {
        this.focalLength = focalLength;
    }

    public double getRefractionIndex() {
        return refractionIndex;
    }

    public void setRefractionIndex(double refractionIndex) {
        this.refractionIndex = refractionIndex;
    }

    public String getLensType() {
        this.setLensType(this.determineType());
        return lensType;
    }

    public void setLensType(String lensType) {
        this.lensType = lensType;
    }

    public FocalPoint getFocalPoint() {
        return focalPoint;
    }

    public void setFocalPoint(FocalPoint focalPoint) {
        this.focalPoint = focalPoint;
    }

    @Override
    public String toString() {
        return Integer.toString(this.orderNumber) + " " + this.itemType;
    }
}
