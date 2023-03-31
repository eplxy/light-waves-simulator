package edu.vanier.mainPackage.lens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Steven
 */
public class ImageObject extends Item {

    //properties
    private double size, magnification;
    private double distToFocal;
    private String type;
    private Item source;
    private boolean inverted;

    //constructor(s)
    public ImageObject(SourceObject source) {
        this.itemType = "image";
        this.source = source;
        this.node = new ImageView(new Image(getClass().getResource("/images/lens/candle.png").toString()));
        this.node.setOpacity(0.5);

    }

    public ImageObject(double size, double magnification, String type, boolean inverted) {
        this.size = size;
        this.magnification = magnification;
        this.type = type;
        this.inverted = inverted;
    }

    //methods
    public void updatePosition() {
        this.setRelPos(LensPhysics.computeImagePosition(this.source));
        this.move(this.source.absPos + relPos);
    }

    private void scaleNodeToSize() {
        this.node.setScaleX(this.size / 40);
        this.node.setScaleY(this.size / 40);
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

    public double getDistToFocal() {
        return distToFocal;
    }

    public void setDistToFocal(double distToFocal) {
        this.distToFocal = distToFocal;
    }

    public Item getSource() {
        return source;
    }

    public void setSource(Item source) {
        this.source = source;
    }
    
    

}
