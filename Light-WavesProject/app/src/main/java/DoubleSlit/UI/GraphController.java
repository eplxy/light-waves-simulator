/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoubleSlit.UI;

import DoubleSlit.Simulation.Parameters;
import java.io.IOException;
import static java.util.EnumSet.range;
import java.util.function.Function;
import static java.util.stream.IntStream.range;
import static java.util.stream.LongStream.range;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author sabri
 */
public class GraphController{

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


    public GraphController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

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

    //https://lankydan.dev/2017/01/29/javafx-graphs-look-pretty-good

    public String getSelectedGraph() {
        return selectedGraph;
    }

    public void setSelectedGraph(String selectedGraph) {
        this.selectedGraph = selectedGraph;
    }
   
}
