/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoubleSlit.UI;

import javafx.scene.chart.LineChart;
import DoubleSlit.Simulation.Graph;
import java.io.IOException;
import java.util.function.Function;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 *
 * @author 2156586
 */
public class TestGraphController {
    Stage stage;


    @FXML
    LineChart<Double, Double> testGraph;
    
    @FXML
    Button btn;
   

    public TestGraphController(Stage stage) { 
        this.stage = stage;
        
    }

    public void initialize() throws IOException {
        btn.setOnAction((event) -> {
            handlebtn(event);
        });
    }
    
     public void handlebtn(ActionEvent event) {
        XYChart.Series series = new XYChart.Series(); 
      series.setName("No of schools in an year"); 
        
//      series.getData().add(new XYChart.Data(1, 15)); 
//      series.getData().add(new XYChart.Data(2, 30)); 
//      series.getData().add(new XYChart.Data(3, 60)); 

    plotLine(interference(4, 750), testGraph);
            
      //Setting the data to Line chart    
      testGraph.getData().add(series);        
    }
     
    public Function<Double, Double> diffraction(double width, double wavelength){
//        //double width = parameters.getWidth();
//        //double wavelength = parameters.getWavelength();
//        double width = 2;
//        double wavelength = 450;
       return x -> (Math.pow((Math.sin((Math.PI*width*Math.pow(10, 3)*Math.sin(x))/(wavelength)))/(Math.PI*width*Math.pow(10, 3)*Math.sin(x)/wavelength), 2));
    }
    
    public Function<Double, Double> interference(double spacing, double wavelength){
//        //double width = parameters.getWidth();
//        //double wavelength = parameters.getWavelength();
//        double width = 2;
//        double wavelength = 450;
       return x -> (Math.pow((Math.cos((Math.PI*spacing*Math.pow(10, 3)*Math.sin(x))/(wavelength))), 2));
    }

    public void plotLine(final Function<Double, Double> function, LineChart<Double, Double> testGraph) {
		final XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
		for (double x = (-Math.PI/2); x <= Math.PI/2; x = x + 0.001) {
			plotPoint(x, function.apply(x), series);
		}
		testGraph.getData().add(series);
                
    }
    private void plotPoint(final double x, final double y,
			final XYChart.Series<Double, Double> series) {
		series.getData().add(new XYChart.Data<Double, Double>(x, y));
	}
}

