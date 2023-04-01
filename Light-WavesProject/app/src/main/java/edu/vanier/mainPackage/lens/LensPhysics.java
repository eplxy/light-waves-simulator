package edu.vanier.mainPackage.lens;

import java.util.ArrayList;

/**
 *
 * @author Steven
 */

public class LensPhysics {

    public static Lens lensSearch() {
        for (int i = 1; i < Item.getItemList().size(); i++) {
            if (Item.getItemList().get(i).getItemType().equals("lens")) {
                return (Lens) Item.getItemList().get(i);
            }
        }
        return null;
    }
    
    public static SourceObject sourceSearch(){
        for (int i = 1; i < Item.getItemList().size(); i++) {
            if (Item.getItemList().get(i).getItemType().equals("source")) {
                return (SourceObject) Item.getItemList().get(i);
            }
        }
        return null;
    }
    
    public static double[] computeRelPos(Item source) {

        Lens lens = lensSearch();
        double f, p, q;

        p = lens.getAbsPos() - source.getAbsPos();
        f = lens.getFocalLength();
        q = (1 / ((1 / f) - (1 / p)));

        return new double[]{p,q};
    }

    public static double computeImageMag(Item source) {

        double[] relPositions = computeRelPos(source);
        return -relPositions[1]/relPositions[0];
        
    }

    public static double computeImageSize(Item source) {
        
        return source.getSize()*computeImageMag(source);
    }

    public static double computeImageAbsPos(Item source) {

        Lens lens = lensSearch();
        double f, p;

        p = lens.getAbsPos() - source.getAbsPos();
        f = lens.getFocalLength();

        return (1 / ((1 / f) - (1 / p)) + lens.absPos);

    }

}
