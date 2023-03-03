package edu.vanier.mainPackage.lens;

import java.util.ArrayList;
import java.util.SortedMap;

/**
 *
 * @author Steven
 */
public abstract class Item {

    //properties
    private static SortedMap<Integer, Item> itemList;
    
    protected int orderNumber;
    protected double absPos, relPos;
    protected ItemLabel label;
    protected boolean positionLocked;
    
    /***
     * Value represents the type of item based on a legend:
     * 1 - SourceObject
     * 2 - Lens
     * 3 - Image
     * 4 - Image used as source object
     */
    protected int itemType;
    
    
    //methods
    public static void addToList(Item item){
        for (int i = item.orderNumber; i < itemList.size(); i++) {
            itemList.get(i).setOrderNumber(itemList.get(i).orderNumber+1);
        }
        Item.itemList.put(item.orderNumber,item);
    }
    
    public static void removeFromList(Item item){
        
    }
    
    private static void updateListOrder(){
        for (int i = 0; i < itemList.size(); i++) {
            for (int j = i; j < itemList.size(); j++) {
                
            }
        }
    }
    
    public void lockPosition() {
    }
    
    public void move(){
        
    }
    
    //getters and setters

    public static SortedMap<Integer,Item> getItemList() {
        return itemList;
    }

    public static void setItemList(SortedMap<Integer,Item> itemList) {
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

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
    
    

    @Override
    public String toString() {
        return Integer.toString(this.orderNumber);
    }
    
    
    
    
}
