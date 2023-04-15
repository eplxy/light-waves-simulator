package edu.vanier.mainPackage.lens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @Override
    public String toString() {
        return Integer.toString(this.orderNumber) + " " + this.itemType;
    }
}