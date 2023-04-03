/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoubleSlit.Simulation;

import java.util.function.Function;
import javafx.scene.chart.XYChart;

/**
 *
 * @author 2156586
 */
public class Graph {
    public double diffraction(double angle){
        //double width = parameters.getWidth();
        //double wavelength = parameters.getWavelength();
        double width = 2;
        double wavelength = 450;
        return Math.pow((Math.sin((Math.PI*width*Math.pow(10, 3)*Math.sin(angle))/(wavelength)))/(Math.PI*width*Math.pow(10, 3)*Math.sin(angle)/wavelength), 2);
    }
    private XYChart<Double, Double> graph;
	private double range;

	public Graph(final XYChart<Double, Double> graph, final double range) {
		this.graph = graph;
		this.range = range;
	}

	public void plotLine(final Function<Double, Double> function) {
		final XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
		for (double x = -range; x <= range; x = x + 0.01) {
			plotPoint(x, function.apply(x), series);
		}
		graph.getData().add(series);
	}

	private void plotPoint(final double x, final double y,
			final XYChart.Series<Double, Double> series) {
		series.getData().add(new XYChart.Data<Double, Double>(x, y));
	}

	public void clear() {
		graph.getData().clear();
	}
}
