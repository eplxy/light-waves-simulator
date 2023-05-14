/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.mainPackage;

import DoubleSlit.UI.DoubleSlitMain;
import DoubleSlit.UI.DoubleSlitMenuController;
import DoubleSlit.UI.GraphController;
import edu.vanier.mainPackage.lens.LensMain;
import edu.vanier.mainPackage.photoelectriceffect.PhotoelectricMain;
import edu.vanier.mainPackage.refraction.Refraction;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
    Button btnSettings;

    public MainMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }

    public void initialize() throws IOException {

        btnLens.setOnAction((event) -> {
            LensMain lensMain = new LensMain();
            try {
                lensMain.start(primaryStage);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        });

        btnRefraction.setOnAction((event) -> {
            Refraction refractionMain = new Refraction();

            try {
                refractionMain.start(primaryStage);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        });

        btnPhotoelectric.setOnAction((event) -> {
            PhotoelectricMain photoelectricMain = new PhotoelectricMain();
            try {
                photoelectricMain.start(primaryStage);
            } catch (IOException ex) {
                System.err.println(ex.toString());
            }

        });
        btnDoubleSlit.setOnAction((event) -> {
            DoubleSlitMain doubleSlitMain = new DoubleSlitMain();
                try {
                    doubleSlitMain.start(primaryStage);
                } catch (Exception ex) {
                    System.err.println(ex.toString());
                }
        });
        btnSettings.setOnAction((event) -> {
            handleBtnSettings();
            
        });

    }
    
    private void handleBtnSettings(){
        FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/generalSettings.fxml"));
                GeneralSettingsController settingsController = new GeneralSettingsController(primaryStage);
                loader.setController(settingsController);
            try {
                BorderPane root = loader.load();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {System.out.println(ex);}
    }
}
