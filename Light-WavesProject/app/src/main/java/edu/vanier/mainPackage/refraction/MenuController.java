package edu.vanier.mainPackage.refraction;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Matthew Hantar
 */
public class MenuController {
    
    @FXML
    Button btnMainMenu;
    
    @FXML
    Button btnLight;
    
    @FXML
    Button btnAngles;
    
    @FXML
    Slider sliderColor;
    
    @FXML
    ChoiceBox btnMaterial1;
    
    @FXML
    ChoiceBox btnMaterial2;
    
    @FXML
    Pane animationPane;
    
    @FXML
    Rectangle rectangle;
    
    @FXML
    TextField textAngle;
    
    @FXML
    TextField textN1;
    
    @FXML
    TextField textN2;
    
    Stage primaryStage;
    Simulation sim;

    Draggable draggableMaker = new Draggable();
    
    private String n1;
    private String n2;
    
    @Getter
    @Setter
    private String angle1;
    
    MenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void initialize(){
        Ray ray = new Ray();
        ray.CreateLines(primaryStage, animationPane);
        draggableMaker.makeDraggable(rectangle, animationPane);
        addMaterials();
        
        textAngle.setOnAction(e -> {
            angle1 = textAngle.getText();
            ray.updateLines(Double.parseDouble(angle1));
         });
        
        textN1.setOnAction(e -> {
            n1 = textN1.getText();
            System.out.println(n1);
         });
        
        textN2.setOnAction(e -> {
            n2 = textN2.getText();
            System.out.println(n2);
         });
        
        btnMaterial1.setOnAction((event) -> {
            int selectedIndex = btnMaterial1.getSelectionModel().getSelectedIndex();
            Object selectedItem = btnMaterial1.getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + btnMaterial1.getValue());
        });
        
    } 
    
    public void addMaterials(){
        ArrayList<Material> listMaterial = new ArrayList<>();
       
        listMaterial.add(new Material(Color.BLUE, "Water", 1.33));
        listMaterial.add(new Material(Color.MAGENTA, "Red", 1.5));
        
        for (int i = 0; i < listMaterial.size(); i++) {
            btnMaterial1.getItems().add(listMaterial.get(i));
        }
        
    }
}

