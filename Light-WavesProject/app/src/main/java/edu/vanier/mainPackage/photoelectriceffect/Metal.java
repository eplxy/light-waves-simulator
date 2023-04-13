/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage.photoelectriceffect;

/**
 *
 * @author maesh
 */
public class Metal {
    String metalName;
    double workFunction;

    public Metal(String metalName, double workFunction) {
        this.metalName = metalName;
        this.workFunction = workFunction;
    }

    public String getMetalName() {
        return metalName;
    }

    public void setMetalName(String metalName) {
        this.metalName = metalName;
    }

    public double getWorkFunction() {
        return workFunction;
    }

    public void setWorkFunction(double workFunction) {
        this.workFunction = workFunction;
    }
}