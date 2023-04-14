package edu.vanier.mainPackage.lens;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Steven
 */
public class SourceObject extends Item {

    //properties
    static double mouseAnchorX;

    private boolean fromImage;
    private ImageObject image;

    //constructor(s)
    public SourceObject(double size, double absPos) {
        this.itemType = Item.ITEMTYPE_SOURCE;
        this.absPos = absPos;
        this.size = size;
        this.node = new ImageView(new Image(getClass().getResource("/images/lens/candle.png").toString()));
        this.image = new ImageObject(this);
        scaleNodeToSize();
        setDragListeners();
        this.label = new ItemLabel(this);
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
            this.image.update();
        });
    }

    private void scaleNodeToSize() {
        this.node.setScaleX(this.size / 40);
        this.node.setScaleY(this.size / 40);
    }

    //getters and setters

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
