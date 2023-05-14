package edu.vanier.mainPackage.lens;


/**
 *
 * @author Steven
 */

public class LensPhysics {
    
    /**
     * Calculates the absolute position of a source given the position of an image relative to the lens.
     * @param q the relative position of the image to the lens
     * @return the absolute position of the source
     */
    public static double sourceAbsPosFromImageRelPos(double q){
        Lens lens = LensPhysics.lensSearch();
        return lens.absPos-(1 / ((1 / lens.getFocalLength()) - (1 / q)));
    }
    
    /**
     * Uses the lens maker equation, isolating focal length.
     * @param lens
     * @return focal length
     */
    public static double lensMakerToFocalLength(Lens lens){
        return 1/((2/lens.getRadius())*(lens.getRefractionIndex()-1));
    }
    
    /**
     * Uses the lens maker equation, isolating the radius.
     * Radius is the same for both edges of the lens.
     * @param lens
     * @return radius of curvature
     */
    public static double lensMakerToRadius(Lens lens){
        return 2*lens.getFocalLength()*(lens.getRefractionIndex()-1);
    }
    
    /**
     * Uses the lens maker equation, isolating the index of refraction.
     * @param lens
     * @return index of refraction
     */
    public static double lensMakerToIndex(Lens lens){
        return (lens.getRadius()/(2*lens.getFocalLength()))+1;
    }

    /**
     * Parses through the list of items to find the lens.
     * @return the lens 
     */
    public static Lens lensSearch() {
        for (int i = 1; i < Item.getItemList().size(); i++) {
            if (Item.getItemList().get(i).getItemType().equals(Item.ITEMTYPE_LENS)) {
                return (Lens) Item.getItemList().get(i);
            }
        }
        return null;
    }
    
    /**
     * Parses through the list of items to find the sourceObject.
     * @return the source
     */
    public static SourceObject sourceSearch(){
        for (int i = 1; i < Item.getItemList().size(); i++) {
            if (Item.getItemList().get(i).getItemType().equals(Item.ITEMTYPE_SOURCE)) {
                return (SourceObject) Item.getItemList().get(i);
            }
        }
        return null;
    }
    
    /***
     * Computes the relative positions of both the source and the image.
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

    /**
     * Computes the magnification of an image based on relative positions.
     * @param source
     * @return image magnification
     */
    public static double computeImageMag(Item source) {

        double[] relPositions = computeRelPos(source);
        return -relPositions[1]/relPositions[0];
        
    }

    /**
     * Computes image size based on image magnification and source size.
     * @param source
     * @return image size
     */
    public static double computeImageSize(Item source) {
        
        return source.getSize()*computeImageMag(source);
    }

    /**
     * Computes image absolute position from object position and lens focal length.
     * @param source
     * @return image absolute position
     */
    public static double computeImageAbsPos(Item source) {

        Lens lens = lensSearch();
        double f, p;

        p = lens.getAbsPos() - source.getAbsPos();
        f = lens.getFocalLength();

        return (1 / ((1 / f) - (1 / p)) + lens.absPos);

    }

}
