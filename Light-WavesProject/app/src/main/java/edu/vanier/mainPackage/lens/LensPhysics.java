package edu.vanier.mainPackage.lens;


/**
 *
 * @author Steven
 */

public class LensPhysics {
    
    public static double sourceAbsPosFromImageRelPos(double q){
        Lens lens = LensPhysics.lensSearch();
        return lens.absPos-(1 / ((1 / lens.getFocalLength()) - (1 / q)));
    }
    
    public static double lensMakerToFocalLength(Lens lens){
        return 1/((2/lens.getRadius())*(lens.getRefractionIndex()-1));
    }
    public static double lensMakerToRadius(Lens lens){
        return 2*lens.getFocalLength()*(lens.getRefractionIndex()-1);
    }
    public static double lensMakerToIndex(Lens lens){
        return (lens.getRadius()/(2*lens.getFocalLength()))+1;
    }

    public static Lens lensSearch() {
        for (int i = 1; i < Item.getItemList().size(); i++) {
            if (Item.getItemList().get(i).getItemType().equals(Item.ITEMTYPE_LENS)) {
                return (Lens) Item.getItemList().get(i);
            }
        }
        return null;
    }
    
    public static SourceObject sourceSearch(){
        for (int i = 1; i < Item.getItemList().size(); i++) {
            if (Item.getItemList().get(i).getItemType().equals(Item.ITEMTYPE_SOURCE)) {
                return (SourceObject) Item.getItemList().get(i);
            }
        }
        return null;
    }
    
    /***
     * 
     * @param source
     * @return object position, image position
     */
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
