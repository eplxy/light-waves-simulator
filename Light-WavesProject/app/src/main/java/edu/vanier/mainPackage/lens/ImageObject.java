package edu.vanier.mainPackage.lens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Data;

/**
 *
 * @author Steven
 */
@Data
public class ImageObject extends Item {

    //properties
    private double magnification;
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

    public void update(){
        updatePosition();
        updateSize();
    }
    
    public void updatePosition() {
        this.move(LensPhysics.computeImageAbsPos(source));
        if (this.node.getLayoutX()>this.node.getParent().getBoundsInParent().getWidth()
                ||this.node.getLayoutX()<0){
            //TODO implement drag limits to avoid pane extending.
        }
    }

    private void updateSize(){
        this.size = LensPhysics.computeImageSize(source);
        System.out.println(this.size);
        scaleNodeToSize();
    }
    
    private void scaleNodeToSize() {
        this.node.setScaleX(this.size / 40);
        this.node.setScaleY(this.size / 40);
    }

    //getters and setters

    //public double getMagnification() {
    //    return magnification;
    //}

    //public void setMagnification(double magnification) {
    //    this.magnification = magnification;
    //}

    //public String getType() {
    //    return type;
    //}

    //public void setType(String type) {
    //    this.type = type;
    //}

    //public boolean isInverted() {
    //    return inverted;
    //}

    //public void setInverted(boolean inverted) {
    //    this.inverted = inverted;
    //}

    //public double getDistToFocal() {
    //    return distToFocal;
    //}

    //public void setDistToFocal(double distToFocal) {
    //    this.distToFocal = distToFocal;
    //}

    //public Item getSource() {
    //    return source;
    //}

    //public void setSource(Item source) {
    //    this.source = source;
    //}
    
    

}
