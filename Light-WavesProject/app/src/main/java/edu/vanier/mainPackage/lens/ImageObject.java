package edu.vanier.mainPackage.lens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Steven
 */
@Data
@EqualsAndHashCode(callSuper = false)
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
        this.node = new ImageView(new Image(getClass().getResource("/images/lens/candle.png").toString()));
        this.node.setOpacity(0.5);
        this.label = new ItemLabel(this);

    }

    //methods
    public void update() {
        updateSize();
        updatePosition();
        updateImgType();
    }

    public void updatePosition() {

//        double destination = (this.getNode().getParent().getParent()
//                .getBoundsInParent().getWidth() / 2 + LensPhysics
//                        .computeImageAbsPos(source) * 20 - this.getNode()
//                .getBoundsInParent().getWidth() / 2);
//        double destination = (1400/ 2 + LensPhysics
//                        .computeImageAbsPos(source) * 20 - this.getNode()
//                .getBoundsInParent().getWidth() / 2);
//
//        if (destination > this.node.getParent().getBoundsInParent().getWidth()-this.node.getBoundsInLocal().getWidth()
//                || destination < 0 ) {
//            this.node.setVisible(false);
//        } else {
//            this.node.setVisible(true);
//        }
        this.move(LensPhysics.computeImageAbsPos(source));

    }

    private void updateSize() {
        this.size = LensPhysics.computeImageSize(source);
        this.magnification = LensPhysics.computeImageMag(source);
        scaleNodeToSize();
    }

    private void updateImgType() {
        if (this.absPos > LensPhysics.lensSearch().getAbsPos()) {
            this.imgType = "real";
        }
        if (this.absPos < LensPhysics.lensSearch().getAbsPos()){
            this.imgType = "virtual";
        }
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
