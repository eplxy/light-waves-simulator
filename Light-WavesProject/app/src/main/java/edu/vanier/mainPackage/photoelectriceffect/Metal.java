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
    private String metalName;
    private double workFunction;
    
    private HashMap<String, Double> metalWorkFunction;
    
    public Metal(){
        metalWorkFunction = new HashMap<>();
        metalWorkFunction.put("Magnesium", 3.7);
        metalWorkFunction.put("Aluminium", 4.28);
        metalWorkFunction.put("Calcium", 3.0);
        metalWorkFunction.put("Copper", 5.0);
        metalWorkFunction.put("Gold", 5.1);
    }
    
    public double getWorkFunction(String metalName){
        return metalWorkFunction.get(metalName);
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