package edu.vanier.mainPackage.lens;

import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 *
 * @author Steven
 */
public class SourceObject extends Item {

    //properties
    private static double mouseAnchorX;
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
        setMouseListeners();
        this.label = new ItemLabel(this);
    }

    //methods
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
            if ((mouseEvent.getSceneX() - (this.node.getLayoutX()) + (this.node.getLayoutX() + this.node.getBoundsInLocal()
                    .getWidth() / 2) - 1400 / 2) / LensMain.CONVERSIONFACTOR < LensPhysics.lensSearch().getAbsPos()) {
                node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
                this.setAbsPos(((this.node.getLayoutX() + this.node.getBoundsInLocal()
                        .getWidth() / 2) - 1400 / 2) / LensMain.CONVERSIONFACTOR
                );
                
                
                this.setRelPos(LensPhysics.lensSearch().getAbsPos() - this.absPos);
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

    /**
     * Adjusts the point at which rays start when size is changed.
     */
    public void adjustRayPointHeight() {
        this.setRayPointHeight(350 - this.getSize() * 1.6666);
    }

    
    /**
     * Adjust node scaling according to object sizing.
     */
    public void scaleNodeToSize() {
        this.adjustRayPointHeight();
        this.node.setScaleX(this.size / 40);
        this.node.setScaleY(this.size / 40);

    }




}
