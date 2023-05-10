package edu.vanier.mainPackage.lens;

import java.util.ArrayList;
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
    public static void addToList(Item item) {

        //TODO solve order number issue
//        for (int i = item.orderNumber; i < itemList.size(); i++) {
//            itemList.get(i).setOrderNumber(itemList.get(i).orderNumber + 1);
//        }
        item.orderNumber = itemList.size() + 1;
        Item.itemList.put(item.orderNumber, item);
    }

    public static void removeFromList(Item item) {

    }

    private static void updateListOrder() {
        for (int i = 0; i < itemList.size(); i++) {
            for (int j = i; j < itemList.size(); j++) {

            }
        }
    }

    public void lockPosition() {
    }

    public void positionFix() {
        this.move(absPos);
    }

    public void move(double absPos) {
        this.absPos = absPos;
        //this.node.setLayoutX((this.getNode().getParent().getParent().getBoundsInParent().getWidth() / 2 + this.getAbsPos() * 20 - this.getNode().getBoundsInParent().getWidth() / 2));
        this.node.setLayoutX((1400/ 2 + this.getAbsPos() * LensMain.CONVERSIONFACTOR - this.getNode().getBoundsInParent().getWidth() / 2));
        //this.node.setLayoutX((1400/ 2 + this.getAbsPos() * 20));
        this.label.updateLabel();
        
    }

    //getters and setters
    public static SortedMap<Integer, Item> getItemList() {
        return itemList;
    }

    public static void setItemList(SortedMap<Integer, Item> itemList) {
        Item.itemList = itemList;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getAbsPos() {
        return absPos;
    }

    public void setAbsPos(double absPos) {
        this.absPos = absPos;
    }

    public double getRelPos() {
        return relPos;
    }

    public void setRelPos(double relPos) {
        this.relPos = relPos;
    }

    public ItemLabel getLabel() {
        return label;
    }

    public void setLabel(ItemLabel label) {
        this.label = label;
    }

    public boolean isPositionLocked() {
        return positionLocked;
    }

    public void setPositionLocked(boolean positionLocked) {
        this.positionLocked = positionLocked;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return Integer.toString(this.orderNumber) + " " + this.itemType;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public static LensMenuController getLmc() {
        return lmc;
    }

    public static void setLmc(LensMenuController lmc) {
        Item.lmc = lmc;
    }
    
   

}
