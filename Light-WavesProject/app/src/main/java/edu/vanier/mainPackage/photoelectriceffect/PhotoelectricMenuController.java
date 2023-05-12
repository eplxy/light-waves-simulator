package edu.vanier.mainPackage.photoelectriceffect;

import edu.vanier.mainPackage.MainApp;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

    /**
    *This class is a simulation of the photoelectric effect.
    *It initializes a window with sliders to control the initial intensity,
    *wavelength, and metal, and also has buttons to play, reset, and show help.
    *The simulation displays an animation with circles representing the emitted
    *electrons. The metal chosen affects the work function needed to emit
    *electrons, while the wavelength chosen affects the energy of the photon
    *used to emit electrons. The simulation shows the effects of changing these
    *values on the emission of electrons.
    *@author maesha
    */

public class PhotoelectricMenuController{
    // Variables
    /**
     * The number of circles to generate
     */
    private int numCircles;

    /**
     * Indicates whether or not circles have been generated
     */
    private boolean circlesGenerated = false;

    /**
     * The minimum energy required to eject an electron
     */
    private double minimumEnergy;

    /**
     * Indicates whether or not the Play button was pressed
     */
    private boolean buttonWasPressed = false;

    /**
     * The wavelength of the incident light
     */
    private double wavelength;

    /**
     * The type of metal being used in the simulation
     */

    private String metal;
        public PhotoelectricMenuController(int numCircles, double minimumEnergy, double wavelength, String metal, AnchorPane pane, Slider sliderIntensity, Slider sliderWavelength, Slider sliderBatteryVoltage, MenuButton metalMenuButton, MenuItem magnesiumMenuItem, MenuItem aluminiumMenuItem, MenuItem calciumMenuItem, MenuItem copperMenuItem, MenuItem goldMenuItem, HashMap<String, Double> metalWorkFunctions, Label intensityLabel, Label wavelengthLabel, Label batteryVoltageLabel, Label workFunctionLabel, Label minimumEnergyLabel, Label displayBatteryVoltageLabel, Stage stage) {
            this.numCircles = numCircles;
            this.minimumEnergy = minimumEnergy;
            this.wavelength = wavelength;
            this.metal = metal;
            this.pane = pane;
            this.sliderIntensity = sliderIntensity;
            this.sliderWavelength = sliderWavelength;
            this.sliderBatteryVoltage = sliderBatteryVoltage;
            this.metalMenuButton = metalMenuButton;
            this.magnesiumMenuItem = magnesiumMenuItem;
            this.aluminiumMenuItem = aluminiumMenuItem;
            this.calciumMenuItem = calciumMenuItem;
            this.copperMenuItem = copperMenuItem;
            this.goldMenuItem = goldMenuItem;
            this.metalWorkFunctions = metalWorkFunctions;
            this.intensityLabel = intensityLabel;
            this.wavelengthLabel = wavelengthLabel;
            this.batteryVoltageLabel = batteryVoltageLabel;
            this.workFunctionLabel = workFunctionLabel;
            this.minimumEnergyLabel = minimumEnergyLabel;
            this.displayBatteryVoltageLabel = displayBatteryVoltageLabel;
            this.stage = stage;
        }
        
        
    
    //object
    Photon photon = new Photon();
    
    //JavaFX components
    /**
     * The AnchorPane on which the simulation is displayed.
     */
    @FXML
    private AnchorPane pane;

    /**
     * The Polygon representing the light in the simulation.
     */
    @FXML
    private Polygon light = new Polygon();

    /**
     * The Slider controlling the intensity of the light in the simulation.
     */
    @FXML
    private Slider sliderIntensity;

    /**
     * The Slider controlling the wavelength of the light in the simulation.
     */
    @FXML
    private Slider sliderWavelength;

    /**
     * The Slider controlling the voltage of the battery in the simulation.
     */
    @FXML
    private Slider sliderBatteryVoltage;

    /**
     * The MenuButton for selecting the type of metal used in the simulation.
     */
    @FXML
    private MenuButton metalMenuButton;

    /**
     * The MenuItem for selecting magnesium as the metal used in the simulation.
     */
    @FXML
    private MenuItem magnesiumMenuItem;

    /**
     * The MenuItem for selecting aluminium as the metal used in the simulation.
     */
    @FXML
    private MenuItem aluminiumMenuItem;

    /**
     * The MenuItem for selecting calcium as the metal used in the simulation.
     */
    @FXML
    private MenuItem calciumMenuItem;

    /**
     * The MenuItem for selecting copper as the metal used in the simulation.
     */
    @FXML
    private MenuItem copperMenuItem;

    /**
     * The MenuItem for selecting gold as the metal used in the simulation.
     */
    @FXML
    private MenuItem goldMenuItem;

    /**
     * The HashMap containing the work functions for the different metals used in the simulation.
     */
    @FXML
    private HashMap<String, Double> metalWorkFunctions;

    /**
     * The Button for starting the simulation.
     */
    @FXML
    private Button btnPlay = new Button();

    /**
     * The Button for opening the help dialog.
     */
    @FXML 
    private Button btnHelp = new Button();

    /**
     * The Button for returning to the main menu.
     */
    @FXML
    private Button btnMainMenu = new Button();

    /**
     * The Label displaying the current intensity of the light in the simulation.
     */
    @FXML 
    private Label intensityLabel;

    /**
     * The Label displaying the current wavelength of the light in the simulation.
     */
    @FXML 
    private Label wavelengthLabel;

    /**
     * The Label displaying the current voltage of the battery in the simulation.
     */
    @FXML 
    private Label batteryVoltageLabel;

    /**
     * The Label displaying the work function of the metal used in the simulation.
     */
    @FXML
    private Label workFunctionLabel;

    /**
     * The Label displaying the minimum energy required for the photoelectric effect to occur.
     */
    @FXML
    private Label minimumEnergyLabel;

    /**
     * The Label displaying the current voltage of the battery in the simulation.
     */
    @FXML
    private Label displayBatteryVoltageLabel;

    /**
     * The Stage in which the simulation is displayed.
     */
    Stage stage;
    
    public PhotoelectricMenuController(Stage stage){}

    /**
     * The Electron object representing the electrons in the simulation.
     */
    Electron electron = new Electron();
    
    //initialize
    public void initialize(){
        /**
        *This method initializes the simulation. It creates a hashmap with metal
        *names and their corresponding work functions. It also sets up the buttons
        *and sliders used in the simulation and sets their listeners.
        */
        metalWorkFunctions = new HashMap<>();
        metalWorkFunctions.put("Magnesium", 3.7);
        metalWorkFunctions.put("Aluminium", 4.28);
        metalWorkFunctions.put("Calcium", 3.0);
        metalWorkFunctions.put("Copper", 5.0);
        metalWorkFunctions.put("Gold", 5.1);
        
        btnMainMenu.setOnAction(e -> {
            MainApp mainApp = new MainApp();
            try {
                mainApp.start(stage);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        });
        
        /**
         * what this does is when the help button is clicked, a window opens up
         * explaining how to use this simulation. 
         */
        btnHelp.setOnAction((ActionEvent event) -> {
            Stage helpStage = new Stage();
            helpStage.setTitle("PHOTOELECTRIC EFFECT");
            
            TextFlow helpTextFlow = new TextFlow();
            Text helpText = new Text("This is a photoelectric effect simulation.\n"
                    + "To insure the proper functionning of the program, make \n"
                    + "sure to input the initial intensity, wavelength, and \n"
                    + "metal, to get the energy of the photon. It must be a \n"
                    + "positive number or else it will not eject any electrons.\n"
                    + "When a positive minimum energy has been acheived, \n"
                    + "you can click the play button to action the animation\n"
            );
            helpTextFlow.getChildren().add(helpText);

            VBox helpLayout = new VBox();
            helpLayout.getChildren().add(helpTextFlow);
            helpLayout.getChildren().add(helpText);
            Scene helpScene = new Scene(helpLayout, 300, 200);
            helpStage.setScene(helpScene);
            helpStage.show();
        });
        
        /**
         * this generates the animation code when the play button is clicked.
         * it will only run if the minimum energy is higher than 0
         */
        
        btnPlay.setOnAction(e ->{
            if(minimumEnergy > 0){
                
                if (circlesGenerated) {
                    pane.getChildren().removeIf(node -> node instanceof Circle);
                }
                electron.generateCircles(numCircles, pane);
                circlesGenerated = true;
                buttonWasPressed = true;
            }  
                
        });
        /**
         * this is to update the intensity slider
         */
        sliderIntensity.valueProperty().addListener((observable, oldValue, newValue) -> {
            /**
             * this updates the intensity label every time the user changes 
             * something
             */
            intensityLabel.setText((String.format("%.2f", newValue)) + " %");
           
            /**
             * similarly, this updates the intensity of the animation according 
             * to the percentage of intensity
             */
            int intensity = newValue.intValue();
            numCircles = (intensity / 2);
            
            
            
            if(minimumEnergy > 0){
                if (buttonWasPressed == true) {
                    if (circlesGenerated) {
                        
                        pane.getChildren().removeIf(node -> node instanceof Circle);
                    }
                    electron.generateCircles(numCircles, pane);
                    circlesGenerated = true;
                }
            }
            
        });
        
        sliderWavelength.valueProperty().addListener((observable, oldValue, newValue) -> {
            wavelengthLabel.setText((String.format("%.2f", newValue)) + " nm");
            wavelength = newValue.doubleValue();
            Color colour = photon.getColorForWavelength(wavelength);
            
            Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(1, colour)};
            LinearGradient lg1 = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops);
            light.setFill(lg1);
            
            if(buttonWasPressed == true){
            minimumEnergy = photon.photonMinuimumEnergy(wavelength, metal);
            minimumEnergyLabel.setText(String.format("%.2f J", minimumEnergy));
            }
            
            if(minimumEnergy > 0 && buttonWasPressed == true){
                if (circlesGenerated) {
                    pane.getChildren().removeIf(node -> node instanceof Circle);
                }
                electron.generateCircles(numCircles, pane);
                circlesGenerated = true;
            }
        }
        
        );
        
        sliderBatteryVoltage.valueProperty().addListener((observable, oldValue, newValue) -> {
            batteryVoltageLabel.setText((String.format("%.2f", newValue)) + " V");
            displayBatteryVoltageLabel.setText((String.format("%.2f", newValue)) + " V");
        });
        
        magnesiumMenuItem.setOnAction(event -> {
            setWorkFunction("Magnesium", wavelength);
            metal = "Magnesium";
        });
        
        aluminiumMenuItem.setOnAction(event -> {
            setWorkFunction("Aluminium", wavelength);
            metal = "Aluminium";
        });
        
        calciumMenuItem.setOnAction(event -> {
                setWorkFunction("Calcium", wavelength);
                metal = "Calcium";
        });
        
        copperMenuItem.setOnAction(event -> {
            setWorkFunction("Copper", wavelength);
            metal = "Copper";
        });
        
        goldMenuItem.setOnAction(event -> {
            setWorkFunction("Gold", wavelength);
            metal = "Copper";
        });
    }
    /**
    * Sets the work function and minimum energy values based on the given metal and wavelength.
    * @param metal the metal whose work function is being set
    * @param wavelength the wavelength of the photon
    */
    private void setWorkFunction(String metal, double wavelength) {
        double workFunction = metalWorkFunctions.get(metal);
        workFunctionLabel.setText(String.format("%.2f eV", workFunction));
        minimumEnergy = photon.photonMinuimumEnergy(wavelength, metal);
        minimumEnergyLabel.setText(String.format("%.2f J", minimumEnergy));
    }

    /**
    * Returns the number of circles generated.
    * @return the number of circles generated
    */
   public int getNumCircles() {
       return numCircles;
   }

   /**
    * Sets the number of circles generated.
    * @param numCircles the number of circles to set
    */
   public void setNumCircles(int numCircles) {
       this.numCircles = numCircles;
   }

   /**
    * Returns a boolean indicating whether or not the circles have been generated.
    * @return true if the circles have been generated, false otherwise
    */
   public boolean isCirclesGenerated() {
       return circlesGenerated;
   }

   /**
    * Sets a boolean indicating whether or not the circles have been generated.
    * @param circlesGenerated true if the circles have been generated, false otherwise
    */
   public void setCirclesGenerated(boolean circlesGenerated) {
       this.circlesGenerated = circlesGenerated;
   }

   /**
    * Returns the minimum energy value.
    * @return the minimum energy value
    */
   public double getMinimumEnergy() {
       return minimumEnergy;
   }

   /**
    * Sets the minimum energy value.
    * @param minimumEnergy the minimum energy value to set
    */
   public void setMinimumEnergy(double minimumEnergy) {
       this.minimumEnergy = minimumEnergy;
   }

   /**
    * Returns a boolean indicating whether or not the button was pressed.
    * @return true if the button was pressed, false otherwise
    */
   public boolean isButtonWasPressed() {
       return buttonWasPressed;
   }

   /**
    * Sets a boolean indicating whether or not the button was pressed.
    * @param buttonWasPressed true if the button was pressed, false otherwise
    */
   public void setButtonWasPressed(boolean buttonWasPressed) {
       this.buttonWasPressed = buttonWasPressed;
   }

   /**
    * Returns the wavelength value.
    * @return the wavelength value
    */
   public double getWavelength() {
       return wavelength;
   }

   /**
    * Sets the wavelength value.
    * @param wavelength the wavelength value to set
    */
   public void setWavelength(double wavelength) {
       this.wavelength = wavelength;
   }

   /**
    * Returns the metal value.
    * @return the metal value
    */
   public String getMetal() {
       return metal;
   }

   /**
    * Sets the metal value.
    * @param metal the metal value to set
    */
   public void setMetal(String metal) {
       this.metal = metal;
   }

   /**
    * Returns the Photon object.
    * @return the Photon object
    */
   public Photon getPhoton() {
       return photon;
   }

   /**
    * Sets the Photon object.
    * @param photon the Photon object to set
    */
   public void setPhoton(Photon photon) {
       this.photon = photon;
   }

   /**
    * Returns the AnchorPane object.
    * @return the AnchorPane object
    */
   public AnchorPane getPane() {
       return pane;
   }

   /**
    * Sets the AnchorPane object.
    * @param pane the AnchorPane object to set
    */
   public void setPane(AnchorPane pane) {
       this.pane = pane;
   }

   /**
    * Returns the Polygon object representing the light.
    * @return the Polygon object representing the light
    */
   public Polygon getLight() {
       return light;
   }


    public void setLight(Polygon light) {
        this.light = light;
    }

    public Slider getSliderIntensity() {
        return sliderIntensity;
    }

    public void setSliderIntensity(Slider sliderIntensity) {
        this.sliderIntensity = sliderIntensity;
    }

    public Slider getSliderWavelength() {
        return sliderWavelength;
    }

    public void setSliderWavelength(Slider sliderWavelength) {
        this.sliderWavelength = sliderWavelength;
    }

    public Slider getSliderBatteryVoltage() {
        return sliderBatteryVoltage;
    }

    public void setSliderBatteryVoltage(Slider sliderBatteryVoltage) {
        this.sliderBatteryVoltage = sliderBatteryVoltage;
    }

    public MenuButton getMetalMenuButton() {
        return metalMenuButton;
    }

    public void setMetalMenuButton(MenuButton metalMenuButton) {
        this.metalMenuButton = metalMenuButton;
    }

    public MenuItem getMagnesiumMenuItem() {
        return magnesiumMenuItem;
    }

    public void setMagnesiumMenuItem(MenuItem magnesiumMenuItem) {
        this.magnesiumMenuItem = magnesiumMenuItem;
    }

    public MenuItem getAluminiumMenuItem() {
        return aluminiumMenuItem;
    }

    public void setAluminiumMenuItem(MenuItem aluminiumMenuItem) {
        this.aluminiumMenuItem = aluminiumMenuItem;
    }

    public MenuItem getCalciumMenuItem() {
        return calciumMenuItem;
    }

    public void setCalciumMenuItem(MenuItem calciumMenuItem) {
        this.calciumMenuItem = calciumMenuItem;
    }

    public MenuItem getCopperMenuItem() {
        return copperMenuItem;
    }

    public void setCopperMenuItem(MenuItem copperMenuItem) {
        this.copperMenuItem = copperMenuItem;
    }

    public MenuItem getGoldMenuItem() {
        return goldMenuItem;
    }

    public void setGoldMenuItem(MenuItem goldMenuItem) {
        this.goldMenuItem = goldMenuItem;
    }

    public HashMap<String, Double> getMetalWorkFunctions() {
        return metalWorkFunctions;
    }

    public void setMetalWorkFunctions(HashMap<String, Double> metalWorkFunctions) {
        this.metalWorkFunctions = metalWorkFunctions;
    }

    public Button getBtnPlay() {
        return btnPlay;
    }

    public void setBtnPlay(Button btnPlay) {
        this.btnPlay = btnPlay;
    }

    public Button getBtnHelp() {
        return btnHelp;
    }

    public void setBtnHelp(Button btnHelp) {
        this.btnHelp = btnHelp;
    }

    public Button getBtnMainMenu() {
        return btnMainMenu;
    }

    public void setBtnMainMenu(Button btnMainMenu) {
        this.btnMainMenu = btnMainMenu;
    }

    public Label getIntensityLabel() {
        return intensityLabel;
    }

    public void setIntensityLabel(Label intensityLabel) {
        this.intensityLabel = intensityLabel;
    }

    public Label getWavelengthLabel() {
        return wavelengthLabel;
    }

    public void setWavelengthLabel(Label wavelengthLabel) {
        this.wavelengthLabel = wavelengthLabel;
    }

    public Label getBatteryVoltageLabel() {
        return batteryVoltageLabel;
    }

    public void setBatteryVoltageLabel(Label batteryVoltageLabel) {
        this.batteryVoltageLabel = batteryVoltageLabel;
    }

    public Label getWorkFunctionLabel() {
        return workFunctionLabel;
    }

    public void setWorkFunctionLabel(Label workFunctionLabel) {
        this.workFunctionLabel = workFunctionLabel;
    }

    public Label getMinimumEnergyLabel() {
        return minimumEnergyLabel;
    }

    public void setMinimumEnergyLabel(Label minimumEnergyLabel) {
        this.minimumEnergyLabel = minimumEnergyLabel;
    }

    public Label getDisplayBatteryVoltageLabel() {
        return displayBatteryVoltageLabel;
    }

    public void setDisplayBatteryVoltageLabel(Label displayBatteryVoltageLabel) {
        this.displayBatteryVoltageLabel = displayBatteryVoltageLabel;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Electron getElectron() {
        return electron;
    }

    public void setElectron(Electron electron) {
        this.electron = electron;
    }
}