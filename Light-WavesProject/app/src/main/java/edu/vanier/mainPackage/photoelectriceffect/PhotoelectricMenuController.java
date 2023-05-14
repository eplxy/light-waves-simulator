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
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
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
     * The intensity of the incident light
     */
    private int intensity;

    /**
     * The type of metal being used in the simulation
     */

    private String metal;
        public PhotoelectricMenuController(int numCircles, double minimumEnergy, double wavelength, String metal, AnchorPane pane, Slider sliderIntensity, Slider sliderWavelength, Slider sliderBatteryVoltage, MenuButton metalMenuButton, MenuItem magnesiumMenuItem, MenuItem aluminiumMenuItem, MenuItem calciumMenuItem, MenuItem copperMenuItem, MenuItem goldMenuItem, HashMap<String, Double> metalWorkFunctions, Label intensityLabel, Label wavelengthLabel, Label batteryVoltageLabel, Label workFunctionLabel, Label minimumEnergyLabel, Label displayBatteryVoltageLabel, Stage stage) {
            this.numCircles = numCircles;
            this.minimumEnergy = minimumEnergy;
            this.wavelength = wavelength;
            this.metal = metal;
            this.anchorpane = pane;
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
    private AnchorPane anchorpane;
    
    /**
     * The BorderPane on which the simulation is displayed.
     */
    @FXML
    private BorderPane borderpane;

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
     * The Button for stoping the simulation.
     */
    @FXML
    private Button btnStop = new Button();

    /**
     * The Button for returning to the main menu.
     */
    @FXML
    private Button btnMainMenu = new Button();
    
    /**
     * The Toggle Button for Dark Mode. 
     */
    @FXML
    private ToggleButton darkModeToggle = new ToggleButton();

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
    
    @FXML
    private Rectangle intensityBox;
    @FXML
    private Rectangle wavelengthBox;
    @FXML
    private Rectangle batteryVoltageBox;
    @FXML
    private Rectangle workFunctionBox;
    @FXML
    private Rectangle minimumEnergyBox;
    @FXML
    private Rectangle displayBatteryVoltageBox;
    
    @FXML
    private Text intensityText;
    @FXML
    private Text wavelengthText;
    @FXML
    private Text minimumEnergyText;
    @FXML
    private Text batteryVoltageText;
    
    @FXML
    private Line line1;
    @FXML
    private Line line2;
    @FXML
    private Line line3;
    @FXML
    private Line line4;
    @FXML
    private Line line5;
    @FXML
    private Line line6;
    @FXML
    private Line line7;
    @FXML
    private Line line8;
    @FXML
    private Line line9;
    @FXML
    private Line line10;
    @FXML
    private Line line11;
    
    @FXML
    private Ellipse metalPlate;
    @FXML
    private Ellipse plate;
    
    @FXML
    MenuItem mainMenu = new MenuItem("Item 1");
    @FXML
    MenuItem help = new MenuItem("Item 2");
    
    /**
     * The Stage in which the simulation is displayed.
     */
    Stage stage;
    Stage primaryStage;
    
    PhotoelectricMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * The Electron object representing the electrons in the simulation.
     */
    Electron electron = new Electron();
    
    Image play = new Image("/images/play.png");
    Image stop = new Image("/images/pause.png");
    Image blackStop = new Image("/images/blackpause.png"); 
    Image whitePlay = new Image("/images/whiteplay.png"); 
    
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
        
        mainMenu.setOnAction(e -> {
            MainApp mainApp = new MainApp();
            try {
                mainApp.start(primaryStage);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        });
        
        help.setOnAction((ActionEvent event) -> {
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
        
        ImageView playView = new ImageView(play);
        playView.setFitWidth(25);
        playView.setFitHeight(25);
        
        ImageView stopView = new ImageView(stop);
        stopView.setFitWidth(25);
        stopView.setFitHeight(25);
        
        ImageView blackStopView = new ImageView(blackStop);
        blackStopView.setFitWidth(25);
        blackStopView.setFitHeight(25);
        
        ImageView whitePlayView = new ImageView(whitePlay);
        whitePlayView.setFitWidth(25);
        whitePlayView.setFitHeight(25);
        
        
        
        btnPlay.setGraphic(playView);
        btnStop.setGraphic(blackStopView);
        
        btnStop.setOnAction(e ->{
            anchorpane.getChildren().removeIf(node -> node instanceof Circle);
            circlesGenerated = false;
        });
        
        btnPlay.setOnAction(e ->{
            electronsEjected(minimumEnergy);
            if(minimumEnergy < 0){
                Stage playStage = new Stage();
                playStage.setTitle("Minimum Energy");

                TextFlow helpTextFlow = new TextFlow();
                Text helpText = new Text("There was an error.\n"
                        + "it seems that the minimum energy is \n"
                        + "less than zero. Therefore, the wavelength that you \n"
                        + "chosen does not surpass the threshold compared to \n"
                        + "the energy of the electron on the metal plate."
                );
                helpTextFlow.getChildren().add(helpText);

                VBox playLayout = new VBox();
                playLayout.getChildren().add(helpTextFlow);
                playLayout.getChildren().add(helpText);
                Scene playScene = new Scene(playLayout, 300, 200);
                playStage.setScene(playScene);
                playStage.show();
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
            intensity = newValue.intValue();
            numCircles = (intensity / 2);
            
            electronsEjected(minimumEnergy);
        });
        
        sliderWavelength.valueProperty().addListener((observable, oldValue, newValue) -> {
            /**
             * this updates the wavelength label every time the user changes 
             * something
             */
            wavelengthLabel.setText((String.format("%.2f", newValue)) + " nm");
            wavelength = newValue.doubleValue();
            Color colour = photon.getColorForWavelength(wavelength);
            Stop[] stops = new Stop[] { new Stop(0, Color.TRANSPARENT), new Stop(1, colour)};
            LinearGradient lg1 = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops);
            light.setFill(lg1);
            
            if(metal != null){
            setWorkFunction(metal, wavelength);
            }
            
            electronsEjected(minimumEnergy);
        });
        
        sliderBatteryVoltage.valueProperty().addListener((observable, oldValue, newValue) -> {
            batteryVoltageLabel.setText((String.format("%.2f", newValue)) + " V");
            displayBatteryVoltageLabel.setText((String.format("%.2f", newValue)) + " V");
        });
        
        magnesiumMenuItem.setOnAction(event -> {
            setWorkFunction("Magnesium", wavelength);
            setMetal("Magnesium");
            metalPlate.setFill(Color.PINK);
        });
        
        aluminiumMenuItem.setOnAction(event -> {
            setWorkFunction("Aluminium", wavelength);
            setMetal("Aluminium");
            metalPlate.setFill(Color.GREY);
        });
        
        calciumMenuItem.setOnAction(event -> {
            setWorkFunction("Calcium", wavelength);
            setMetal("Calcium");
            metalPlate.setFill(Color.ORANGE);
        });
        
        copperMenuItem.setOnAction(event -> {
            setWorkFunction("Copper", wavelength);
            setMetal("Copper");
            metalPlate.setFill(Color.BROWN);
        });
        
        goldMenuItem.setOnAction(event -> {
            setWorkFunction("Gold", wavelength);
            setMetal("Gold");
            metalPlate.setFill(Color.YELLOW);
        });
                
        darkModeToggle.setOnAction(event -> {
            if (darkModeToggle.isSelected()) {
                setBlackAndWhite(true, blackStopView, stopView, playView, whitePlayView);
            } else {
                setBlackAndWhite(false, blackStopView, stopView, playView, whitePlayView);
            }
        });
    }
    
    /**
    *Sets the color scheme of the UI to black and white if the input parameter
    *is true, and resets the color scheme to the default colors if the input
    *parameter is false.
    *@param isBlackAndWhite a boolean flag indicating whether the black and white
    * 
    */
    private void setBlackAndWhite(boolean isBlackAndWhite, ImageView blackpause, ImageView whitepause, ImageView blackpay, ImageView whiteplay) {
        if (isBlackAndWhite) {
            intensityLabel.setStyle("-fx-text-fill: white;");
            wavelengthLabel.setStyle("-fx-text-fill: white;");
            batteryVoltageLabel.setStyle("-fx-text-fill: white;");
            workFunctionLabel.setStyle("-fx-text-fill: white;");
            minimumEnergyLabel.setStyle("-fx-text-fill: white;");
            displayBatteryVoltageLabel.setStyle("-fx-text-fill: white;");
            
            btnHelp.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            btnMainMenu.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            metalMenuButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            darkModeToggle.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            
            intensityBox.setStyle("-fx-fill: black;");
            wavelengthBox.setStyle("-fx-fill: black;");
            batteryVoltageBox.setStyle("-fx-fill: black;");
            workFunctionBox.setStyle("-fx-fill: black;");
            minimumEnergyBox.setStyle("-fx-fill: black;");
            displayBatteryVoltageBox.setStyle("-fx-fill: black;");
            
            intensityText.setStyle("-fx-fill: white;");
            wavelengthText.setStyle("-fx-fill: white;");
            minimumEnergyText.setStyle("-fx-fill: white;");
            batteryVoltageText.setStyle("-fx-fill: white;");
            
            line1.setStyle("-fx-stroke: white; -fx-stroke-width: 2px;");
            line2.setStyle("-fx-stroke: white; -fx-stroke-width: 2px;");
            line3.setStyle("-fx-stroke: white; -fx-stroke-width: 2px;");
            line4.setStyle("-fx-stroke: white; -fx-stroke-width: 2px;");
            line5.setStyle("-fx-stroke: white; -fx-stroke-width: 2px;");
            line6.setStyle("-fx-stroke: white; -fx-stroke-width: 2px;");
            line7.setStyle("-fx-stroke: white; -fx-stroke-width: 2px;");
            line8.setStyle("-fx-stroke: white; -fx-stroke-width: 2px;");
            line9.setStyle("-fx-stroke: white; -fx-stroke-width: 2px;");
            line10.setStyle("-fx-stroke: white; -fx-stroke-width: 2px;");
            line11.setStyle("-fx-stroke: white; -fx-stroke-width: 2px;");
            
            metalPlate.setStyle("-fx-stroke: white;");
            plate.setStyle("-fx-stroke: white;");
            
            borderpane.setStyle("-fx-background-color: #333333;");
            
            btnPlay.setGraphic(blackpay);
            btnStop.setGraphic(blackpause);
            
        } else {
            intensityLabel.setStyle("");
            wavelengthLabel.setStyle("");
            batteryVoltageLabel.setStyle("");
            workFunctionLabel.setStyle("");
            minimumEnergyLabel.setStyle("");
            displayBatteryVoltageLabel.setStyle("");
            
            btnHelp.setStyle("");
            btnMainMenu.setStyle("");
            metalMenuButton.setStyle("");
            darkModeToggle.setStyle("");
            
            intensityBox.setStyle("");
            wavelengthBox.setStyle("");
            batteryVoltageBox.setStyle("");
            workFunctionBox.setStyle("");
            minimumEnergyBox.setStyle("");
            displayBatteryVoltageBox.setStyle("");
            
            intensityText.setStyle("");
            wavelengthText.setStyle("");
            minimumEnergyText.setStyle("");
            batteryVoltageText.setStyle("");
            
            line1.setStyle("");
            line2.setStyle("");
            line3.setStyle("");
            line4.setStyle("");
            line5.setStyle("");
            line6.setStyle("");
            line7.setStyle("");
            line8.setStyle("");
            line9.setStyle("");
            line10.setStyle("");
            line11.setStyle("");
            
            metalPlate.setStyle("");
            plate.setStyle("");
            
            borderpane.setStyle("");
            
            btnPlay.setGraphic(whiteplay);
            btnStop.setGraphic(whitepause);
        }
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
    *This method simulates the ejection of electrons with a minimum energy.
    *@param minimumEnergy the minimum energy required for an electron to be ejected
    *This method simulates the ejection of electrons with a minimum energy.
    *It removes all the circles from the pane and generates new circles with the
    *specified minimum energy if it is greater than zero. If it is less than zero,
    *it removes all the circles and sets circlesGenerated to false.
    *@param minimumEnergy the minimum energy required for an electron to be ejected
    */
    private void electronsEjected(double minimumEnergy){
        if (minimumEnergy > 0){
                if (circlesGenerated) {
                    anchorpane.getChildren().removeIf(node -> node instanceof Circle);
                }
                electron.generateCircles(numCircles, anchorpane);
                circlesGenerated = true;
                if(minimumEnergy < 0){
                    anchorpane.getChildren().removeIf(node -> node instanceof Circle);
                    circlesGenerated = false;
                }
            } else {
                anchorpane.getChildren().removeIf(node -> node instanceof Circle);
                circlesGenerated = false;
            }
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
    public AnchorPane getAnchorPane() {
       return anchorpane;
    }

   /**
    * Sets the AnchorPane object.
    * @param pane the AnchorPane object to set
    */
    public void setAnchorPane(AnchorPane anchorpane) {
       this.anchorpane = anchorpane;
    }

   /**
    * Returns the Polygon object representing the light.
    * @return the Polygon object representing the light
    */
    public Polygon getLight() {
       return light;
    }

    /**
    Sets the light polygon.
    @param light the polygon to set
    */
    public void setLight(Polygon light) {
        this.light = light;
    }
    /**

    Gets the slider for intensity.
    @return the intensity slider
    */
    public Slider getSliderIntensity() {
        return sliderIntensity;
    }
    /**

    Sets the slider for intensity.
    @param sliderIntensity the slider to set
    */
    public void setSliderIntensity(Slider sliderIntensity) {
        this.sliderIntensity = sliderIntensity;
    }
    /**

    Gets the slider for wavelength.
    @return the wavelength slider
    */
    public Slider getSliderWavelength() {
        return sliderWavelength;
    }
    /**

    Sets the slider for wavelength.
    @param sliderWavelength the slider to set
    */
    public void setSliderWavelength(Slider sliderWavelength) {
        this.sliderWavelength = sliderWavelength;
    }
    /**

    Gets the slider for battery voltage.
    @return the battery voltage slider
    */
    public Slider getSliderBatteryVoltage() {
        return sliderBatteryVoltage;
    }
    /**

    Sets the slider for battery voltage.
    @param sliderBatteryVoltage the slider to set
    */
    public void setSliderBatteryVoltage(Slider sliderBatteryVoltage) {
        this.sliderBatteryVoltage = sliderBatteryVoltage;
    }
    /**

    Gets the metal menu button.
    @return the metal menu button
    */
    public MenuButton getMetalMenuButton() {
        return metalMenuButton;
    }
    /**

    Sets the metal menu button.
    @param metalMenuButton the menu button to set
    */
    public void setMetalMenuButton(MenuButton metalMenuButton) {
        this.metalMenuButton = metalMenuButton;
    }
    /**

    Gets the menu item for magnesium.
    @return the magnesium menu item
    */
    public MenuItem getMagnesiumMenuItem() {
        return magnesiumMenuItem;
    }
    /**

    Sets the menu item for magnesium.
    @param magnesiumMenuItem the menu item to set
    */
    public void setMagnesiumMenuItem(MenuItem magnesiumMenuItem) {
        this.magnesiumMenuItem = magnesiumMenuItem;
    }
    /**

    Gets the menu item for aluminium.
    @return the aluminium menu item
    */
    public MenuItem getAluminiumMenuItem() {
        return aluminiumMenuItem;
    }
    /**

    Sets the menu item for aluminium.
    @param aluminiumMenuItem the menu item to set
    */
    public void setAluminiumMenuItem(MenuItem aluminiumMenuItem) {
        this.aluminiumMenuItem = aluminiumMenuItem;
    }
    /**

    Gets the menu item for calcium.
    @return the calcium menu item
    */
    public MenuItem getCalciumMenuItem() {
        return calciumMenuItem;
    }
    /**

    Sets the menu item for calcium.
    @param calciumMenuItem the menu item to set
    */
    public void setCalciumMenuItem(MenuItem calciumMenuItem) {
        this.calciumMenuItem = calciumMenuItem;
    }
    /**

    Gets the menu item for copper.
    @return the copper menu item
    */
    public MenuItem getCopperMenuItem() {
        return copperMenuItem;
    }
    /**

    Sets the menu item for copper.
    @param copperMenuItem the menu item to set
    */
    public void setCopperMenuItem(MenuItem copperMenuItem) {
        this.copperMenuItem = copperMenuItem;
    }
    /**

    Gets the menu item for gold.
    @return the gold menu item
    */
    public MenuItem getGoldMenuItem() {
        return goldMenuItem;
    }
    /**

    Sets the menu item for gold.
    @param goldMenuItem the menu item to set
    */
    public void setGoldMenuItem(MenuItem goldMenuItem) {
        this.goldMenuItem = goldMenuItem;
    }
    /**

    Gets the metal work functions hash map.
    @return the metal work functions hash map
    */
    public HashMap<String, Double> getMetalWorkFunctions() {
        return metalWorkFunctions;
    }
    
    /**
    *Sets the work function values of the metals in the simulation.
    *@param metalWorkFunctions a HashMap containing the metal names as keys
    * */
    
    public void setMetalWorkFunctions(HashMap<String, Double> metalWorkFunctions) {
        this.metalWorkFunctions = metalWorkFunctions;
    }

    /**

    Returns the "Play" button in the user interface.
    @return the "Play" button.
    */
    public Button getBtnPlay() {
        return btnPlay;
    }
    /**

    Sets the "Play" button in the user interface.
    @param btnPlay the "Play" button to be set.
    */
    public void setBtnPlay(Button btnPlay) {
        this.btnPlay = btnPlay;
    }
    /**

    Returns the "Help" button in the user interface.
    @return the "Help" button.
    */
    public Button getBtnHelp() {
        return btnHelp;
    }
    /**

    Sets the "Help" button in the user interface.
    @param btnHelp the "Help" button to be set.
    */
    public void setBtnHelp(Button btnHelp) {
        this.btnHelp = btnHelp;
    }
    /**

    Returns the "Main Menu" button in the user interface.
    @return the "Main Menu" button.
    */
    public Button getBtnMainMenu() {
        return btnMainMenu;
    }
    /**

    Sets the "Main Menu" button in the user interface.
    @param btnMainMenu the "Main Menu" button to be set.
    */
    public void setBtnMainMenu(Button btnMainMenu) {
        this.btnMainMenu = btnMainMenu;
    }
    /**

    Returns the intensity label in the user interface.
    @return the intensity label.
    */
    public Label getIntensityLabel() {
        return intensityLabel;
    }
    /**

    Sets the intensity label in the user interface.
    @param intensityLabel the intensity label to be set.
    */
    public void setIntensityLabel(Label intensityLabel) {
        this.intensityLabel = intensityLabel;
    }
    /**

    Returns the wavelength label in the user interface.
    @return the wavelength label.
    */
    public Label getWavelengthLabel() {
        return wavelengthLabel;
    }
    /**

    Sets the wavelength label in the user interface.
    @param wavelengthLabel the wavelength label to be set.
    */
    public void setWavelengthLabel(Label wavelengthLabel) {
        this.wavelengthLabel = wavelengthLabel;
    }
    /**

    Returns the battery voltage label in the user interface.
    @return the battery voltage label.
    */
    public Label getBatteryVoltageLabel() {
        return batteryVoltageLabel;
    }
    /**

    Sets the battery voltage label in the user interface.
    @param batteryVoltageLabel the battery voltage label to be set.
    */
    public void setBatteryVoltageLabel(Label batteryVoltageLabel) {
        this.batteryVoltageLabel = batteryVoltageLabel;
    }
    /**

    Returns the work function label in the user interface.
    @return the work function label.
    */
    public Label getWorkFunctionLabel() {
        return workFunctionLabel;
    }
    /**

    Sets the work function label in the user interface.
    @param workFunctionLabel the work function label to be set.
    */
    public void setWorkFunctionLabel(Label workFunctionLabel) {
        this.workFunctionLabel = workFunctionLabel;
    }
    /**

    Returns the minimum energy label in the user interface.
    @return the minimum energy label.
    */
    public Label getMinimumEnergyLabel() {
        return minimumEnergyLabel;
    }
    /**

    Sets the minimum energy label in the user interface.
    @param minimumEnergyLabel the minimum energy label to be set.
    */
    public void setMinimumEnergyLabel(Label minimumEnergyLabel) {
        this.minimumEnergyLabel = minimumEnergyLabel;
    }

        /**
     * Returns the display battery voltage label.
     *
     * @return the display battery voltage label
     */
    public Label getDisplayBatteryVoltageLabel() {
        return displayBatteryVoltageLabel;
    }

    /**
     * Sets the display battery voltage label.
     *
     * @param displayBatteryVoltageLabel the new display battery voltage label to set
     */
    public void setDisplayBatteryVoltageLabel(Label displayBatteryVoltageLabel) {
        this.displayBatteryVoltageLabel = displayBatteryVoltageLabel;
    }

    /**
     * Returns the stage.
     *
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Sets the stage.
     *
     * @param stage the new stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Returns the electron.
     *
     * @return the electron
     */
    public Electron getElectron() {
        return electron;
    }

    /**
     * Sets the electron.
     *
     * @param electron the new electron to set
     */
    public void setElectron(Electron electron) {
        this.electron = electron;
    }

    /**

    Gets the intensity value of the object.
    @return the intensity value of the object
    */
    public int getIntensity() {
        return intensity;
    }
    /**

    Sets the intensity value of the object.
    @param intensity the new intensity value to set
    */
    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
    /**

    Gets the AnchorPane node of the object.
    @return the AnchorPane node of the object
    */
    public AnchorPane getAnchorpane() {
        return anchorpane;
    }
    /**

    Sets the AnchorPane node of the object.
    @param anchorpane the new AnchorPane node to set
    */
    public void setAnchorpane(AnchorPane anchorpane) {
        this.anchorpane = anchorpane;
    }
    /**

    Gets the BorderPane node of the object.
    @return the BorderPane node of the object
    */
    public BorderPane getBorderpane() {
        return borderpane;
    }
    /**

    Sets the BorderPane node of the object.
    @param borderpane the new BorderPane node to set
    */
    public void setBorderpane(BorderPane borderpane) {
        this.borderpane = borderpane;
    }
    /**

    Gets the ToggleButton node of the object.
    @return the ToggleButton node of the object
    */
    public ToggleButton getDarkModeToggle() {
        return darkModeToggle;
    }
    /**

    Sets the ToggleButton node of the object.
    @param darkModeToggle the new ToggleButton node to set
    */
    public void setDarkModeToggle(ToggleButton darkModeToggle) {
        this.darkModeToggle = darkModeToggle;
    }
    /**

    Gets the Stage node of the object.
    @return the Stage node of the object
    */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    /**

    Sets the Stage node of the object.
    @param primaryStage the new Stage node to set
    */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}