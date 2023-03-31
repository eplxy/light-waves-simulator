package edu.vanier.mainPackage.lens;

import java.util.ArrayList;

/**
 *
 * @author Steven
 */
public class LensPhysics {
    
    public static double computeImagePosition(Item source){
        
        Lens lens = null;
        double f, p;
        
        for (int i = 0; i < Item.getItemList().size(); i++) {
            if (Item.getItemList().get(i).getItemType().equals("lens")){
                lens = (Lens) Item.getItemList().get(i);
                break;
            }
        }
        
        p = lens.getAbsPos()-source.getAbsPos();
        f = lens.getFocalLength();
        
        return 1/((1/f)-(1/p));
    }
    
}
