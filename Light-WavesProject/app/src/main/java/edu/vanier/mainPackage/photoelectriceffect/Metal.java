/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage.photoelectriceffect;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author maesh
 */
public class Metal {
    String metalName;
    double workFunction;
    private static final Map<String, Double> WORK_FUNCTIONS = new HashMap<>();
    
    static{
        WORK_FUNCTIONS.put("Aluminum", 4.08);
        WORK_FUNCTIONS.put("Copper", 4.7);
        WORK_FUNCTIONS.put("Gold", 5.1);
    }
    
    public static double getWorkFunction(String metalName){
        return WORK_FUNCTIONS.get(metalName);
    }

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