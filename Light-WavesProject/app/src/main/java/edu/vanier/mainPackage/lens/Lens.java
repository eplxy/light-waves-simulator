package edu.vanier.mainPackage.lens;

/**
 *
 * @author Steven
 */
public class Lens extends Item {

    //properties
    private double focalLength, refractionIndex;
    private String type;
    private FocalPoint focalPoint;

    //constructor(s)
    public Lens() {
    }

    public Lens(double focalLength, double refractionIndex, String type, FocalPoint focalPoint) {
        this.focalLength = focalLength;
        this.refractionIndex = refractionIndex;
        this.type = type;
        this.focalPoint = focalPoint;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FocalPoint getFocalPoint() {
        return focalPoint;
    }

    public void setFocalPoint(FocalPoint focalPoint) {
        this.focalPoint = focalPoint;
    }

}
