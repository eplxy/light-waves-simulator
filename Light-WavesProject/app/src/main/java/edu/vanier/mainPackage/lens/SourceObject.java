package edu.vanier.mainPackage.lens;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.transform.Scale;

/**
 *
 * @author Steven
 */
public class SourceObject extends Item {

    //properties
    static double mouseAnchorX;
    private boolean fromImage;
    private ImageObject image;
    private double rayPointHeight;

    //constructor(s)
    public SourceObject(double size, double absPos) {
        this.itemType = Item.ITEMTYPE_SOURCE;
        this.absPos = absPos;
        this.size = size;
        this.node = new ImageView(ResourceManager.retrieveCandleImage());
        this.rayPointHeight = 300;
        this.image = new ImageObject(this);
        scaleNodeToSize();
        setDragListeners();
        this.label = new ItemLabel(this);
    }

    //methods
    private void setDragListeners() {
        node.setOnMousePressed((mouseEvent) -> {
            mouseAnchorX = mouseEvent.getX();

        });

        node.setOnMouseDragged((mouseEvent) -> {
            if ((mouseEvent.getSceneX() - (this.node.getLayoutX()) + (this.node.getLayoutX() + this.node.getBoundsInLocal()
                    .getWidth() / 2) - 1400 / 2) / LensMain.CONVERSIONFACTOR < LensPhysics.lensSearch().getAbsPos()) {
                node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
                this.setAbsPos(((this.node.getLayoutX() + this.node.getBoundsInLocal()
                        .getWidth() / 2) - 1400 / 2) / LensMain.CONVERSIONFACTOR);
                this.setRelPos(LensPhysics.computeRelPos(this)[0]);
                this.label.updateLabel();
                this.image.update();
            }
            Rays.updateRays();
        });

        node.setOnMouseReleased((mouseEvent) -> {
            if (LensMenuController.currentPPController != null) {
                LensMenuController.currentPPController.updateTextFields();
            }
        });
        
    }

    public void scaleNodeToSize() {
        this.node.setScaleX(this.size / 40);
        this.node.setScaleY(this.size / 40);
    }

    //getters and setters
    public boolean isFromImage() {
        return fromImage;
    }

    public void setFromImage(boolean fromImage) {
        this.fromImage = fromImage;
    }

    public ImageObject getImage() {
        return image;
    }

    public void setImage(ImageObject image) {
        this.image = image;
    }

    public double getRayPointHeight() {
        return this.rayPointHeight;
    }

    public void setRayPointHeight(double rayPointHeight) {
        this.rayPointHeight = rayPointHeight;
    }

}
