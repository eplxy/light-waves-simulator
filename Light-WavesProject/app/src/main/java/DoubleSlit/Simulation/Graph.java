/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoubleSlit.Simulation;

/**
 *
 * @author 2156586
 */
public class Graph {
    public static double diffraction(Parameters parameters, double angle){
        double width = parameters.getWidth();
        double wavelength = parameters.getWavelength();
        return Math.pow((Math.sin((Math.PI*width*Math.pow(10, 3)*Math.sin(angle))/(wavelength)))/(Math.PI*width*Math.pow(10, 3)*Math.sin(angle)/wavelength), 2);
    }
}
