/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoubleSlit.Simulation;

import java.util.function.Function;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author 2156586
 */
public class Graph {
    @FXML
    public LineChart<Double, Double> graph;
        protected String selectedGraph;

      public Function<Double, Double> plotDiffraction() {
        final double width = Parameters.getWidth();
        final double wavelength = Parameters.getWavelength();
        return x -> (Math.pow((Math.sin((Math.PI * width * Math.pow(10, 3) * Math.sin(x)) / (wavelength))) / (Math.PI * width * Math.pow(10, 3) * Math.sin(x) / wavelength), 2));
    }

    public Function<Double, Double> plotInterference() {
        final double spacing = Parameters.getSpacing();
        final double wavelength = Parameters.getWavelength();
        return x -> (Math.pow((Math.cos((Math.PI * spacing * Math.pow(10, 3) * Math.sin(x)) / (wavelength))), 2));
    }

    public Function<Double, Double> plotBoth() {
        return x -> plotDiffraction().apply(x) * plotInterference().apply(x);
    }

    public void plotLine() {
        final Function<Double, Double> function;
        if (selectedGraph.equals("Diffraction")){
            function = plotDiffraction();
        }else if (selectedGraph.equals("Interference")){
            function = plotInterference();
        }else{
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
