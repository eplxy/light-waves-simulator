/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage;

import edu.vanier.mainPackage.DoubleSlit.UI.DoubleSlitMain;
import edu.vanier.mainPackage.lens.LensMain;
import edu.vanier.mainPackage.photoelectriceffect.PhotoelectricMain;
import edu.vanier.mainPackage.refraction.Refraction;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author 2148289
 */
public class MainMenuController {

    Stage primaryStage;

    @FXML
    Button btnSettings;

    @FXML
    Button btnDoubleSlit;

    @FXML
    Button btnRefraction;

    @FXML
    Button btnLens;

    @FXML
    Button btnPhotoelectric;

    @FXML
    Button btnStart;

    @FXML
    Label labelDescription;

    @FXML
    ImageView imgPreview;

    private int selectedSimIndex;

    public MainMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }

    public void initialize() throws IOException {

        btnRefraction.setOnAction((event) -> {
            selectedSimIndex = 1;
            displayPreview();
        });

        btnLens.setOnAction((event) -> {
            selectedSimIndex = 2;
            displayPreview();
        });

        btnDoubleSlit.setOnAction((event) -> {
            selectedSimIndex = 3;
            displayPreview();
        });

        btnPhotoelectric.setOnAction((event) -> {
            selectedSimIndex = 4;
            displayPreview();
        });

        btnStart.setOnAction(e -> {
            runSimulation();
        });

        btnSettings.setOnAction((event) -> {
            handleBtnSettings();

        });

    }

    private void displayPreview() {
        clearButtonHighlights();
        switch (selectedSimIndex) {
            case 1:
                imgPreview.setImage(new Image(this.getClass().getResource("/images/previewRefraction.png").toString()));
                labelDescription.setText("Experiment with the physical phenomenon of refraction and reflection through different transparent mediums.");
                btnRefraction.setBorder(Border.stroke(Color.WHITE));
                break;
            case 2:
                imgPreview.setImage(new Image(this.getClass().getResource("/images/previewLens.png").toString()));
                labelDescription.setText("Visualize geometric optics by adjusting lens and source object properties, such as position, focal length, and more.");
                btnLens.setBorder(Border.stroke(Color.WHITE));
                break;
            case 3:
                imgPreview.setImage(new Image(this.getClass().getResource("/images/previewDoubleSlit.png").toString()));
                labelDescription.setText("Simulate the interactions of light waves passing through small slits. Control properties and visualize their impact through an animated and/or graphical view.");
                btnDoubleSlit.setBorder(Border.stroke(Color.WHITE));
                break;
            case 4:
                imgPreview.setImage(new Image(this.getClass().getResource("/images/previewPhotoelectric.png").toString()));
                labelDescription.setText("Experience the photoelectric effect before your vey eyes. Control which wavelengths, intensities, voltages and metals you want to experiment with.");
                btnPhotoelectric.setBorder(Border.stroke(Color.WHITE));
        }
        labelDescription.setTextFill(Color.BLACK);
    }

    private void clearButtonHighlights() {
        btnRefraction.setBorder(Border.EMPTY);
        btnLens.setBorder(Border.EMPTY);
        btnDoubleSlit.setBorder(Border.EMPTY);
        btnPhotoelectric.setBorder(Border.EMPTY);
    }

    private void runSimulation() {
        switch (selectedSimIndex) {
            case 1:
                Refraction refractionMain = new Refraction();
                try {
                    refractionMain.start(primaryStage);
                } catch (Exception ex) {
                    System.err.println(ex.toString());
                }
                break;
            case 2:
                LensMain lensMain = new LensMain();
                try {
                    lensMain.start(primaryStage);
                } catch (Exception ex) {
                    System.err.println(ex.toString());
                }
                break;
            case 3:
                DoubleSlitMain doubleSlitMain = new DoubleSlitMain();
                try {
                    doubleSlitMain.start(primaryStage);
                } catch (Exception ex) {
                    System.err.println(ex.toString());
                }

                break;
            case 4:
                PhotoelectricMain photoelectricMain = new PhotoelectricMain();
                try {
                    photoelectricMain.start(primaryStage);
                } catch (IOException ex) {
                    System.err.println(ex.toString());
                }
                break;
        }
    }

    private void handleBtnSettings() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/generalSettings.fxml"));
        GeneralSettingsController settingsController = new GeneralSettingsController(primaryStage);
        loader.setController(settingsController);
        try {
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
