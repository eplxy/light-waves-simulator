/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoubleSlit.UI;

import DoubleSlit.Simulation.Graph;
import DoubleSlit.Simulation.Parameters;
import static java.util.EnumSet.range;
import static java.util.stream.IntStream.range;
import static java.util.stream.LongStream.range;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author sabri
 */
public class GraphController {
    Stage primaryStage;
    
    private Parameters parameters = null;
    
    @FXML
    RadioButton radioBtnInterference;
    @FXML
    RadioButton radioBtnDiffraction;
    @FXML
    RadioButton radioBtnBoth;
    @FXML
    LineChart graph;
    
    
    public GraphController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
    }
    public void initialize(){
        ToggleGroup graphTG = new ToggleGroup();
        radioBtnInterference.setToggleGroup(graphTG);
        radioBtnDiffraction.setToggleGroup(graphTG);
        radioBtnBoth.setToggleGroup(graphTG);
        graphTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                    Toggle o, Toggle n) {

                RadioButton rb = (RadioButton) graphTG.getSelectedToggle();

                if (rb != null) {
                    String s = rb.getText();
                    plotInterference();
                }
            }
        });
    }
    
    //https://lankydan.dev/2017/01/29/javafx-graphs-look-pretty-good
    public void plotInterference(){
        final XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
	for (double x = -100; x <= 100; x = x + 0.01) {
//		series.getData().add(new XYChart.Data<Double, Double>(x, Graph.diffraction(parameters, x)));
	}
	graph.getData().add(series);
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
    
    
}
