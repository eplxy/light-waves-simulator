package edu.vanier.mainPackage.lens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Steven
 */
@Data @EqualsAndHashCode(callSuper=false)
public class Lens extends Item {

    //properties
    static double mouseAnchorX;
    
    private double focalLength, refractionIndex;
    private String lensType;
    private FocalPoint focalPoint;

    //constructor(s)
    public Lens(double focalLength, double absPos) {
        this.itemType = "lens";
        this.focalLength = focalLength;
        this.node = new ImageView(new Image(getClass().getResource("/images/lens/lens.png").toString()));
        setDragListeners();
        this.label = new ItemLabel(this);
    }

    public Lens(double focalLength, double refractionIndex, FocalPoint focalPoint) {
        this.itemType = "lens";
        this.focalLength = focalLength;
        this.refractionIndex = refractionIndex;
        this.focalPoint = focalPoint;

    }

    public String determineType() {
        if (this.focalLength > 0) {
            return "convergent";
        } else if (this.focalLength < 0) {
            return "divergent";
        } else {
            return "flat";
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
                    .getWidth() / 2) - 1400 / 2)/30);
            this.label.updateLabel();
            LensPhysics.sourceSearch().getImage().update();
            
        });
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

}
