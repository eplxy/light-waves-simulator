/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoubleSlit.UI;

import javafx.scene.chart.LineChart;
import DoubleSlit.Simulation.Graph;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

/**
 *
 * @author 2156586
 */
public class TestGraphController {
    Stage stage;


    @FXML
    LineChart<Double, Double> testGraph;
    
    private Graph graph;

    public TestGraphController(Stage stage) { System.out.println("!");
        this.stage = stage;
        this.graph = new Graph(testGraph, 100);
        //graph.plotLine(x -> Math.pow((Math.sin((Math.PI * 2 * Math.pow(10, 3) * Math.sin(x)) / (450))) / (Math.PI * 2 * Math.pow(10, 3) * Math.sin(x) / 450), 2));
       this.graph.plotLine(x -> x);
       
    
    }

    public void initialize() {
    }

}
