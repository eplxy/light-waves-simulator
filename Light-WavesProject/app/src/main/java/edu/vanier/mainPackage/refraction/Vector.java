package edu.vanier.mainPackage.refraction;
/**
 *
 * @author Matthew 
 */
public class Vector {
    
    static final double NAIR = 1;
    static final double NWATER = 1.33;
    static final double NGLASS = 1.52;
    
    public static void main(String[] args) {
        
        System.out.println(90 - Vector.CalculateAngle(NAIR, NWATER, 45));
    }
    
    /**
     * A utility method for calculating the angle of refraction using the given
     * refractive indices and angle of incidence.
     *
     * @param n1 the refractive index of the first medium
     * @param n2 the refractive index of the second medium
     * @param angle1 the angle of incidence in degrees
     * @return the angle of refraction in degrees
     */
    public static double CalculateAngle(double n1, double n2, double angle1){
        // Converts the angle of incidence from degrees to radians
        angle1 = Math.toRadians(angle1);
        // Calculates the angle of refraction using Snell's law
        double x = (n1*Math.sin(angle1))/n2;
        double Angle2 = Math.asin(x);
        // Converts the angle of refraction from radians to degrees
        return Math.toDegrees(Angle2);
    }
    
}
