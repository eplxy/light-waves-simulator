package edu.vanier.mainPackage.photoelectriceffect;

/**
 *
 * @author maesh
 */
public class Photon {
    private static final double plankConstant = 6.62607015e-34;
    private static final double speedOfLight = 299792458;
    private static final double convertionJoulesToElectronVolt = 1.602e-19;
    
    public double photonMinuimumEnergy(double wavelength, String metalName){
        Metal metal = new Metal();
        
        double workFunction = metal.getWorkFunction(metalName);
        wavelength = wavelength * 1e-9;
        
        double energyMinimum = (((plankConstant * speedOfLight)/wavelength)/convertionJoulesToElectronVolt) - workFunction;
        /*not only does it calculate the energy of the photon, it removed the 
        * work function so it give the minimum energy required of a photon
        * to make an electron ejected from the metal plate. Therefore,
        * energyPhoton > workFunction or eneryMin > 0 [same thing]
        */
        return energyMinimum;
    }
    
}