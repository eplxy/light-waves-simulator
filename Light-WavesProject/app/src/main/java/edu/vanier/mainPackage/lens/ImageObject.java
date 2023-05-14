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
public class ImageObject extends Item {

    //properties
    private double magnification;
    private double distToFocal;
    private String imgType;
    private Item source;
    private boolean inverted;

    //constructor(s)
    public ImageObject(SourceObject source) {
        this.itemType = Item.ITEMTYPE_IMAGE;
        this.source = source;
        this.node = new ImageView(ResourceManager.retrieveCandleImage());
        this.node.setOpacity(0.5);
        this.label = new ItemLabel(this);
        setMouseListeners();
    }

    //methods
    /**
     * Setup mouse listeners for right clicking only.
     */
    private void setMouseListeners() {
        node.setOnMousePressed((mouseEvent) -> {
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                try {
                    LensMenuController.currentLMC.createParametersPane(this);
                } catch (IOException e) {
                }

            }
        });
    }

    /**
     * Updates based on source and lens properties.
     */
    public void update() {
        updateSize();
        updatePosition();
        updateImgType();
    }

    /**
     * Updates the position of an image based on source and lens properties.
     */
    public void updatePosition() {
        this.move(LensPhysics.computeImageAbsPos(source));
        this.setRelPos(LensPhysics.computeRelPos(source)[1]);
    }

    /**
     * Updates the size of an image based on source and lens properties.
     */
    private void updateSize() {
        this.size = LensPhysics.computeImageSize(source);
        this.magnification = LensPhysics.computeImageMag(source);
        scaleNodeToSize();
    }

    /**
     * Updates the image type of an image based on source and lens properties.
     */
    private void updateImgType() {
        if (this.absPos > LensPhysics.lensSearch().getAbsPos()) {
            this.imgType = "real";
        }
        if (this.absPos < LensPhysics.lensSearch().getAbsPos()) {
            this.imgType = "virtual";
        }
    }

    /**
     * Adjusts node scaling based on item sizing.
     */
    public void scaleNodeToSize() {
        this.node.setScaleX(this.size / 40);
        this.node.setScaleY(this.size / 40);
    }

}
