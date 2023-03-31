package edu.vanier.mainPackage.lens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 *
 * @author Steven
 */
public class Lens extends Item {

    //properties
    private double focalLength, refractionIndex;
    private String lensType;
    private FocalPoint focalPoint;

    //constructor(s)
    public Lens(double FocalLength, double absPos) {
        this.itemType = "lens";
        this.node = new ImageView(new Image(getClass().getResource("/images/lens/lens.png").toString()));
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
