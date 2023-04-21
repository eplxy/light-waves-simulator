package edu.vanier.mainPackage.refraction;

import edu.vanier.mainPackage.MainApp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
@lombok.Data
@Getter
@Setter
public class MenuController {
    
    @FXML
    Button btnMainMenu;
    
    @FXML
    Button btnLight;
    
    @FXML
    Button btnAngles;
    
    @FXML
    Slider sliderAngle;
    
    @FXML
    Slider sliderColor;
    
    @FXML
    ChoiceBox btnMaterial1;
    
    @FXML
    ChoiceBox btnMaterial2;
    
    @FXML
    Pane animationPane;
    
    @FXML
    Pane materialPane1;
            
    @FXML
    Pane materialPane2;        
    
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
    ArrayList<Material> listMaterial = new ArrayList<>();
    
    private String n1;
    private String n2;
    private int selectedIndex1;
    private int selectedIndex2;
    private String angle1 = "45";
    
    MenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void initialize(){
        Ray ray = new Ray();
        ray.CreateLines(primaryStage, animationPane);
        sliderAngle.setMin(0);
        sliderAngle.setMax(90);
        sliderAngle.setValue(45);
        sliderAngle.setShowTickLabels(true);
        sliderAngle.setShowTickMarks(true);
        sliderAngle.setMajorTickUnit(5);
        sliderAngle.setMinorTickCount(5);
        sliderAngle.setBlockIncrement(1);
        
        draggableMaker.makeDraggable(rectangle, animationPane);
        addMaterials();
        
        
        btnMainMenu.setOnMouseClicked((event) -> {
            MainApp mainApp = new MainApp();
            try {
                mainApp.start(primaryStage);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        });
        
        textAngle.setOnAction(e -> {
            angle1 = textAngle.getText();
            System.out.println(listMaterial.get(selectedIndex1));
            System.out.println(listMaterial.get(selectedIndex2));
            ray.updateLines(Double.parseDouble(angle1), listMaterial.get(selectedIndex1).getRefractionIndex(), listMaterial.get(selectedIndex2).getRefractionIndex());
         });
        
        textN1.setOnAction(e -> {
            n1 = textN1.getText();
            System.out.println(n1);
         });
        
        textN2.setOnAction(e -> {
            n2 = textN2.getText();
            System.out.println(n2);
         });
        
        btnAngles.setOnAction((event) -> {
            if (ray.getArcIncidentRay().isDisabled() & ray.getArcRefractedRay().isDisabled()) {
                
                ray.getArcIncidentRay().setDisable(false);
                ray.getArcRefractedRay().setDisable(false);
                
                ray.getArcIncidentRay().setVisible(true);
                ray.getArcRefractedRay().setVisible(true);
            }
            
            else if (!(ray.getArcIncidentRay().isDisabled() & ray.getArcRefractedRay().isDisabled())) {
                ray.getArcIncidentRay().setDisable(true);
                ray.getArcRefractedRay().setDisable(true);
                
                ray.getArcIncidentRay().setVisible(false);
                ray.getArcRefractedRay().setVisible(false);
                
            }
        });
        
       
        sliderAngle.valueProperty().addListener(new ChangeListener<Number>() {
         public void changed(ObservableValue <?extends Number>observable, Number oldValue, Number newValue){
            ray.updateLines(Double.parseDouble(newValue.toString()), listMaterial.get(selectedIndex1).getRefractionIndex(), listMaterial.get(selectedIndex2).getRefractionIndex());
         }
        });
         
        btnLight.setOnAction((event) -> {
             
        }); 
        
        btnMaterial1.setOnAction((event) -> {
            selectedIndex1 = btnMaterial1.getSelectionModel().getSelectedIndex();
            Object selectedItem = btnMaterial1.getSelectionModel().getSelectedItem();
            System.out.println(angle1);

            ray.materialUpdateLines1(selectedIndex1,selectedIndex2, materialPane1, listMaterial, angle1);
            System.out.println("Selection made: [" + selectedIndex1 + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + btnMaterial1.getValue());
        });
        
        btnMaterial2.setOnAction((event) -> {
            selectedIndex2 = btnMaterial2.getSelectionModel().getSelectedIndex();
            Object selectedItem = btnMaterial2.getSelectionModel().getSelectedItem();

            System.out.println(angle1);
            ray.materialUpdateLines2(selectedIndex1, selectedIndex2, materialPane2, listMaterial, angle1);
            System.out.println("Selection made: [" + selectedIndex2 + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + btnMaterial2.getValue());
        });
        
    } 
    
     public void addMaterials(){
        
        listMaterial.add(new Material(Color.TRANSPARENT, "Air", 1.0));
        listMaterial.add(new Material(Color.LIGHTSKYBLUE, "Water", 1.33));
        listMaterial.add(new Material(Color.YELLOW, "Kerosene", 1.39));
        listMaterial.add(new Material(Color.WHITE, "Lens (Human)", 1.406));
        listMaterial.add(new Material(Color.WHEAT, "Benzene", 1.501));
        listMaterial.add(new Material(Color.GOLDENROD, "Amber", 1.55));
        listMaterial.add(new Material(Color.CORAL, "Topaz", 1.60));
        listMaterial.add(new Material(Color.LIGHTGOLDENRODYELLOW, "Carbon Disulfide", 1.628));
        listMaterial.add(new Material(Color.STEELBLUE, "Sapphire", 1.770));
        listMaterial.add(new Material(Color.LIGHTBLUE, "Diamond", 2.417));
        listMaterial.add(new Material(Color.SLATEGREY, "Silicon", 3.42)); 
        
        for (int i = 0; i < listMaterial.size(); i++) {
            btnMaterial1.getItems().add(listMaterial.get(i));
            btnMaterial2.getItems().add(listMaterial.get(i));
        }
        
    }
}

