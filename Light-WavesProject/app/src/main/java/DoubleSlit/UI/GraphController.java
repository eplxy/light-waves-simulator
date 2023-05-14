package DoubleSlit.UI;

import DoubleSlit.Simulation.Parameters;
import java.util.function.Function;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Sabrina Amoura
 * basis of the code for the graphs is from:
 * https://lankydan.dev/2017/01/29/javafx-graphs-look-pretty-good
 */
public class GraphController {
    Stage primaryStage;

    @FXML
    RadioButton radioBtnInterference;

    @FXML
    RadioButton radioBtnDiffraction;

    @FXML
    RadioButton radioBtnBoth;

    @FXML
    public LineChart<Double, Double> graph;

    private String selectedGraph = "Diffraction";

    /**
     * Sole constructor.
     * @param primaryStage 
     */
    public GraphController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Sets up the radio buttons for the graph options.
     */
    public void initialize() {
        ToggleGroup graphTG = new ToggleGroup();
        radioBtnInterference.setToggleGroup(graphTG);
        radioBtnDiffraction.setToggleGroup(graphTG);
        radioBtnBoth.setToggleGroup(graphTG);
        radioBtnDiffraction.setSelected(true);
        graphTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                    Toggle o, Toggle n) {
                RadioButton rb = (RadioButton) graphTG.getSelectedToggle();
                graph.getData().clear();
                if (rb != null) {
                    selectedGraph = rb.getText();
                    plotLine();
                }
            }
        });
    }

    /**
     * Creates the function to plot the diffraction graph.
     * @return the created function
     */
    public Function<Double, Double> plotDiffraction() {
        final double width = Parameters.getWidth();
        final double wavelength = Parameters.getWavelength();
        return x -> (Math.pow((Math.sin((Math.PI * width * Math.pow(10, 3) *
                Math.sin(x)) / (wavelength))) / (Math.PI * width *
                        Math.pow(10, 3) * Math.sin(x) / wavelength), 2));
    }

    /**
     * Creates the function to plot the Interference graph.
     * @return the created function
     */
    public Function<Double, Double> plotInterference() {
        final double spacing = Parameters.getSpacing();
        final double wavelength = Parameters.getWavelength();
        return x -> (Math.pow((Math.cos((Math.PI * spacing * Math.pow(10, 3) *
                Math.sin(x)) / (wavelength))), 2));
    }

    /**
     * Creates the function to plot the graph with both interference and diffraction.
     * @return the created function
     */
    public Function<Double, Double> plotBoth() {
        return x -> plotDiffraction().apply(x) * plotInterference().apply(x);
    }

    /**
     * Plots the line according to the selected graph function.
     */
    public void plotLine() {
        graph.getData().clear();
        final Function<Double, Double> function;
        if (selectedGraph.equals("Diffraction")) {
            function = plotDiffraction();
        } else if (selectedGraph.equals("Interference")) {
            function = plotInterference();
        } else {
            function = plotBoth();
        }
        final XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
        for (double x = (-Math.PI / 2); x <= Math.PI / 2; x = x + 0.001) {
            plotPoint(x, function.apply(x), series);
        }
        graph.getData().add(series);
    }

    private void plotPoint(final double x, final double y,
            final XYChart.Series<Double, Double> series) {
        series.getData().add(new XYChart.Data<Double, Double>(x, y));
    }
}
