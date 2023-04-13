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
        double energy;
        energy = plankConstant * (speedOfLight/wavelength) - workFunction;
        return energy;
    }
    
}
