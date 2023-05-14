package edu.vanier.mainPackage.lens;

import java.util.SortedMap;
import java.util.TreeMap;
import javafx.scene.Node;
import lombok.Data;

/**
 *
 * @author Steven
 */
@Data
public abstract class Item {

    //properties
    public static final String ITEMTYPE_SOURCE = "Source";
    public static final String ITEMTYPE_LENS = "Lens";
    public static final String ITEMTYPE_IMAGE = "Image";

    private static LensMenuController lmc;
    private static SortedMap<Integer, Item> itemList = new TreeMap<>();

    protected double size;
    protected int orderNumber;
    protected double absPos, relPos;
    protected ItemLabel label;
    protected boolean positionLocked;
    protected Node node;

    protected String itemType;

    //methods
    
    /**
     * Adds an Item to the list of all items.
     * @param item 
     */
    public static void addToList(Item item) {

        item.orderNumber = itemList.size() + 1;
        Item.itemList.put(item.orderNumber, item);
    }

    /**
     * Adjusts position in case of a need to do so.
     */
    public void positionFix() {
        this.move(absPos);
    }

    /**
     * Moves an object, both visually and by adjusting the absolute position property.
     * @param absPos 
     */
    public void move(double absPos) {
        this.absPos = absPos;
        this.node.setLayoutX((700 + this.getAbsPos() * LensMain.CONVERSIONFACTOR - this.getNode().getLayoutBounds().getWidth() / 2));
        this.label.updateLabel();

    }

    /**
     * Adjusts vertical position of an object, notably when changing node images.
     */
    public void fixVerticalPosition() {
        this.getNode().setLayoutY(250 - this.getNode().getLayoutBounds().getHeight() / 2);
    }

    //getters and setters
    public static SortedMap<Integer, Item> getItemList() {
        return itemList;
    }

    public static void setItemList(SortedMap<Integer, Item> itemList) {
        Item.itemList = itemList;
    }
    @Override
    public String toString() {
        return Integer.toString(this.orderNumber) + " " + this.itemType;
    }


    public static LensMenuController getLmc() {
        return lmc;
    }

    public static void setLmc(LensMenuController lmc) {
        Item.lmc = lmc;
    }

}
