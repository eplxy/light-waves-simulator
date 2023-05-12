package edu.vanier.mainPackage.photoelectriceffect;

import javafx.scene.paint.Color;

/**
 *
 * @author maesh
 */
public class Photon {
    private static final double plankConstant = 6.62607015e-34;
    private static final double speedOfLight = 299792458;
    private static final double convertionJoulesToElectronVolt = 1.602e-19;
    public double energyMinimum;
    
    /**
     * this metal is used to calculate the minimum energy of the photoelectron. 
     * It takes in account of the work function of the metal and substracts from 
     * the energy of the photon to calculate the minimum energy required by the 
     * photon to makes the electrons eject from the metal
     * @param wavelength
     * @param metalName
     * @return 
     */
    
    public double photonMinuimumEnergy(double wavelength, String metalName){
        Metal metal = new Metal();
        double workFunction = metal.getWorkFunction(metalName);
        wavelength = wavelength * 1e-9;
        energyMinimum = (((plankConstant * speedOfLight)/wavelength)/convertionJoulesToElectronVolt) - workFunction;
        return energyMinimum;
    }
    
    /**
     * this is used to change the colour of the light as the user chooses the 
     * wavelength that they wanna use for the animation. it uses rgb ratio as 
     * well as a the wavelength to create a gradiant affect, i also added a hue
     * affect so it looks more like a light
     * @param wavelength
     * @return 
     */
    
    public Color getColorForWavelength(double wavelength) {
        if (wavelength < 300) {
        return Color.TRANSPARENT; 
        }
        double red, green, blue;
        if (wavelength >= 380 && wavelength <= 439) {
        red = -(wavelength - 440) / (440 - 380);
        green = 0.0;
        blue = 1.0;
        }
        else if (wavelength >= 440 && wavelength <= 489) {
        red = 0.0;
        green = (wavelength - 440) / (490 - 440);
        blue = 1.0;
        } 
        else if (wavelength >= 490 && wavelength <= 509) {
        red = 0.0;
        green = 1.0;
        blue = -(wavelength - 510) / (510 - 490);
        } 
        else if (wavelength >= 510 && wavelength <= 579) {
        red = (wavelength - 510) / (580 - 510);
        green = 1.0;
        blue = 0.0;
        } 
        else if (wavelength >= 580 && wavelength <= 644) {
        red = 1.0;
        green = -(wavelength - 645) / (645 - 580);
        blue = 0.0;
        } 
        else if (wavelength >= 645 && wavelength <= 780) {
        red = 1.0;
        green = 0.0;
        blue = 0.0;
        } 
        else {
        return Color.TRANSPARENT;
        }

    red = Math.min(Math.max(0, red * 255), 255);
    green = Math.min(Math.max(0, green * 255), 255);
    blue = Math.min(Math.max(0, blue * 255), 255);

    return Color.rgb((int) red, (int) green, (int) blue);
   }
}