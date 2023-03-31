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
        this.itemType = "source";
        this.absPos = absPos;
        this.size = size;
        this.node = new ImageView(new Image(getClass().getResource("/images/lens/candle.png").toString()));
        this.image = new ImageObject(this);
        scaleNodeToSize();
        setDragListeners();
    }

    //methods
    private void setDragListeners() {
        node.setOnMousePressed((mouseEvent) -> {
            mouseAnchorX = mouseEvent.getX();

        });

        node.setOnMouseDragged((mouseEvent) -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            this.setAbsPos(((this.node.getLayoutX() + this.node.getBoundsInLocal()
                    .getWidth() / 2) - this.node.getParent().getParent()
                            .getBoundsInLocal().getWidth() / 2)/20);
            this.image.update();
            System.out.println("obj pos " + this.absPos + " img pos " + this.image.absPos);
            System.out.println("width " + this.node.getParent().getParent().getBoundsInLocal().getWidth());
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
