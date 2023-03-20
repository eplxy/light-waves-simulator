/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage.refraction;

/**
 *
 * @author 2148289
 */
public class Vector {
    
    static final double NAIR = 1;
    static final double NWATER = 1.33;
    static final double NGLASS = 1.52;
    
    public static void main(String[] args) {
        
        System.out.println(Vector.CalculateAngle(NAIR, NWATER, 45));
    }
    
    public static double CalculateAngle(double n1, double n2, double Angle1){
        Angle1 = Math.toRadians(Angle1);
        double x = (n1*Math.sin(Angle1))/n2;
        System.out.println(x);
        double Angle2 = Math.asin(x);
        return Math.toDegrees(Angle2);
    }
    
}
