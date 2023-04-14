package edu.vanier.mainPackage.lens;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import lombok.Data;

/**
 *
 * @author Steven
 */
@Data
public class ItemLabel {

    //decimal format
    private static DecimalFormat df = new DecimalFormat("######.##");

    //properties
    private static boolean visible;
    private static LensMenuController lmc;
    private FlowPane labelContainer;
    private Label labAbsPos, labRelPos, labSize, labMagnification, labFocalLength, labInversion, labImgType;
    private static ArrayList<ItemLabel> itemLabelList = new ArrayList<ItemLabel>();
    private ArrayList<Label> activeLabelList;
    private Item item;
    private String itemType;

    //constructor(s)
    public ItemLabel(Item item) {
        this.instantiateLabels();
        this.item = item;
        this.itemType = item.getItemType();
        activeLabelList = new ArrayList<Label>();
        activeLabelList.add(labAbsPos);
        activeLabelList.add(labRelPos);
        if (this.itemType.equals(Item.ITEMTYPE_SOURCE)) {
            activeLabelList.add(labSize);
        } else if (this.itemType.equals(Item.ITEMTYPE_LENS)) {
            activeLabelList.add(labFocalLength);
        } else if (this.itemType.equals(Item.ITEMTYPE_IMAGE)) {
            activeLabelList.add(labSize);
            activeLabelList.add(labMagnification);
            activeLabelList.add(labInversion);
            activeLabelList.add(labImgType);
        }
        itemLabelList.add(this);
        createLabel();
    }

    //methods
    public void instantiateLabels() {
        labelContainer = new FlowPane();
        labelContainer.setMaxWidth(100);
        labelContainer.setPadding(new Insets(10));
        labelContainer.setAlignment(Pos.CENTER);

        labAbsPos = new Label();
        labRelPos = new Label();
        labSize = new Label();
        labFocalLength = new Label();
        labMagnification = new Label();
        labInversion = new Label();
        labImgType = new Label();
    }

    public static void toggleLabelVisibility() {
        visible = !visible;
        for (ItemLabel itmLab : itemLabelList) {
            itmLab.getLabelContainer().setVisible(visible);
        }

    }

    public void updateLabel() {
        for (Label label : activeLabelList) {
            if (label.equals(labAbsPos)) {
                label.setText("AbsPos: " + df.format(item.getAbsPos()));
            }
            if (label.equals(labRelPos)) {
                label.setText("RelPos: " + df.format(item.getRelPos()));
            }
            if (label.equals(labSize)) {
                label.setText("Size: " + df.format(item.getSize()));
            }
            if (label.equals(labMagnification)) {
                label.setText("Magnification: " + df.format(((ImageObject) item).getMagnification()));
            }
            if (label.equals(labFocalLength)) {
                label.setText("FocalLength: " + df.format(((Lens) item).getFocalLength()));
            }
            if (label.equals(labInversion)) {
                label.setText("Inverted: " + (((ImageObject) item).isInverted()));
            }
            if (label.equals(labImgType)) {
                label.setText("ImgType: " + (((ImageObject) item).getImgType()));
            }
        }
        positionItemLabel();
    }

    public void createLabel() {
        for (Label lab : activeLabelList) {
            labelContainer.getChildren().add(lab);
        }
        lmc.animationPane.getChildren().add(labelContainer);
        updateLabel();
    }

    public void positionItemLabel() {
        labelContainer.setLayoutX(item.getNode().getLayoutX() - 25);
        labelContainer.setLayoutY(500);
    }

    //getters and setters
    public static void setLmc(LensMenuController lmc) {
        ItemLabel.lmc = lmc;
    }

}
