package edu.vanier.mainPackage.lens;

/**
 *
 * @author Steven
 */
public class Image extends Item {

    //properties
    private double size, magnification;
    private String type;
    private Item source;
    private boolean inverted;

    //constructor(s)
    public Image() {
    }

    public Image(double size, double magnification, String type, boolean inverted) {
        this.size = size;
        this.magnification = magnification;
        this.type = type;
        this.inverted = inverted;
    }

    //methods
    public void updatePosition(){
        this.setRelPos(LensPhysics.computeImagePosition(this.source));
    }
    
    //getters and setters

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getMagnification() {
        return magnification;
    }

    public void setMagnification(double magnification) {
        this.magnification = magnification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    
}
