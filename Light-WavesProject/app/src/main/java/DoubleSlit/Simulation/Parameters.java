/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoubleSlit.Simulation;

/**
 *
 * @author 2156586
 */
public class Parameters {
    private double wavelength;
    private double width;
    private double screen;
    private double spacing;

    public Parameters(double wavelength, double width, double screen, double spacing) {
        this.wavelength = wavelength;
        this.width = width;
        this.screen = screen;
        this.spacing = spacing;
    }
    
    

    public double getWavelength() {
        return wavelength;
    }

    public void setWavelength(double wavelength) {
        this.wavelength = wavelength;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getScreen() {
        return screen;
    }

    public void setScreen(double screen) {
        this.screen = screen;
    }

    public double getSpacing() {
        return spacing;
    }

    public void setSpacing(double spacing) {
        this.spacing = spacing;
    }
    
    
}
