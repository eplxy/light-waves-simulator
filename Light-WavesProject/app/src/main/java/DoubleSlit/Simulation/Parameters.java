package DoubleSlit.Simulation;

/**
 *
 * @author sabri
 */
public class Parameters {
    private static double wavelength;
    private static double width;
    private static double screen;
    private static double spacing;

    public static double getWavelength() {
        return wavelength;
    }

    public static void setWavelength(double wavelength) {
        Parameters.wavelength = wavelength;
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        Parameters.width = width;
    }

    public static double getScreen() {
        return screen;
    }

    public static void setScreen(double screen) {
        Parameters.screen = screen;
    }

    public static double getSpacing() {
        return spacing;
    }

    public static void setSpacing(double spacing) {
        Parameters.spacing = spacing;
    } 
}
