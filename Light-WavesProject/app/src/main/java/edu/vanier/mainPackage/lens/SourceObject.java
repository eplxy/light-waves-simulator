package edu.vanier.mainPackage.lens;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 *
 * @author Steven
 */
public class SourceObject extends Item {

    //properties
    private double size;
    private boolean fromImage;
    private ImageObject image;

    //constructor(s)
    public SourceObject(double size, double absPos) {
        this.itemType = "source";
        this.absPos = absPos;
        this.size = size;
        this.node = new ImageView(new Image(getClass().getResource("/images/lens/candle.png").toString()));
        this.image = new ImageObject(this);
        scaleNodeToSize();

    }

    //methods
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

}
