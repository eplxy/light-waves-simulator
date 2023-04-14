/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage.photoelectriceffect;

/**
 *
 * @author maesh
 */
public class Photon {
    double plankConstant = 6.62607015e-34;
    double wavelength;
    double workFunction;
    double speedOfLight = 299792458;
    
    public double photonEnergy(){
        double energyMin;
        energyMin = plankConstant * (speedOfLight/wavelength) - workFunction;
        /*not only does it calculate the energy of the photon, it removed the 
        * work function so it give the minimum energy required of a photon
        * to make an electron ejected from the metal plate. Therefore,
        * energyPhoton > workFunction or eneryMin > 0 [same thing]
        */
        return energyMin;
    }
    
}
