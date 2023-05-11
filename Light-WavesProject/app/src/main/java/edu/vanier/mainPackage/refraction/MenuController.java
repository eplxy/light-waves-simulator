package edu.vanier.mainPackage.refraction;

import edu.vanier.mainPackage.MainApp;
import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The MenuController class is the controller for the main menu FXML file.
 * It contains methods and event handlers for handling user input and updating
 * the visual representation of the application.
 *
 * @author Matthew Hantar
 */
@lombok.Data
public class MenuController {
    /**
     * The main menu button that returns the user to the main menu.
     */
    @FXML
    Button btnMainMenu;
    
     /**
     * The button that toggles the line to its initial color.
     */
    @FXML
    Button btnLight;
    
    /**
     * The button that toggles the visibility of the angle labels and arcs.
     */
    @FXML
    Button btnAngles;
    
    /**
     * The slider for adjusting the angle of incidence of the light ray.
     */
    @FXML
    Slider sliderAngle;
    
    /**
     * The slider for adjusting the color of the light source.
     */
    @FXML
    Slider sliderColor;
    
     /**
     * The choice box for selecting the first material.
     */
    @FXML
    ChoiceBox choiceBoxMaterial1;
    
    /**
     * The choice box for selecting the first material.
     */
    @FXML
    ChoiceBox choiceBoxMaterial2;
    
     /**
     * The pane where the animation of the light ray and material interfaces are 
     * displayed.
     */
    @FXML
    Pane animationPane;
    
    /**
     * The pane where the first material interface is displayed.
     */
    @FXML
    Pane materialPane1;
            
    /**
     * The pane where the second material interface is displayed.
     */
    @FXML
    Pane materialPane2;
    
    /**
     * The text field for entering the angle of incidence of the light ray.
     */
    @FXML
    TextField textAngle;
    
    /**
     * The label for displaying the refractive index of the first material.
     */
    @FXML
    Label labelMaterial1 = new Label("Material index 1: ");
    
    /**
     * The label for displaying the refractive index of the second material.
     */
    @FXML
    Label labelMaterial2 = new Label("Material index 2: ");
    
    Stage primaryStage;
    ArrayList<Material> listMaterial = new ArrayList<>();
    private int selectedIndex1;
    private int selectedIndex2;
    private String angle1 = "45";
    
     /**
     * Constructs a new MenuController object with the given stage.
     *
     * @param primaryStage the stage where the application is displayed.
     */
    MenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    /**
     * The initialize method creates the lines, adds the materials, then it
     * updates the lines with the user input.
     */
    public void initialize(){
        Ray ray = new Ray();
        Light light = new Light();
        
        /**
         * These methods create the lines from the ray class which inherits from
         * the LineBuild class. Then the next methods add the materials and the 
         * sliders visuals. 
         */
        ray.CreateLines(primaryStage, animationPane);
        addMaterials();
        sliderSetup();
        
        /**
         * This action event makes the user go back to the main menu.
         */
        btnMainMenu.setOnAction(e -> {
            MainApp mainApp = new MainApp();
            try {
                mainApp.start(primaryStage);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        });
        
        /**
         * This action event updates the angle of the incident line based on 
         * the user input. 
         */
        textAngle.setOnAction(e -> {
            angle1 = textAngle.getText();
            System.out.println(listMaterial.get(selectedIndex1));
            System.out.println(listMaterial.get(selectedIndex2));
            ray.updateLines(Double.parseDouble(angle1), listMaterial.get(selectedIndex1).getRefractionIndex(), listMaterial.get(selectedIndex2).getRefractionIndex());
         });
        
        /**
         * This action event disables the angles.
         */
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
        
        /**
         * This action event is for the slider that changes the angle.
         */
        sliderAngle.valueProperty().addListener((ObservableValue <?extends Number>observable, Number oldValue, Number newValue) -> {
            ray.updateLines(Double.parseDouble(newValue.toString()), listMaterial.get(selectedIndex1).getRefractionIndex(), listMaterial.get(selectedIndex2).getRefractionIndex());
        });
        
         /**
          * This action event is for the slider that changes the color.
          */
        sliderColor.valueProperty().addListener((ObservableValue <?extends Number>observable, Number oldValue, Number newValue) -> {
            light.colorLines(ray.getIncidentRay(), ray.getRefractedRay(), ray.getTotalRefractedRay(), (double) newValue);
        });
        
        /**
         * The action event puts the different colors of the light to their 
         * original colors.
         */
        btnLight.setOnAction((event) -> {
             ray.getIncidentRay().setStroke(Color.BLACK);
             ray.getRefractedRay().setStroke(Color.BLACK);
             ray.getTotalRefractedRay().setStroke(Color.BLACK);
        }); 
        
        /**
         * This action event updates the choiceBox for the materials 1 depending 
         * on the selected index chosen.
         */
        choiceBoxMaterial1.setOnAction((event) -> {
            selectedIndex1 = choiceBoxMaterial1.getSelectionModel().getSelectedIndex();
            
            
            labelMaterial1.setText("Material index 1: " + listMaterial.get(selectedIndex1).getRefractionIndex() + " ");
            ray.materialUpdateLines1(selectedIndex1,selectedIndex2, materialPane1, listMaterial, angle1);
            
        });
        
        /**
         * This action event updates the choiceBox for the materials 2 depending 
         * on the selected index chosen.
         */
        choiceBoxMaterial2.setOnAction((event) -> {
            selectedIndex2 = choiceBoxMaterial2.getSelectionModel().getSelectedIndex();
            
            labelMaterial2.setText("Material index 2: " + listMaterial.get(selectedIndex2).getRefractionIndex() + " ");
            
            ray.materialUpdateLines2(selectedIndex1, selectedIndex2, materialPane2, listMaterial, angle1);
            
        });
        
    } 
    
     /**
     * Sets up the slider properties for adjusting the angle of incidence of the
     * light ray and the color of the light source.
     */
    public void sliderSetup(){
        sliderAngle.setMin(0);
        sliderAngle.setMax(90);
        sliderAngle.setValue(45);
        sliderAngle.setShowTickLabels(true);
        sliderAngle.setShowTickMarks(true);
        sliderAngle.setMajorTickUnit(5);
        sliderAngle.setMinorTickCount(5);
        sliderAngle.setBlockIncrement(1);
        
        sliderColor.setMin(300);
        sliderColor.setMax(700);
        sliderColor.setValue(300);
        sliderColor.setShowTickLabels(true);
        sliderColor.setShowTickMarks(true);
        sliderColor.setMajorTickUnit(20);
        sliderColor.setMinorTickCount(10);
        sliderColor.setBlockIncrement(5);
    }
    
    /**
     * Adds the materials in the arrayList based on real properties. 
     */
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
            choiceBoxMaterial1.getItems().add(listMaterial.get(i));
            choiceBoxMaterial2.getItems().add(listMaterial.get(i));
        }
        
    }
}

